package httpFrame.http.dasApi;

import mySocketClient.myHttpClient.OkHttpClientUtil;
import mySocketClient.myHttpClient.api.GetDeviceParams;
import sqlite3.DataBaseExecute;
import sqlite3.DeviceExecute;
import sqlite3.table.ComDevTable;
import tools.MyString;
import tools.SystemTimes;
import view.MainIntfaceView;
import java.sql.SQLException;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import httpFrame.http.HttpCmd;
import myGson.das.DevInfo;
import myGson.das.DeviceHeartBeatGson;
import myGson.das.DeviceHeartBeatResponseGson;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
/**
 * 3.1上报心跳
 * @author yangdang
 *
 */
public class DeviceHeartBeat extends HttpCmd {//http接口需要继承 HttpCmd
	public static String getDeviceUniqueCode() {
		return deviceUniqueCode;
	}
	Gson gson = new Gson();
	private ComDevTable comDevTable;
	private String ip = null;
	private String port = null;
	private static String deviceUniqueCode = null;
//	private static Map<String, ComDevTable> devTableMap = new HashMap<String, ComDevTable>();
	static {
		//注册接口 /ServerApi/DeviceHeartBeat
		HttpCmd.register(SERVERAPI+DeviceHeartBeat.class.getSimpleName(),DeviceHeartBeat.class);
	}

	@Override
	public void execute() {  //接口业务逻辑处理方法
		//读取post请求json参数并创建JSONObject
		
		String requestStr = getRequestJsonStr();
		MainIntfaceView.writeLogsTextArea("[RequestURL] " + requestUrl, requestStr);
		
		if (MyString.isJsonStr(requestStr)) {
			
			RequestGson<DeviceHeartBeatGson> requestGson = gson.fromJson(requestStr, new TypeToken<RequestGson<DeviceHeartBeatGson>>() {}.getType());
			deviceUniqueCode = requestGson.getDeviceUniqueCode();
			
			doSomeThing(requestGson);
			
			DeviceHeartBeatResponseGson deviceHeartBeatResponseGson = new DeviceHeartBeatResponseGson();
			deviceHeartBeatResponseGson.setServerTime(SystemTimes.getSysTime1());
			ResponseGson<DeviceHeartBeatResponseGson> responseGson = new ResponseGson<DeviceHeartBeatResponseGson>();
			responseGson.setCode("0");
			responseGson.setMsg("OK");
			responseGson.setTimeStamp(SystemTimes.getSysTime1());
			responseGson.setData(deviceHeartBeatResponseGson);
			requestStr = gson.toJson(responseGson, new TypeToken<ResponseGson<DeviceHeartBeatResponseGson>>() {}.getType());
			response(requestStr);  //响应请求
			
			MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + requestStr);
		}
	}
	
	private void doSomeThing(RequestGson<DeviceHeartBeatGson> requestGson) {
		ip = getServerIpInfo();
		port = Integer.toString(requestGson.getData().getDevicePort());
		
		/*
		 * 如果表face_dev_parameter没有该设备，则读取设备参数
		 * 当读取参数成功时，写表com_dev，face_dev_parameter
		 * 写库成功则devTableMap移除相应的设备，MainIntfaceView.getDevInfo()添加相应的设备信息
		 */
		String devID = null;
		if (!MainIntfaceView.getDevInfo().containsKey(deviceUniqueCode)) {
			comDevTable = new ComDevTable();
			comDevTable.devMacAddress = deviceUniqueCode;
			comDevTable.devIp = ip;
			comDevTable.devPort = Integer.parseInt(port);
			comDevTable.devIsUsed = "1";
			comDevTable.devIsOnline = "1";
			comDevTable.gmtCreate = SystemTimes.getSysTime1();
//			devTableMap.put(deviceUniqueCode, comDevTable);
			//写数据库
			try {
				devID = new DeviceExecute().insertIntoDevInfo(comDevTable);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//设置全局map
			DevInfo devInfo = new DevInfo();
			devInfo.setDevID(devID);
			devInfo.setMacAddr(comDevTable.devMacAddress);
			devInfo.setDevIP(comDevTable.devIp);
			devInfo.setDevPort(Integer.toString(comDevTable.devPort));
			devInfo.setFeature_type(null);
			MainIntfaceView.getDevInfo().put(comDevTable.devMacAddress, devInfo);
			
			comDevTable = null;
			//更新面板显示
			MainIntfaceView.updateComboBox(MyString.getMapKeyToList(MainIntfaceView.getDevInfo()));
			// 读取设备参数
			new Thread(()->{
				RequestGson<String> requestGson2 = new RequestGson<String>();
				requestGson2.setDeviceUniqueCode(deviceUniqueCode);
				requestGson2.setData(null);
				
				OkHttpClientUtil.setDevApiIP(ip);
				OkHttpClientUtil.setDevApiPort(Integer.parseInt(port));
				new GetDeviceParams(new GsonBuilder().serializeNulls().create().toJson(requestGson2, RequestGson.class), deviceUniqueCode);
			})
			.start(); 
			
		}else if(!MainIntfaceView.getDevInfo().get(deviceUniqueCode).getDevIP().equals(ip) ||
				!MainIntfaceView.getDevInfo().get(deviceUniqueCode).getDevPort().equals(port)) {
			/*
			 * 当设备上报的IP地址与端口MainIntfaceView.getDevInfo()中不一致时，更新
			 */
			boolean a = DataBaseExecute.getInstance().updateDB("com_dev", 
					Arrays.asList("dev_mac_address"), 
					Arrays.asList(requestGson.getDeviceUniqueCode()), 
					Arrays.asList("dev_ip", "dev_port"), 
					Arrays.asList(ip, port));
			
			if (a) {
				MainIntfaceView.getDevInfo().get(deviceUniqueCode).setDevID(ip);
				MainIntfaceView.getDevInfo().get(deviceUniqueCode).setDevPort(port);
			}
		}
	}
}
