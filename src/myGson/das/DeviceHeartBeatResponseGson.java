package myGson.das;

import tools.SystemTimes;

public class DeviceHeartBeatResponseGson {
	public String getServerTime() {
		return ServerTime;
	}

	public void setServerTime(String serverTime) {
		ServerTime = serverTime;
	}

	public String ServerTime = SystemTimes.getSysTime1();
	
	
}
