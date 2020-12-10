package com.testModel.heartBeatTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testModel.TestParamInit;
import httpFrame.http.HttpCmd;
import myGson.das.DeviceParameterGson;
import myGson.das.ResponseGson;
import myGson.das.SendGson;
import tools.Ipv4FromLocal;
import tools.LogsWriter;
import tools.MyString;
import tools.SystemTimes;


public class ResponseGetDeviceParams extends HttpCmd{
	public static final String DEVICE_NAME_MJ = "DO";
	public static final String DEVICE_NAME_XF = "CO";
	private static String deviceName;
	private static String deviceType;
	static {
		HttpCmd.register(DEVAPI + "GetDeviceParams", ResponseGetDeviceParams.class);
	}
	
	@Override
	public void execute() {  //接口业务逻辑处理方法
		//收到获取设备参数请求

		String requestStr = getRequestJsonStr();
		if (MyString.isJsonStr(requestStr)) {
			SendGson<String> SendGson = 
					gson.fromJson(requestStr, new TypeToken<SendGson<String>>() {}.getType());
			String macAddr = SendGson.getDeviceUniqueCode();
			
			DeviceParameterGson deviceParameterGson = getDeviceParamsResponseGson(macAddr);
			ResponseGson<DeviceParameterGson> responseGson = new ResponseGson<DeviceParameterGson>();
			responseGson.setCode("0");
			responseGson.setMsg("OK");
			responseGson.setTimeStamp(SystemTimes.getSysTime1());
			responseGson.setData(deviceParameterGson);
			System.out.println( "DeviceParameterGson = " + new Gson().toJson(responseGson, 
		    		new TypeToken<ResponseGson<DeviceParameterGson>>() {}.getType()));
			
		    response(new Gson().toJson(responseGson, 
		    		new TypeToken<ResponseGson<DeviceParameterGson>>() {}.getType()));  //响应请求
	
			LogsWriter.writeUrlInfo(LogsWriter.GETHEARTBEAT, "http:/" + http.getRemoteAddress().toString() + http.getRequestURI().toString(), "设备【" + macAddr + "】返回读取参数成功");
		}
	}
	
