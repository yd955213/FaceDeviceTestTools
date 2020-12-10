package myGson.das;

import com.google.gson.annotations.SerializedName;

import tools.SystemTimes;

public class RequestGson<T> {
	@SerializedName(value = "DeviceUniqueCode", alternate = {"DevMac", "DeviceMacAddress"})
    private String DeviceUniqueCodeEx;
    private String TimeStamp = SystemTimes.getSysTime1();
    private T Data ;
    
	public String getDeviceUniqueCode() {
		return DeviceUniqueCodeEx;
	}
	public void setDeviceUniqueCode(String deviceUniqueCode) {
		DeviceUniqueCodeEx = deviceUniqueCode;
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
