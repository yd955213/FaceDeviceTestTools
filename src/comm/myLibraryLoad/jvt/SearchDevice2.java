package comm.myLibraryLoad.jvt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sqlite3.DataBaseExecute;
import sqlite3.table.ComDevTable;
import tools.SystemTimes;

public class SearchDevice2 {
	private List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
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
	 * 通过dll调用VideoNet_SearchDevice，获取设备Ip信息
	 */
	public void searchDeviceFromDll() {
		if (null != deviceInfoList || !deviceInfoList.isEmpty()) {
			deviceInfoList.clear();
		}else {
			deviceInfoList = new ArrayList<DeviceInfo>();
		}
		
		deviceInfoList = jvtDeviceDll.getSearchDeviceInfoList();
	}
	

	/**
	 * 根据用户名和密码登录
	 * @param userName
	 * @param passWord
	 */
	public List<DeviceInfo> searchAndLogin(String userName, String passWord) {
		searchDevice();
		
		/*
		 *搜索设备后会自动添加username、passWord的默认值
		 *这里修改下 
		 */
		for (int i = 0 ; i < deviceInfoList.size(); i++) {
			deviceInfoList.get(i).userName = userName;
			deviceInfoList.get(i).passWord = passWord;
		}

		LoginDevice();
		updateDevTableMap();
		return deviceInfoList;
	}
	
	public void searchDevice() {
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
			TestProcedure.updateJlabel( FactoryTest.WAIT_TIME/1000 + "秒未搜索到设备，请检查网络是否正常！！！重试次数：" + (++timeTemp));
		}
	}
	
	
	public void LoginDevice() {

		// TODO Auto-generated method stub
		/*
		 *  出厂设备:用户名：admin、密码默认 admin  dll搜索到的用户名和密码为空，需要手工添加
		 *  JvtDeviceDll.getSearchDeviceInfoList()方法已加入 用户和密码
		 */
		if (null != deviceInfoList && deviceInfoList.size() > 0) {
			deviceInfoList = JvtDeviceDll.getInstance().getLoginExDeviceInfoList(deviceInfoList);
		}
		
	}
	public List<DeviceInfo> getDeviceInfoList() {
		return deviceInfoList;
	}
	
}