	private DeviceParameterGson getDeviceParamsResponseGson(String macAddr) {
		DeviceParameterGson deviceParameterGson = new DeviceParameterGson();
		deviceParameterGson.getBasicParams().DeviceUniqueCode = macAddr;
		deviceParameterGson.getBasicParams().DeviceIP = new Ipv4FromLocal().getIpv4FromLocal().get(0);
		deviceParameterGson.getBasicParams().DeviceName = "TS";
		deviceParameterGson.getBasicParams().ServerIP = TestParamInit.getServerIP();
		deviceParameterGson.getBasicParams().SystemID = TestParamInit.getSystemID();
		deviceParameterGson.getBasicParams().DailyRestartTime = "02:00:00" ;
		deviceParameterGson.getBasicParams().OpenDoorPassword = "123456";
		deviceParameterGson.getBasicParams().DevicePort = TestParamInit.getDevPort();
		deviceParameterGson.getBasicParams().DeviceType = 4;
		deviceParameterGson.getBasicParams().ServerPort = TestParamInit.getServerProt();
		deviceParameterGson.getBasicParams().IsAutoRestart = 2;
		deviceParameterGson.getBasicParams().IsUploadPassImg = 2;
		deviceParameterGson.getBasicParams().RelayTime = 5000;
		deviceParameterGson.getBasicParams().TriggerActionInterval = 1000;
		deviceParameterGson.getBasicParams().QrCodeSwitch = 2;
		deviceParameterGson.getBasicParams().IsSupportCard = 1;
		deviceParameterGson.getBasicParams().WiegandType = 34;
		deviceParameterGson.getBasicParams().WiegandIn = 0;
		deviceParameterGson.getBasicParams().WiegandOut = 2;
		deviceParameterGson.getBasicParams().MainUIType = 1;
		deviceParameterGson.getBasicParams().EnableScreenSaver = 1;
		deviceParameterGson.getBasicParams().HeartBeatInterval = 20*1000;
		
		deviceParameterGson.getFeatureParams().FeatureType = 3;
		deviceParameterGson.getFeatureParams().FeatureSDKValue = "SDK-2.2.8";
		deviceParameterGson.getFeatureParams().FeatureVersion = "FW-1.8.6-03.04.00";
		
		deviceParameterGson.getRecognitionParams().SimilityThreshold = "75.00";
		deviceParameterGson.getRecognitionParams().QualityThreshold = "80.00" ;
		deviceParameterGson.getRecognitionParams().MinFacePixel = 30;
		deviceParameterGson.getRecognitionParams().MaxFacePixel = 1000;
		deviceParameterGson.getRecognitionParams().IsAlive = 2 ;
		deviceParameterGson.getRecognitionParams().LivingThreshold = 99.999 ;
		
		deviceParameterGson.getAppParams().AppVersion = "361.2.5";
		deviceParameterGson.getAppParams().AppPassword= "999999";
		deviceParameterGson.getAccessParams().AccessDeviceIP = "192.168.11.201";
		deviceParameterGson.getAccessParams().AccessDeviceUniqueCode = "HENHJ0S";
		deviceParameterGson.getAccessParams().AccessDoorID = 0;
		deviceParameterGson.getAccessParams().UDPPort = 100;
		
		deviceParameterGson.getCameraParams().CameraMode = 0;
		deviceParameterGson.getCameraParams().BeautyScore = 80;
		
		deviceParameterGson.getHardWareParams().SuppleLightOpenTime = "18:00-07:00";
		deviceParameterGson.getHardWareParams().SuppleLightMode = 2;
		deviceParameterGson.getHardWareParams().DebugModeSwitch = 3;
		
		deviceParameterGson.getVoiceTipParams().AttendanceTime = "09:00-18:00";
		deviceParameterGson.getVoiceTipParams().BeforeJobTipEx= "@打卡成功";
		deviceParameterGson.getVoiceTipParams().AfterJobEx = "@上班一天辛苦了";
		deviceParameterGson.getVoiceTipParams().RecognizeSuccessTipEx = "识别成功";
		deviceParameterGson.getVoiceTipParams().RecognizeErrorTipEx = "未登记";
		
		deviceParameterGson.getConsumeParams().PayUrlEx = "172.168.120.11:21000";
		deviceParameterGson.getConsumeParams().QueryUrlEx = "172.168.120.12:80";
		deviceParameterGson.getConsumeParams().DeviceNum = TestParamInit.getDevInfoMap().get(macAddr) ;
		deviceParameterGson.getConsumeParams().SuccessInfoDuration = 2;
		deviceParameterGson.getConsumeParams().DisplayTitle = "欢迎使用达实物联消费系统";
		deviceParameterGson.getConsumeParams().BreakfastTime = "09:00-10:00";
		deviceParameterGson.getConsumeParams().LunchTime = "11:00-13:00";
		deviceParameterGson.getConsumeParams().SupperTime = "23:00-2:00";
		deviceParameterGson.getConsumeParams().DinnerTime = "18:00-20:00";
		deviceParameterGson.getConsumeParams().BreakfastAmount = "6.50";
		deviceParameterGson.getConsumeParams().LunchAmount = "18.50";
		deviceParameterGson.getConsumeParams().SupperAmount = "20.00";
		deviceParameterGson.getConsumeParams().DinnerAmount = "12.00";
		deviceParameterGson.getConsumeParams().IsCardSystemInit = 0;
		deviceParameterGson.getConsumeParams().OnlineWalletID1 = 94;
		deviceParameterGson.getConsumeParams().OnlineWalletID2 = 95;
		deviceParameterGson.getConsumeParams().OfflineMoneyLimit = 500;
		deviceParameterGson.getConsumeParams().OfflineNumberLimit = 50;
		deviceParameterGson.getConsumeParams().OfflineTimeLimit = 600;
		deviceParameterGson.getConsumeParams().OfflineMode = 1;
		deviceParameterGson.getConsumeParams().ConsumeMode = 0;
		
		return deviceParameterGson;
	}
	public static String getDeviceName() {
		return deviceName;
	}

	public static void setDeviceName(String deviceName) {
		ResponseGetDeviceParams.deviceName = deviceName;
	}

	public static String getDeviceType() {
		return deviceType;
	}

	public static void setDeviceType(String deviceType) {
		ResponseGetDeviceParams.deviceType = deviceType;
	}
}


