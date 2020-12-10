package httpFrame.http.readSense;

import java.util.HashMap;
import java.util.Map;

import httpFrame.http.HttpCmd;
import myGson.readSense.DeviceBindGson;
import myGson.readSense.DeviceBindGson.ResultTid;
import tools.MyString;
import myGson.readSense.HardwareIdGson;

public class DeviceBind extends HttpCmd {
	private static Map<String , String> devMap = new HashMap<String , String>();
	private final static String urlName = "/api/v1/device";
	static {
		//注册接口 /ServerApi/DeviceHeartBeat
		HttpCmd.register(urlName, DeviceBind.class);
	}
	
	public void execute() {  //接口业务逻辑处理方法

		String requestStr = getRequestJsonStr();
		if (MyString.isJsonStr(requestStr)) {
			HardwareIdGson hardwareIdGson = gson.fromJson(requestStr,HardwareIdGson.class);
			String devId = Long.toString(System.currentTimeMillis());
			if (!devMap.containsKey(hardwareIdGson.getHardwareId())) {
				DeviceBindGson.ResultTid tid = new ResultTid();
				tid.setTid(devId);
				DeviceBindGson deviceBindGson = new DeviceBindGson();
				deviceBindGson.setResult(tid);
				System.out.println("返回数据：" + gson.toJson(deviceBindGson, DeviceBindGson.class));
				response(gson.toJson(deviceBindGson, DeviceBindGson.class));
				devMap.put(hardwareIdGson.getHardwareId(), devId);
			}
		}
	}
	
	
}
