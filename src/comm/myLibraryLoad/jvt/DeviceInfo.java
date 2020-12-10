package comm.myLibraryLoad.jvt;

import java.util.Vector;

public class DeviceInfo {
	public String devIp;
	public String hostName;
	public String mac;
	public String submask;
	public String gateway;
	public String serverIp;
	public int httpPort;
	public int tcpPort;
	public int sslPort;
	public int udpPort;
	public String userName;
	public String passWord;
	public String serialNumber;
	public String softWareVersion;
	public String hardWareVersion;
	public String encryptVersion;
	public String tmBuildTime;
	public String uiDeviceRunTime;
	public int deviceTye;
	public String hardWare;
	public String updataTime;
	public int loginID;
	
	public DeviceInfo() {
		
	}
	
	
	
	/**
	 * 与factoryTestTable的列名保持一致
	 * @return
	 */
	public Vector<String> toVector(){
		Vector<String> rowList = new Vector<String>();
		rowList.add(devIp);
		rowList.add(mac);
		rowList.add(serialNumber);
		rowList.add(userName);
		rowList.add(passWord);
		rowList.add(softWareVersion);
//		rowList.add(hardWareVersion);
		rowList.add(encryptVersion);
//		rowList.add(tmBuildTime);
//		rowList.add(hardWare);
		rowList.add(updataTime);
		rowList.add(Integer.toString(httpPort));
		return rowList;
	}
}
