package myGson.das;

import tools.SystemTimes;

public class SendGson<T> {
	private String DeviceUniqueCode;
	private String TimeStamp = SystemTimes.getSysTime1();
	private String Sign;
	private T Data;
	
	public String getSign() {
		return Sign;
	}
	public void setSign(String sign) {
		Sign = sign;
	}
	public String getDeviceUniqueCode() {
		return DeviceUniqueCode;
	}
	public void setDeviceUniqueCode(String deviceUniqueCode) {
		DeviceUniqueCode = deviceUniqueCode;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	public T getData() {
		return Data;
	}
	public void setData(T data) {
		Data = data;
	}
	
	
}
