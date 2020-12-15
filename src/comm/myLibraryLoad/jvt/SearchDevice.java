package comm.myLibraryLoad.jvt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sqlite3.DataBaseExecute;
import sqlite3.table.ComDevTable;
import tools.SystemTimes;

public class SearchDevice {
	private static List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
	public static final int DEVICE_PORT = 8015;
	private JvtDeviceDll jvtDeviceDll = JvtDeviceDll.getInstance();
	
	public void updateDevTableMap() {
		/*
		 * 获取到设备mac地址后
		 * 如果需要写数据库，则需要插入DeviceHeartBeat.getDevTableMap()，插入数据库时调用DeviceExecute。insertIntoDevInfo
		 */
		ComDevTable comDevTable = null;
		for (DeviceInfo deviceInfo:deviceInfoList) {
			comDevTable = new ComDevTable();
			comDevTable.devIsOnline = "1";
			comDevTable.devIp = deviceInfo.devIp;
			comDevTable.devMacAddress = deviceInfo.serialNumber;
			comDevTable.devPort = DEVICE_PORT;
			comDevTable.gmtCreate = SystemTimes.getSysTime1();
			
			try {
				DataBaseExecute.getInstance().insertIntoDevInfo(comDevTable);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据用户名和密码登录
	 * @param userName
	 * @param passWord
	 */
	public List<DeviceInfo> searchAndLogin(String userName, String passWord) {
		doSearch();
		
		/*
		 *搜索设备后会自动添加username、passWord的默认值
		 *这里修改下 
		 */
		for (int i = 0 ; i < deviceInfoList.size(); i++) {
			System.out.println(deviceInfoList.get(i).passWord);
			deviceInfoList.get(i).userName = userName;
			deviceInfoList.get(i).passWord = passWord;

			System.out.println(deviceInfoList.get(i).passWord);
		}

		doLogin();
		updateDevTableMap();
		return deviceInfoList;
	}
	/**
	 * 使用默认用户名和密码登录
	 */
	public List<DeviceInfo> searchDevice() {
		doSearch();
		doLogin();
		return deviceInfoList;
	}
	private void doSearch() {
		if (null != deviceInfoList || !deviceInfoList.isEmpty()) {
			deviceInfoList.clear();
		}
//		deviceInfoList = new ArrayList<DeviceInfo>();
		int timeTemp = 0 ;
		while(deviceInfoList.isEmpty() || deviceInfoList.size() < 0) {
			//getSearchDeviceInfoList方法有等待时间，这里有用一个死循环就行
			deviceInfoList = jvtDeviceDll.getSearchDeviceInfoList();
			if (!deviceInfoList.isEmpty() || deviceInfoList.size() > 0) {
				break;
			}
			FactoryTest.updateJlabel( FactoryTest.WAIT_TIME/1000 + "秒未搜索到设备，请检查网络是否正常！！！重试次数：" + (++timeTemp));
		}
	}
	
	
	private void doLogin() {

		// TODO Auto-generated method stub
		/*
		 *  出厂设备:用户名：admin、密码默认 admin  dll搜索到的用户名和密码为空，需要手工添加
		 *  JvtDeviceDll.getSearchDeviceInfoList()方法已加入 用户和密码
		 */
//		FactoryTest.updateJlabel("正在登录设备获取设备ID。。。");
		deviceInfoList = JvtDeviceDll.getInstance().getLoginExDeviceInfoList(deviceInfoList);
//		/*
//		 * deviceInfoList 当list中有设备登录失败时，deviceInfoTemp == null, 这里做一个判断。以前没有做这个判断，浪费3个小时找问题
//		 * 这是后期增加的删除null方法，程序前期做了很多非空判断，懒得去删代码了
//		 */
//		deviceInfoList = (List<DeviceInfo>) new MyString().removeListElement(deviceInfoList, null);
		//设备列表长度为0，表示设备全登录失败
//		if(deviceInfoList == null || deviceInfoList.isEmpty()) {
//			nextProcedureIsOk = false;
//		}else {
//			for (DeviceInfo deviceInfo : deviceInfoList) {
//				resultLogMap.put(deviceInfo.serialNumber, new StringBuffer());
//			}
//		}
	}
	
}
