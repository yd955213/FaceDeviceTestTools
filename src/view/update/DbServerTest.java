package view.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import sqlite3.DataBaseExecute;
import tools.LogsWriter;
import view.MainIntfaceView;

public class DbServerTest {
	private static JRadioButton sqlJRadioButton = null;
	private static JRadioButton oracleJRadioButton= null;
	private static JRadioButton sqlite3JRadioButton = null;
	private static JComboBox<String> dbNameJComboBox = null;
	private static JTextField serverIPJTextField, userNameJTextField, passwordJTextField;
	private static String serverIP;
	private static String userName;
	private static String password;
	private static String dbName = "master";
	private static Connection conn;
//	private static AtomicInteger mOpenCounter = new AtomicInteger();
	private static String url;
	/**HashMap = {"AccName" : "DataBaseName"} */
	private static HashMap<String, String> dbnameInfoMap = null;
		
	public void updatecomboBox() {
		DefaultComboBoxModel<String> comboBoxModel = (DefaultComboBoxModel<String>) dbNameJComboBox.getModel();
		comboBoxModel.removeAllElements();
		if (!dbnameInfoMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = dbnameInfoMap.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				comboBoxModel.addElement(entry.getKey());
			}
		}
	}
	
	public String doCompareAuthority(String macAddr, List<String> personList) {
		String sql = null;
		if (sqlJRadioButton.isSelected()) {
			sql = getSqlStr(SqlStr.SELECT_GETAUTHORITY_SQL, macAddr);
		}else if(oracleJRadioButton.isSelected()){
			sql = getSqlStr(SqlStr.SELECT_GETAUTHORITY_ORACLE, macAddr);
		}else {
			sql = getSqlStr(SqlStr.SELECT_GETAUTHORITY_SQLITE3, macAddr);
		}
		//获取数据库中该设备的人脸权限的uniqueCode
		List<String> authorityInDb = getAuthority(sql);
		int authorityInDbSize = authorityInDb.size();
		
		List<String> personListInDev = new ArrayList<String>();
		for (String st : personList) {
			personListInDev.add(st);
		}
//		List<String> personListTemp =new ArrayList<String>();
//		for (String st : personList) {
//			personListTemp.add(st);
//		}
		//进行比对
		personListInDev.removeAll(authorityInDb);
		authorityInDb.removeAll(personList);
		/*
		 * 生成日志
		 */
		int devCompareSize = personListInDev.size();
		String data = "";
		if (devCompareSize > 0) {
			data = "设备[" + macAddr + "]权限异常数：" + devCompareSize + " 以下虚拟ID对应人员不在数据库中：\r\n";
			for (Object str : personListInDev) {
				data += str + " ";
			}
			LogsWriter.writeInfo(LogsWriter.COMPARE_AUTHORITY, data);
		}
		int inDbCompareSize = authorityInDb.size();
		/*
		 * 将对比后库中多余人员按照虚拟ID查询人员信息，写日志
		 */
		if (inDbCompareSize > 0) {
			//拼接查询条件 in (, , , )
			data = "(" ;
			for (String str : authorityInDb) {
				data += "'" + str + "', ";
			}
			//去掉多余的空格和逗号（ ,） 
			data = data.substring(0, data.length() - 2);
			data += ")";
			if (sqlJRadioButton.isSelected()) {
				sql = getSqlStr(SqlStr.SELECT_DOWNLOAD_PERSONINFO_SQL, data);
			}else if(oracleJRadioButton.isSelected()){
				sql = getSqlStr(SqlStr.SELECT_DOWNLOAD_PERSONINFO_ORACLE, data);
			}else {
				sql = getSqlStr(SqlStr.SELECT_DOWNLOAD_PERSONINFO_SQLITE3, data);
			}
			//查询人员信息
			List<List<String>> list = getPersonInfo(sql);
			
			data = "库中权限异常数：" + inDbCompareSize + "以下虚拟ID对应人员不在设备[" + macAddr + "]中\r\n";
			LogsWriter.writeInfo(LogsWriter.COMPARE_AUTHORITY, data);
			for (List<String> personInfo : list) {
				data = personInfo.get(0) + "\t" + personInfo.get(1) + "\t" + personInfo.get(2) + "\r\n";
				LogsWriter.writeInfo(LogsWriter.COMPARE_AUTHORITY, data);
			}
		}
		return "<html>设备["+macAddr+"]  权限数：" + personList.size() + "、设备权限异常数：" + devCompareSize +"、库中权限数：" + authorityInDbSize + "、库中权限异常数：" + inDbCompareSize + ";详细信息请查看日志：./logs/权限对比结果</html>";
	}
	
	
	
	
	private static class DbServerTestHandler {
		private static DbServerTest instance  = new DbServerTest();
	}
 	private DbServerTest() {
 		
	}
	/**第一次调用时需调用getIinitialization初识参数
	 * */
	public static synchronized DbServerTest getInstance() {
		serverIP = serverIPJTextField.getText().replaceAll(" ", "");
		userName = userNameJTextField.getText().replaceAll(" ", "");
		password = passwordJTextField.getText().replaceAll(" ", "");
		if (dbNameJComboBox.getItemCount() == 0) {
			dbName = "master";
		}else {
			dbName = dbNameJComboBox.getSelectedItem().toString();
			if(dbnameInfoMap != null) {
				dbName = dbnameInfoMap.get(dbName);
			}
		}
//		System.out.println(dbName);
		return DbServerTestHandler.instance;
	}
	
	public static void setIinitialization(JRadioButton sqlJRadioButton, JRadioButton oracleJRadioButton, JRadioButton sqlite3JRadioButton, 
			 JTextField serverIPJTextField, JTextField userNameJTextField, JTextField passwordJTextField, JComboBox<String> dbNameJComboBox) {
		DbServerTest.dbNameJComboBox = dbNameJComboBox;
		DbServerTest.sqlJRadioButton = sqlJRadioButton;
		DbServerTest.oracleJRadioButton = oracleJRadioButton;
		DbServerTest.sqlite3JRadioButton = sqlite3JRadioButton;
		DbServerTest.serverIPJTextField = serverIPJTextField;
		DbServerTest.userNameJTextField = userNameJTextField;
		DbServerTest.passwordJTextField = passwordJTextField;
	}
