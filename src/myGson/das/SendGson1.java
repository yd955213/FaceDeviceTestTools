package myGson.das;

import tools.SystemTimes;

public class SendGson1<T> {
	private String DeviceUniqueCode;
	private String TimeStamp = SystemTimes.getSysTime1();
	private T Data;
	
	public String getDeviceUniqueCode() {
		return DeviceUniqueCode;
	}
	public void setDeviceUniqueCode(String DeviceUniqueCode) {
		this.DeviceUniqueCode = DeviceUniqueCode;
	}
	public T getData() {
		return Data;
	}
	public void setData(T Data) {
		this.Data = Data;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	
	
}
