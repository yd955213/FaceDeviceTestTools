package myGson.das;

import com.testModel.TestParamInit;

import tools.SystemTimes;

public class DeviceHeartBeatTestGson {
	public String DeviceTime = SystemTimes.getSysTime1();
	public int AuthorityCount = 0;
	public int UnuploadRecordsCount = 0;
	public int DevicePort = TestParamInit.getDevPort();
	
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
