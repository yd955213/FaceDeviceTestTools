package sqlite3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import myGson.das.DownloadAuthorityDataGson;
import tools.SystemTimes;
import view.MainIntfaceView;

public class DataBaseExecute {
	private  String classFroName = "org.sqlite.JDBC";
	protected static  Connection conn = null;
	protected static PreparedStatement ps = null;
	private String url = "jdbc:sqlite:";
	private String  dbPath = "./DB/db.db";
	private AtomicInteger mOpenCounter = new AtomicInteger();
	
	private static class DataBaseExecuteHandler{
		private static DataBaseExecute instance  = new DataBaseExecute();
	}
	
	protected DataBaseExecute() {
		getDataBaseConnection();
	}
	
	public static synchronized DataBaseExecute getInstance() {
		return DataBaseExecuteHandler.instance;
	}
	public  synchronized Connection getConnect() {
		if (mOpenCounter.incrementAndGet() == 1) {
			conn = getDataBaseConnection();
		}
		return conn;
	}
	

	/**
	 * 获取最后一次添加的人员信息
	 * @return
	 */
	public synchronized PersonInfo getPersonInfo() {
		String sql = "select  * from pearson_info order by gmt_create desc limit 0,1;";
		PersonInfo pi = null;
//		System.out.println("sql = " + sql);
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pi = new PersonInfo();
				pi.setPersonID(rs.getInt("person_id"));
				pi.setUniqueCode(rs.getString("unique_code"));
				pi.setPersonType(rs.getInt("person_type"));
				pi.setPersonNo(rs.getString("person_no"));
				pi.setPersonName(rs.getString("person_name"));
				pi.setGender(rs.getInt("gender"));
				pi.setDptName(rs.getString("dpt_name"));
				pi.setIDType(Integer.toString(rs.getInt("id_type")));
				pi.setIDNo(rs.getString("Id_no"));
				pi.setCardNo(rs.getString("card_no"));
				pi.setIsLegal(Integer.toString(rs.getInt("is_legal")));
				pi.setFaceStartUseTime(rs.getString("face_start_use_time"));
				pi.setFaceStopUseTime(rs.getString("face_stop_use_time"));
				pi.setCardStartUseTime(rs.getString("card_start_use_time"));
				pi.setCardEndUseTime(rs.getString("card_end_use_time"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return pi;
	}
		
	public  synchronized DownloadAuthorityDataGson getDownloadAuthorityDataGson() {
//		String sql = "select  * from pearson_info order by gmt_create desc limit 0,1;";
		String sql = "SELECT pearson_info.unique_code, pearson_info.person_type, pearson_info.person_no, pearson_info.person_name, pearson_info.gender, "
				+ "pearson_info.dpt_name, pearson_info.id_type, 	pearson_info.Id_no,	pearson_info.card_no,	pearson_info.is_legal, pearson_info.face_start_use_time, "
				+ "pearson_info.face_stop_use_time, pearson_info.card_start_use_time,	pearson_info.card_end_use_time,	face_photo.PhotoBase64,	face_feature.feature "
				+ "FROM	pearson_info,	face_photo,	face_feature "
				+ "WHERE pearson_info.Person_id = face_photo.Person_id 	"
				+ "AND face_photo.Person_id = face_feature.person_id "
				+ "AND pearson_info.Person_id = face_feature.person_id "
				+ "AND face_photo.PhotoBase64 IS NOT NULL ORDER BY	pearson_info.Person_id desc limit 0,1;";
		DownloadAuthorityDataGson downloadAuthorityDataGson = null;
//		System.out.println("sql = " + sql);
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				downloadAuthorityDataGson = new DownloadAuthorityDataGson();
				downloadAuthorityDataGson.setUniqueCode(rs.getString("unique_code"));
				downloadAuthorityDataGson.setPersonType(rs.getInt("person_type"));
				downloadAuthorityDataGson.setPersonNo(rs.getString("person_no"));
				downloadAuthorityDataGson.setPersonName(rs.getString("person_name"));
				downloadAuthorityDataGson.setGender(rs.getInt("gender"));
				downloadAuthorityDataGson.setDptName(rs.getString("dpt_name"));
				downloadAuthorityDataGson.setIDType(Integer.toString(rs.getInt("id_type")));
				downloadAuthorityDataGson.setIDNo(rs.getString("Id_no"));
				downloadAuthorityDataGson.setCardNo(rs.getString("card_no"));
				downloadAuthorityDataGson.setIsLegal(Integer.toString(rs.getInt("is_legal")));
				downloadAuthorityDataGson.setFaceStartUseTime(rs.getString("face_start_use_time"));
				downloadAuthorityDataGson.setFaceStopUseTime(rs.getString("face_stop_use_time"));
				downloadAuthorityDataGson.setCardStartUseTime(rs.getString("card_start_use_time"));
				downloadAuthorityDataGson.setCardEndUseTime(rs.getString("card_end_use_time"));
				downloadAuthorityDataGson.setPhoto(rs.getString("PhotoBase64"));
				downloadAuthorityDataGson.setCharacter(rs.getString("feature"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return downloadAuthorityDataGson;
	}
	
	public  synchronized DownloadAuthorityDataGson getDownloadAuthorityDataGson(String personID) {
//		String sql = "select  * from pearson_info order by gmt_create desc limit 0,1;";
		String sql = "SELECT pearson_info.unique_code, pearson_info.person_type, pearson_info.person_no, pearson_info.person_name, pearson_info.gender, "
				+ "pearson_info.dpt_name, pearson_info.id_type, 	pearson_info.Id_no,	pearson_info.card_no,	pearson_info.is_legal, pearson_info.face_start_use_time, "
				+ "pearson_info.face_stop_use_time, pearson_info.card_start_use_time,	pearson_info.card_end_use_time,	face_photo.PhotoBase64,	face_feature.feature "
				+ "FROM	pearson_info,	face_photo,	face_feature "
				+ "WHERE pearson_info.Person_id = face_photo.Person_id 	"
				+ "AND face_photo.Person_id = face_feature.person_id "
				+ "AND pearson_info.Person_id = face_feature.person_id "
				+ "AND face_photo.PhotoBase64 IS NOT NULL "
				+ "and face_photo.Person_id = " + personID + ";";
		DownloadAuthorityDataGson pi = null;
		System.out.println("sql = " + sql);
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pi = new DownloadAuthorityDataGson();
				pi.setUniqueCode(rs.getString("unique_code"));
				pi.setPersonType(rs.getInt("person_type"));
				pi.setPersonNo(rs.getString("person_no"));
				pi.setPersonName(rs.getString("person_name"));
				pi.setGender(rs.getInt("gender"));
				pi.setDptName(rs.getString("dpt_name"));
				pi.setIDType(Integer.toString(rs.getInt("id_type")));
				pi.setIDNo(rs.getString("Id_no"));
				pi.setCardNo(rs.getString("card_no"));
				pi.setIsLegal(Integer.toString(rs.getInt("is_legal")));
				pi.setFaceStartUseTime(rs.getString("face_start_use_time"));
				pi.setFaceStopUseTime(rs.getString("face_stop_use_time"));
				pi.setCardStartUseTime(rs.getString("card_start_use_time"));
				pi.setCardEndUseTime(rs.getString("card_end_use_time"));
				pi.setPhoto(rs.getString("PhotoBase64"));
				pi.setCharacter(rs.getString("feature"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return pi;
	}
	
	
	/**
	 * 分页查询，获取人员信息
	 * @param selectCount 一次查询selectCount 行
	 * @param start 取start后面的数据
	 * @return
	 */
	public  synchronized List<PersonInfo> listPersonInfo(int selectCount, int start) {
		//offset代表从第几条记录“之后“开始查询，limit表明查询多少条结果
		String sql = "select * from pearson_info order by Person_id limit " + selectCount + " offset " + start + ";";
//		System.out.println("sql = " + sql);
		List<PersonInfo> list = new ArrayList<PersonInfo>();
		PersonInfo pi = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pi = new PersonInfo();
				pi.setPersonID(rs.getInt("person_id"));
				pi.setUniqueCode(rs.getString("unique_code"));
				pi.setPersonType(rs.getInt("person_type"));
				pi.setPersonNo(rs.getString("person_no"));
				pi.setPersonName(rs.getString("person_name"));
				pi.setGender(rs.getInt("gender"));
				pi.setDptName(rs.getString("dpt_name"));
				pi.setIDType(Integer.toString(rs.getInt("id_type")));
				pi.setIDNo(rs.getString("Id_no"));
				pi.setCardNo(rs.getString("card_no"));
				pi.setIsLegal(Integer.toString(rs.getInt("is_legal")));
				pi.setFaceStartUseTime(rs.getString("face_start_use_time"));
				pi.setFaceStopUseTime(rs.getString("face_stop_use_time"));
				pi.setCardStartUseTime(rs.getString("card_start_use_time"));
				pi.setCardEndUseTime(rs.getString("card_end_use_time"));
				
				list.add(pi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取表person_info 的总行数
	 * @return
	 */
	public  synchronized int getPersonInfoCount() {
		//offset代表从第几条记录“之后“开始查询，limit表明查询多少条结果
		String sql = "select count(*) from pearson_info ;";
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 获取表person_info 的总行数
	 * @return
	 */
	public  synchronized int getDownLoadFeatureCount(String featureType) {
		//offset代表从第几条记录“之后“开始查询，limit表明查询多少条结果
		String sql = "select COUNT(f.person_id) from face_photo p, face_feature f where f.person_id = p.person_id and p.photo_url is not null and f.feature is null and f.feature_type = "+featureType+";";
		int count = 0;
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			count = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 
	 * @param tableName 表名
	 * @param fields 判断字段
	 * @param fields_data 值
	 * @param tab_fields 被更新字段
	 * @param tab_fields_data 值
	 */
	public  synchronized boolean updateDB (String tableName,List<String> fields,List<String> fields_data,List<String> tab_fields,List<String> tab_fields_data) {
		/*
		 * sql语句拼接
		 */
		String sql = "update " + tableName + " set ";
		int length = tab_fields.size();
	      for (int i=0;i<length;i++){
	        sql += tab_fields.get(i) + " =? ";
	        //防止最后一个,
	        if (i < length-1){
	          sql += ", ";
	        }
	      }
	      sql+=" where ";
	      length =  fields.size();
	      for (int i=0;i<length;i++){
	        sql += fields.get(i) + " =? ";
	        //防止最后一个,
	        if ( i < length-1){
	          sql+= " and ";
	        }
	      }
	      sql += ";";
	      System.out.println("updateDB = " + sql);
	      try {
			excutePreparedStatement(sql, tab_fields_data, fields_data);
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	public  synchronized  void updateDB (String sql) {
//		System.out.println(sql);
	      try {
	    	  ps = conn.prepareStatement(sql);
	    	  ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
		}
	}
	public  synchronized void deleteDataBase(String sql) {
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  synchronized  String getDevID (String macAdrr) {
		String devId = null;
		try {
			excutePreparedStatement("select dev_id from com_dev where dev_mac_address = ?;", Arrays.asList(macAdrr));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				devId = rs.getString("dev_id");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return devId;
	}
	
	/**
	 * 查找库中最大人员ID
	 * @return
	 */
	public  synchronized  int getPersonID () {
		int person_id = 0 ;
		try {
			ps = conn.prepareStatement("select max(person_id) from pearson_info;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				person_id = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person_id;
	}
	/**
	 * 根据虚拟ID查找personID
	 * @param unique_code
	 * @return
	 */
	public  synchronized  String getPersonID (String unique_code) {
		String person_id = null ;
		try {
			excutePreparedStatement("select person_id from pearson_info where unique_code = ?;", Arrays.asList(unique_code));
			ResultSet rs = ps.executeQuery();
			person_id = rs.getString(1);
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person_id;
	}
	/**
	 * 根据虚拟ID查找姓名
	 * @param unique_code
	 * @return
	 */
	public  synchronized  String getPersonName (String unique_code) {
		String person_id = null ;
		try {
			excutePreparedStatement("select person_name from pearson_info where unique_code = ?;", Arrays.asList(unique_code));
			ResultSet rs = ps.executeQuery();
			person_id = rs.getString(1);
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person_id;
	}
	/**
	 * insert into pearson_info(person_id, unique_code, person_type, person_no, person_name, gender, "
				+ "dpt_name, id_type, Id_no, card_no is_legal, face_start_use_time, face_stop_use_time, card_start_use_time, "
				+ "card_end_use_time, gmt_create, gmt_modified)
	 */
	public synchronized boolean insertIntoPersonInfo(List<PersonInfo> personInfoList) {
		StringBuffer sqlBuffer= new StringBuffer();
		sqlBuffer.append("insert into pearson_info(person_id, unique_code, person_type, person_no, person_name, gender, "
				+ "dpt_name, id_type, Id_no, card_no, is_legal, face_start_use_time, face_stop_use_time, card_start_use_time, "
				+ "card_end_use_time, gmt_create, gmt_modified, photo_url) ");
		String select = "select ";
		String comma = ", ";
		String unionAll = " union all ";
		for (PersonInfo personInfo : personInfoList) {
			sqlBuffer.append(select + getStringSplicing(Integer.toString(personInfo.getPersonID())) + comma + //personID 自增长
					getStringSplicing(personInfo.getUniqueCode()) + comma +
					getStringSplicing(Integer.toString(personInfo.getPersonType())) + comma +
					getStringSplicing(personInfo.getPersonNo()) + comma +
					getStringSplicing(personInfo.getPersonName()) + comma +
					getStringSplicing(Integer.toString(personInfo.getGender())) + comma +
					getStringSplicing(personInfo.getDptName()) + comma +
					getStringSplicing(personInfo.getIDType()) + comma +
					getStringSplicing(personInfo.getIDNo()) + comma +
					getStringSplicing(personInfo.getCardNo()) + comma +
					getStringSplicing(personInfo.getIsLegal()) + comma +
					getStringSplicing(personInfo.getFaceStartUseTime()) + comma +
					getStringSplicing(personInfo.getFaceStopUseTime()) + comma +
					getStringSplicing(personInfo.getCardStartUseTime()) + comma +
					getStringSplicing(personInfo.getCardEndUseTime()) + comma +
					getStringSplicing(SystemTimes.getSysTime()) + comma +  //gmt_create text,    --创建时间
					null + comma + // gmt_modified text,    --修改时间
					getStringSplicing(personInfo.getPhotoPath()) +
					unionAll);  
		}
		String sql = sqlBuffer.toString();
		sql = sql.substring(0, sql.length() - 11) + ";";
//		System.out.println("sql = " + sql);
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
			return false;
		}finally {
			sqlBuffer.setLength(0);
			sql = null;
		}
	}
//	/**
//	 * 批量更新表
//	 * @param tableName
//	 * @param ChangeColumnName
//	 * @param ChangeValueList
//	 * @param requirementColumnName
//	 * @param requirementValue
//	 */
//	public synchronized void updateDownLoadAuthorityToDeviceDelete(String devId, String ) {
//		StringBuffer sqlBuffer= new StringBuffer();
//		sqlBuffer.append("update " + tableName +" set ");
//		for (String st : ChangeValueList) {
//			sqlBuffer.append(b)
//		}
//	}
	
	public synchronized boolean insertIntoFacePhoto(List<FaceEmpPhoto> facePhotosList) {
		StringBuffer sqlBuffer= new StringBuffer();
		sqlBuffer.append("insert into face_photo(person_id, photo_url, PhotoBase64, gmt_create, gmt_modified) ");
		String select = "select ";
		String comma = ", ";
		String unionAll = " union all ";
		for (FaceEmpPhoto faceEmpPhoto : facePhotosList) {
			sqlBuffer.append(select + 
					getStringSplicing(Integer.toString(faceEmpPhoto.getPersonID())) + comma + 
					getStringSplicing(faceEmpPhoto.getPhotoUrl()) + comma + 
					getStringSplicing(faceEmpPhoto.getPhotoBase64()) + comma + 
					getStringSplicing(faceEmpPhoto.getGmtCreate()) + comma + 
					getStringSplicing(faceEmpPhoto.getGmtModified())+ 
					unionAll);
		}
		//删除多余"union all select "
		String sql = sqlBuffer.toString();
		sql = sql.substring(0, sql.length() - 11) + ";";
//		System.out.println("sql = " + sql);
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql = " + sql);
			return false;
		}finally {
			sqlBuffer.setLength(0);
			sql = null;
		}
	}
	
	/**
	* 添加数据
	* @param tabName 表名
	* @param fields 参数字段
	* @param data 参数字段数据
	*/
	public  synchronized  boolean insertIntoDB (String tableName, List<String> fields, List<String> data) {
		/*
		 * sql语句拼接
		 */
		String sql = "insert into " + tableName ;
		int length = 0;
		if (!fields.isEmpty()) {
			sql +=  "( ";
			length = fields.size();
			for(int i=0;i<length;i++){
				sql+=fields.get(i);
				//防止最后一个,
				if(i<length-1){
					sql += " , ";
				}
			}
			sql+=") " ;
			sql+=" values(";
			for(int i=0;i<length;i++){
				sql += "?";
				//防止最后一个,
				if(i<length-1){
					sql += ",";
				}
			}
		}else {
			length = data.size();
			sql+=" values(";
		    for(int i=0;i<length;i++){
		        sql += "?";
		        //防止最后一个,
		       if(i<length-1){
		          sql += " , ";
		       }
		    }
		}
		sql+=");";
//	    System.out.println(sql);  
	    try {
			excutePreparedStatement(sql, data);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
			return false;
			
		}
	}
	

	
	/**
	 	* 查询表 【查询结果的顺序要和数据库字段的顺序一致】
	 	* @param tabName 表名
	 	* @param fields 查询参数字段
	 	* @param data 查询参数字段数据
	 	* @param tab_fields 数据库中被查参数字段
	*/
	public  synchronized  List<String> listResult(String tabName,String field,String data,String tab_field) {
		List<String> resultList = new ArrayList<String>();
		
		try {
			String sql = "select " + tab_field + " from " + tabName ;
			if( "".equals(data)) {
				sql += ";";
				ps = conn.prepareStatement(sql);
			}else {
				sql += " where " + field + " = ?;";
				excutePreparedStatement(sql, Arrays.asList(data));
			}
//			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int length = rsmd.getColumnCount();
			while(rs.next()) {
				for( int i = 0 ; i < length; i++) {
					resultList.add(rs.getString(i + 1));
				}
			}
			rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
		}
		
		return resultList;
		
	}
	
	/**
	 	* 查询表 【查询结果的顺序要和数据库字段的顺序一致】
	 	* @param tabName 表名
	 	* @param fields 参数字段
	 	* @param data 参数字段数据
	 	* @param tab_fields 数据库的字段
	*/
	public  synchronized  List<List<String>> listResult(String tabName,List<String> fields,List<String> data,List<String> tab_fields) {
	
		String sql = "select ";
		List<List<String>> resultList = null;
		try {
			
			int length = tab_fields.size();
			for(int i = 0;  i< length; i++){
				sql += tab_fields.get(i);
		        //防止最后一个,
		        if(i < length-1){
		            sql+=", ";
		        }
		    }
			if(data.isEmpty()) {
				sql += " from " + tabName + " ;";
				ps = conn.prepareStatement(sql);
			}else {
				sql += " from " + tabName + " where ";
				
				length = fields.size();
				for(int i = 0;  i< length; i++){
					sql += fields.get(i) + " = ? ";
			        //防止最后一个,
			        if(i < length-1){
			            sql+="and ";
			        }
			    }
				sql += ";";
//				System.out.println("sql=" +sql);
				excutePreparedStatement(sql, data);
			}
			
			ResultSet rs = ps.executeQuery();
			resultList = new ArrayList<List<String>>();
			List<String> resultList_tp = null;
			resultList_tp = new ArrayList<String>();
			while(rs.next()) {
				resultList_tp = new ArrayList<String>();
				for ( int i = 0 ; i < tab_fields.size(); i++) {
					resultList_tp.add(rs.getString(tab_fields.get(i)));
				}
				resultList.add(resultList_tp);
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
		}
		return resultList;
	}
	
	/**
	 * 返回总数
	 * @param tableName 表名明
	 * @param fields 列名
	 * @param tab_fields_values 查询值
	 * @return
	 */
	public  synchronized int getCountResultData(String tableName, String fields, String tab_fields_values) {
		int count = 0;
		ResultSet rs = null;
		try {
//			System.out.println("select COUNT(*) from "+tableName+" where " + fields + " = '"+tab_fields_values+"';");
			ps = conn.prepareStatement("select COUNT(*) from "+tableName+" where " + fields + " = '"+tab_fields_values+"';");
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}
	/**
	 * 返回总数
	 * @param sql sql语句
	 * @return
	 */
	public  synchronized int getCountResultData(String sql) {
		int count = 0;
		ResultSet rs = null;
		try {
//			System.out.println("select COUNT(*) from "+tableName+" where " + fields + " = '"+tab_fields_values+"';");
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}
	public  synchronized List<List<String>> listResultData(String sql) {
		List<List<String>> list = new ArrayList<List<String>>();
		
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int rsSize = rs.getMetaData().getColumnCount();
			List<String> tempList = null;
			while (rs.next()) {
				tempList = new ArrayList<String>();
				for (int i = 1; i <= rsSize; i++) {
					tempList.add(rs.getString(i));
				}
				list.add(tempList);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public  synchronized List<DownloadAuthorityDataGson> listResult(String sql) {
		
		List<DownloadAuthorityDataGson> list = new ArrayList<DownloadAuthorityDataGson>();
		DownloadAuthorityDataGson pi = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pi = new DownloadAuthorityDataGson();
				pi.setUniqueCode(rs.getString("unique_code"));
				pi.setPersonType(rs.getInt("person_type"));
				pi.setPersonNo(rs.getString("person_no"));
				pi.setPersonName(rs.getString("person_name"));
				pi.setGender(rs.getInt("gender"));
				pi.setDptName(rs.getString("dpt_name"));
				pi.setIDType(Integer.toString(rs.getInt("id_type")));
				pi.setIDNo(rs.getString("Id_no"));
				pi.setCardNo(rs.getString("card_no"));
				pi.setIsLegal(Integer.toString(rs.getInt("is_legal")));
				pi.setFaceStartUseTime(rs.getString("face_start_use_time"));
				pi.setFaceStopUseTime(rs.getString("face_stop_use_time"));
				pi.setCardStartUseTime(rs.getString("card_start_use_time"));
				pi.setCardEndUseTime(rs.getString("card_end_use_time"));
				pi.setPhoto(rs.getString("PhotoBase64"));
				pi.setCharacter(rs.getString("feature"));
				list.add(pi);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return list;
	}
	
	public  synchronized  List<Map<String,String>> listResult_Map(String tabName,List<String> fields,List<String> data,List<String> tab_fields) {
		
		String sql = "select ";
		List<Map<String,String>> resultList = null;
		try {
			
			Map<String,String> resultMap = null;
			int length = tab_fields.size();
			for(int i = 0;  i< length; i++){
				sql += tab_fields.get(i);
		        //防止最后一个,
		        if(i < length-1){
		            sql+=", ";
		        }
		    }
			if(data.isEmpty()) {
				sql += " from " + tabName + " ;";
				ps = conn.prepareStatement(sql);
//				System.out.println("sql=" +sql);
			}else {
				sql += " from " + tabName + " where ";
				
				length = fields.size();
				for(int i = 0;  i< length; i++){
					sql += fields.get(i) + " = ? ";
			        //防止最后一个,
			        if(i < length-1){
			            sql+="and ";
			        }
			    }
				sql += ";";
//				System.out.println("sql=" +sql);
				excutePreparedStatement(sql, data);
			}
			
			ResultSet rs = ps.executeQuery();
			resultList = new ArrayList<Map<String,String>>();
			
			while(rs.next()) {
				resultMap = new HashMap<String, String>();
				for ( int i = 0 ; i < tab_fields.size(); i++) {
					resultMap.put(tab_fields.get(i), rs.getString(tab_fields.get(i)));
				}
				resultList.add(resultMap);
			}
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
		}
		return resultList;
	}
	
	public  synchronized  void cleanTable(String tableName) {
		try {
			String sql = "delete from " + tableName + ";";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql = "update sqlite_sequence SET seq = 0 where name ='"+tableName+"' ;";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 压缩数据库
	 */
	public  synchronized void getVacuum() {
		String sql = "vacuum";
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public  synchronized  String getRowCount(String tableName) {
		String sql = null;
		try {
			sql = "select count(*) from " + tableName + ";";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			sql = rs.getString(1);
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
		}
		return sql;
		
	}
	
	private  synchronized  Connection getDataBaseConnection() {
		try {
			Class.forName(classFroName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url + dbPath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			closeConnect();
			e.printStackTrace();
		}
		return conn;
	}
	
	private  void closeConnect() {
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ 数据库操作错误 ]："+e.getMessage());
			e.printStackTrace();
		}
		mOpenCounter.getAndDecrement();
	}
	
	private  void excutePreparedStatement(String sql , List<String> data) throws SQLException {
		ps = conn.prepareStatement(sql);
		int length = data.size();
		for(int i = 0; i < length; i++) {
			ps.setString(i + 1, data.get(i));
		}
	}
	
	private  void excutePreparedStatement(String sql , List<String> tab_fields_data ,List<String> fields_data) throws SQLException {
		ps = conn.prepareStatement(sql);
		
		int length1 = tab_fields_data.size();
		for(int i = 0; i < length1; i++) {
			ps.setString(i + 1, tab_fields_data.get(i));
		}
		int length2 = fields_data.size();
		for(int i = 0; i < length2; i++) {
			ps.setString(length1 + i + 1 , fields_data.get(i));
		}
	}
	/**
	 * 字符串拼接： str != null str = 'str';
	 * @param str
	 * @return
	 */
	private String getStringSplicing(String str) {
		if (null !=str) {
			
			str = "'" + str + "'";
		}
		return str;
	}

}