//	private synchronized Connection getConnect() {
//		
//		
////		if (mOpenCounter.incrementAndGet() == 1) {
//			conn = getDataBaseConnection();
////		}
//		return conn;
//	}
	
	/**获取sql数据库名称，用于显示用*/
	public synchronized HashMap<String, String> getDBname() {
		String sql = "SELECT [AccName],[DataBaseName] FROM [master].[dbo].[aio_account] where accstatus = 1 order by AccID ;";
		conn = getDataBaseConnection();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			dbnameInfoMap = new HashMap<String, String>();
			while(rs.next()) {
				dbnameInfoMap.put(rs.getString("AccName"), rs.getString("DataBaseName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, stat, conn);
		}
		return dbnameInfoMap;
		
	}
	 
	private synchronized Connection getDataBaseConnection() {
		if (sqlite3JRadioButton.isSelected()) {
//			DataBaseExecute.getInstance().getInstance();
			conn = DataBaseExecute.getInstance().getConnect();
		}else {
			try {
				
				Class.forName(getClassForName());
	//			System.out.println("url = " + url);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				conn = DriverManager.getConnection(url, userName, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
				MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
	//			e.printStackTrace();
			}
		}
		return conn;
	}
	
	private static String getClassForName() {
		String classForName = null;
		if (sqlJRadioButton.isSelected()) {
			classForName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			url = "jdbc:sqlserver://" + serverIP + ";databaseName=" + dbName+ ";";
//				dbName = "";
		}else if (oracleJRadioButton.isSelected()) {
			classForName = "oracle.jdbc.OracleDriver";
//				url = "jdbc:odbc:dbjdbc";
//				url = "jdbc:oracle:thin:@"+ serverIP +":1521:"+dataname; 
//				dbName = "
		} 
		return classForName; 
	}
	
	/**List<List<String>>  {name,devName,uniquecode}*/
	private synchronized List<List<String>> getPersonInfo(String sql) {
		// TODO Auto-generated method stub
		conn = getDataBaseConnection();
		Statement stat = null;
		ResultSet rs = null;
		List<List<String>> personInfoList = null;
		System.out.println(sql);
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			personInfoList = new ArrayList<List<String>>();
			List<String> list = null;
			while(rs.next()) {
				list = new ArrayList<String>();
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				personInfoList.add(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, stat, conn);
		}
		return personInfoList;
	}

	private synchronized List<String> getAuthority(String sql) {
		// TODO Auto-generated method stub
		conn = getDataBaseConnection();
		Statement stat = null;
		ResultSet rs = null;
		List<String> authorityList = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			authorityList = new ArrayList<String>();
			while(rs.next()) {
				authorityList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "数据查询", JOptionPane.ERROR_MESSAGE);
		}finally {
			close(rs, stat, conn);
			
		}
		return authorityList;
	}
	
	/**
	 * 返回数据库可执行语句
	 * @param type 查询类型 类SqlStr
	 * @param data 附加条件
	 * @return 完整的sql语句
	 */
	private static String getSqlStr(String type, String data) {
		String sql = null;
		switch(type) {
		
			case SqlStr.SELECT_DOWNLOAD_PERSONINFO_SQL:
				sql = "SELECT hrms_emp.EmpName, Hrms_Dpt.DptName, hrms_emp.uniqueCode, ndr2_facedev_authorset.AuthorStatus, ndr2_facedev_authorset.LastDownTime, ndr2_facedev_authorset.LastDownMemo "
						+ "FROM hrms_emp  LEFT JOIN Hrms_Dpt ON hrms_emp.DptID = Hrms_Dpt.DptID  "
						+ "LEFT JOIN ndr2_facedev_authorset on hrms_emp.uniqueCode = ndr2_facedev_authorset.EmpID "
						+ "WHERE hrms_emp.uniqueCode in " + data +";";
				break;
				
			case SqlStr.SELECT_DOWNLOAD_PERSONINFO_ORACLE:
				sql = "SELECT hrms_emp.EmpName, Hrms_Dpt.DptName, hrms_emp.uniqueCode, ndr2_facedev_authorset.AuthorStatus, ndr2_facedev_authorset.LastDownTime, ndr2_facedev_authorset.LastDownMemo "
						+ "FROM hrms_emp "
						+ "LEFT JOIN Hrms_Dpt ON hrms_emp.DptID = Hrms_Dpt.DptID "
						+ "LEFT JOIN ndr2_facedev_authorset on hrms_emp.uniqueCode = ndr2_facedev_authorset.EmpID "
						+ "WHERE hrms_emp.uniqueCode in " + data +";";
				break;
				
			case SqlStr.SELECT_DOWNLOAD_PERSONINFO_SQLITE3:
				sql = "SELECT p.person_name, p.dpt_name, p.unique_code, f.author_status, f.down_loaded, f.last_down_time, f.remark FROM pearson_info p, face_dev_author_set f where p.Person_id = f.person_id and p.unique_code in " + data +";";
				break;
				
			case SqlStr.SELECT_GETAUTHORITY_SQL:
				sql = "select NDr2_FaceDev_AuthorSet.EmpID from NDr2_FaceDev_AuthorSet LEFT JOIN NDr2_Dev ON NDr2_FaceDev_AuthorSet.DevID = NDr2_Dev.DevID " + 
						"where NDr2_Dev.DevMacAddr = '" + data +"';";
				break;
				
			case SqlStr.SELECT_GETAUTHORITY_ORACLE:
				sql = "";
				break;
				
			case SqlStr.SELECT_GETAUTHORITY_SQLITE3:
				sql = "select face_dev_author_set.person_id from face_dev_author_set,com_dev where face_dev_author_set.dev_id = com_dev.dev_id and com_dev.dev_mac_address = '" + data +"';";
				break;
			
			default:
				break;
		}
		return sql;
	}
	/**数据库执行语言的判断条件*/
	private class SqlStr{
		/**获去库中人员信息*/
		private static final String SELECT_DOWNLOAD_PERSONINFO_SQL = "SELECT_PERSONINFO_SQL";
		/**获去库中人员信息*/
		private static final String SELECT_DOWNLOAD_PERSONINFO_ORACLE = "SELECT_PERSONINFO_ORACLE";
		/**获去库中人员信息*/
		private static final String SELECT_DOWNLOAD_PERSONINFO_SQLITE3 = "SELECT_PERSONINFO_SQLITE3";
		/**按设备获取库中所有人的人脸权限*/
		private static final String SELECT_GETAUTHORITY_SQL = "SELECT_GETAUTHORITY_SQL";
		/**按设备获取库中所有人的人脸权限*/
		private static final String SELECT_GETAUTHORITY_ORACLE = "SELECT_GETAUTHORITY_ORACLE";
		/**按设备获取库中所有人的人脸权限*/
		private static final String SELECT_GETAUTHORITY_SQLITE3 = "SELECT_GETAUTHORITY_SQLITE3";
		
	}
	public Connection getConn() {
		if (sqlite3JRadioButton.isSelected()) {
			conn = DataBaseExecute.getInstance().getConnect();
			if (conn == null) {
				JOptionPane.showMessageDialog(null, "请先连接数据库", "数据库未连接", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		return conn;
	}

	public List<String> getDevMacList_XF() {
		// TODO Auto-generated method stub
		String sql = "select ndr2_dev.DevMacAddr from ndr2_dev,NDr2_FaceDev_DevParm1 where ndr2_dev.DevID = NDr2_FaceDev_DevParm1.DevID and ndr2_dev.DevType = 22 and NDr2_FaceDev_DevParm1.DevUseType = 4;";
		List<String> list = gerResultSet(sql);
		return list;
	}
	public List<String> getDevMacList_MJ() {
		// TODO Auto-generated method stub
		String sql = "select ndr2_dev.DevMacAddr from ndr2_dev,NDr2_FaceDev_DevParm1 where ndr2_dev.DevID = NDr2_FaceDev_DevParm1.DevID and ndr2_dev.DevType = 22 and NDr2_FaceDev_DevParm1.DevUseType <> 4;";
		List<String> list = gerResultSet(sql);
		return list;
	}
	public List<String> getUniqueCodeList() {
		// TODO Auto-generated method stub
		String sql = "select distinct a.uniqueCode from Hrms_Emp a, Pos_FaceDev_AuthorSet b where a.uniqueCode = b.EmpID and b.AuthorStatus = 0;";
		List<String> list = gerResultSet(sql);
		return list;
	}
	public Map<String, String> getDevNumMap() {
		// TODO Auto-generated method stub
		String sql = "SELECT DevMacAddr,devID FROM com_dev WHERE featureType = 3;";
		return gerResultSetMap(sql);
	}
	
	private synchronized Map<String, String> gerResultSetMap(String sql) {
		Map<String, String> map = new HashMap<String, String>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = getDataBaseConnection().createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "数据查询", JOptionPane.ERROR_MESSAGE);
		}finally {
			close(rs, stat, conn);
			
		}
		
		return map;
	} 
	
	private synchronized List<String> gerResultSet(String sql) {
		List<String> list = new ArrayList<String>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = getDataBaseConnection().createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "数据查询", JOptionPane.ERROR_MESSAGE);
		}finally {
			close(rs, stat, conn);
		}
		
		return list;
	} 
	private void close(ResultSet rs, Statement stat ,Connection conn) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		if(!sqlite3JRadioButton.isSelected()) {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
	}
}
