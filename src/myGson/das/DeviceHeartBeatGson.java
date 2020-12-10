package myGson.das;

import tools.SystemTimes;

public class DeviceHeartBeatGson {
	
	private String DeviceTime = SystemTimes.getSysTime1();
	private int AuthorityCount = 0;
	private int UnuploadRecordsCount = 0;
	private int DevicePort = 0;
	
	public String getDeviceTime() {
		return DeviceTime;
	}
	public void setDeviceTime(String deviceTime) {
		DeviceTime = deviceTime;
	}
	public int getAuthorityCount() {
		return AuthorityCount;
	}
	public void setAuthorityCount(int authorityCount) {
		AuthorityCount = authorityCount;
	}
	public int getUnuploadRecordsCount() {
		return UnuploadRecordsCount;
	}
	public void setUnuploadRecordsCount(int unuploadRecordsCount) {
		UnuploadRecordsCount = unuploadRecordsCount;
	}
	public int getDevicePort() {
		return DevicePort;
	}
	public void setDevicePort(int devicePort) {
		DevicePort = devicePort;
	}
		
}
