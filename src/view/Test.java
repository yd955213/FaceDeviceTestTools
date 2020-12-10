package view;

import java.io.File;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import comm.myLibraryLoad.jvt.JvtDeviceDll;
import faceEngine.MyPhoto;
import myGson.das.GetCharacterGson;
import myGson.das.GetCharacterResponseGson;
import myGson.das.ResponseGson;
import myGson.das.SendGson;
import myGson.tianmo350.GetDeviceEmployeeForSyncResponse;
import mySocketClient.myHttpClient.api.GetCharacter;
import tools.SystemTimes;


public class Test {
	private static InetAddress anyLocalAddress;



	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("123", 123);
		map.put("23", 123);

		System.out.println(map.size());
		map.remove("23");
		System.out.println(map.size());
		map.remove("23");
		System.out.println(map.size());
//		System.out.println(MyPhoto.getPhoteBASE64_Mime(new File("E:\\02人脸测试111\\1人脸库\\Picture\\000243.jpg")));
//
//		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//		GetCharacterGson getCharacterGson = new GetCharacterGson();
//		SendGson<GetCharacterGson> sendGson = new SendGson<GetCharacterGson>();
//		
//		getCharacterGson.setUniqueCode("1");
//		getCharacterGson.setPhoto(MyPhoto.getPhoteBASE64_Mime(new File("E:\\02人脸测试111\\1人脸库\\Picture\\000243.jpg")));
//		
//		sendGson.setData(getCharacterGson);
//		sendGson.setDeviceUniqueCode("123123");
//		sendGson.setTimeStamp(SystemTimes.getSysTime1());
//		
//		System.out.println(gson.toJson(sendGson, new TypeToken<SendGson<GetCharacterGson>>() {}.getType()));
		
//data:image/jpg;base64,
//		String st = "{\"Code\":\"0\",\"Msg\":\"OK\",\"TimeStamp\":\"2020-09-16 17:46:54\",\"Data\":{\"BasicParams\":{\"DeviceUniqueCode\":\"10166747\",\"DeviceIP\":\"172.168.120.220\",\"DevicePort\":\"\",\"DeviceName\":\"IPC1000\",\"DeviceType\":2,\"ServerIP\":\"172.168.120.126\",\"ServerPort\":8026,\"IsAutoRestart\":2,\"DailyRestartTime\":\"00:00:00\",\"IsUploadPassImg\":2,\"RelayTime\":1000,\"TriggerActionInterval\":0,\"QrCodeSwitch\":0,\"IsSupportCard\":0,\"WiegandType\":0,\"MainUIType\":0,\"EnableScreenSaver\":0,\"HeartBeatInterval\":30000000},\"FeatureParams\":{\"FeatureType\":4,\"FeatureSDKValue\":\"FeatureSDKValue\",\"FeatureVersion\":\"2.4.3\"},\"RecognitionParams\":{\"SimilityThreshold\":\"75\",\"QualityThreshold\":\"99\",\"MinFacePixel\":80,\"MaxFacePixel\":500,\"IsAlive\":2},\"AppParams\":{\"AppVersion\":\"16.15.146.210\",\"AppPassword\":\"admin123\"},\"AccessParams\":{\"AccessDeviceIP\":\"12.168.11.201\",\"AccessDoorID\":0,\"AccessDeviceMacAddress\":\"\",\"UDPPort\":0},\"CameraParams\":{\"CameraMode\":0,\"BeautyScore\":0},\"HardWareParams\":{\"SuppleLightMode\":0,\"SuppleLightOpenTime\":\"\"}}}";
//		st = "{\"Msg\":\"OK\"}";
//		Gson gson = new GsonBuilder()
//				.registerTypeAdapter(Object.class, new JsonDeserializer<Object>() {
//
//					@Override
//					public Object deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
//							throws JsonParseException {
//						// TODO Auto-generated method stub
//						switch (json.getClass().getSimpleName()) {
//						case "Integer":
//							try {
//								return json.getAsInt();
//							} catch (Exception e) {
//								// TODO: handle exception
//								return -1;
//							}
//						case "Double":
//							try {
//								return json.getAsDouble();
//							} catch (Exception e) {
//								// TODO: handle exception
//								return -1;
//							}
//						default:
//							break;
//						}
//						return null;
//					}
//					
//				})
//				.create();
//		ResponseExGson<String> responseExGson = gson.fromJson(st, new TypeToken<ResponseExGson<String>>() {}.getType());
//		System.out.println(gson.fromJson(responseExGson.getTest().toString(), Double.class));
		
////		
//		Gson gson = new NumberJsonSerializerString().create();
//		DeviceInfo deviceInfo = new DeviceInfo();
////		deviceInfo.httpPort = 200D;
//		System.out.println("Code = " + Double.parseDouble("99999.1234567899923"));
//		try {
//			st = "{\"Code\":\"0\",\"Msg\":\"OK\",\"TimeStamp\":\"2020-09-18 14:58:57\",\"Data\":{\"BasicParams\":{\"DeviceUniqueCode\":\"1020585988\",\"DeviceIP\":\"192.168.1.88\",\"DevicePort\":8015,\"DeviceName\":\"工厂测试\",\"DeviceType\":2,\"ServerIP\":\"192.168.1.56\",\"ServerPort\":8026,\"IsAutoRestart\":2,\"DailyRestartTime\":\"02:00:00\",\"IsUploadPassImg\":1,\"RelayTime\":1000,\"TriggerActionInterval\":1000,\"QrCodeSwitch\":0,\"IsSupportCard\":0,\"WiegandType\":34,\"MainUIType\":0,\"EnableScreenSaver\":0,\"HeartBeatInterval\":20000},\"FeatureParams\":{\"FeatureType\":4,\"FeatureSDKValue\":\"FeatureSDKValue\",\"FeatureVersion\":\"3.5.4\"},\"RecognitionParams\":{\"SimilityThreshold\":\"75\",\"QualityThreshold\":\"80\",\"MinFacePixel\":30,\"MaxFacePixel\":500,\"IsAlive\":2},\"AppParams\":{\"AppVersion\":\"17.17.146.1\",\"AppPassword\":\"admin\"},\"AccessParams\":{\"AccessDeviceIP\":\"192.168.11.201\",\"AccessDoorID\":0,\"AccessDeviceMacAddress\":\"\",\"UDPPort\":0},\"CameraParams\":{\"CameraMode\":0,\"BeautyScore\":0},\"HardWareParams\":{\"SuppleLightMode\":2,\"SuppleLightOpenTime\":\"23:59-00:00\"}}}";
////		+ ",\"Msg\":\"OK\",\"TimeStamp\":\"2020-09-17 10:55:26\",\"Data\":{\"BasicParams\":{\"DeviceUniqueCode\":\"10166747\",\"DeviceIP\":\"172.168.120.220\",\"DevicePort\":8015,\"DeviceName\":\"工厂测试\",\"DeviceType\":2,\"ServerIP\":\"172.168.120.126\",\"ServerPort\":8026,\"IsAutoRestart\":2,\"DailyRestartTime\":\"02:00:00\",\"IsUploadPassImg\":1,\"RelayTime\":1000,\"TriggerActionInterval\":0,\"QrCodeSwitch\":0,\"IsSupportCard\":0,\"WiegandType\":0,\"MainUIType\":0,\"EnableScreenSaver\":0,\"HeartBeatInterval\":20000},\"FeatureParams\":{\"FeatureType\":4,\"FeatureSDKValue\":\"FeatureSDKValue\",\"FeatureVersion\":\"2.4.3\"},\"RecognitionParams\":{\"SimilityThreshold\":\"75\",\"QualityThreshold\":\"80\",\"MinFacePixel\":30,\"MaxFacePixel\":500,\"IsAlive\":2},\"AppParams\":{\"AppVersion\":\"16.15.146.210\",\"AppPassword\":\"admin123\"},\"AccessParams\":{\"AccessDeviceIP\":\"192.168.11.201\",\"AccessDoorID\":0,\"AccessDeviceMacAddress\":\"\",\"UDPPort\":0},\"CameraParams\":{\"CameraMode\":0,\"BeautyScore\":0},\"HardWareParams\":{\"SuppleLightMode\":0,\"SuppleLightOpenTime\":\"\"}}}";
//			ResponseExGson<DeviceParameterGson> responseExGson= 
//					new NumberJsonSerializerString().create().fromJson(st, new TypeToken<ResponseExGson<DeviceParameterGson>>() {}.getType());
//			System.out.println("Code = " + responseExGson.getData().getBasicParams().getDeviceUniqueCode());
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//		}
//		
		
//		
//		System.out.println(gson.toJson(responseExGson, new TypeToken<ResponseExGson<DeviceParameterGson>>() {}.getType()));
//		System.out.println(gson.fromJson("\"\"", Integer.class));
		
//		st = "{\"msg\":\"\", \"code\":\"5\"}";
//		System.out.println(new GsonBuilder().serializeNulls().create().toJson(new ResponseExGson<String>()));
//		ResponseExGson<String> responseExGson =  new NumberJsonSerializerString().create().fromJson(st, ResponseExGson.class);
//		System.out.println(responseExGson.getMsg());
//		responseExGson = new ResponseExGson<String>();
//		responseExGson.setCode(5);
//		System.out.println(new NumberJsonSerializerString().create().toJson(responseExGson, ResponseExGson.class));
		
//		System.out.println(getData("int", Integer.class));
		
//		System.out.println(Double.class.cast(5.99D));
		
//		System.out.println(list.isEmpty());
//		JvtDeviceDll.getInstance().getsomeTest();
		
//		Class<GetDeviceEmployeeForSyncResponse> cls = GetDeviceEmployeeForSyncResponse.class;
//		System.out.println("----------获取所有公有的字段----------");
//        Field[] fieldArray = cls.getFields();
//        for (Field f : fieldArray) {
//            System.out.println(f.getName());
//        }
//		JvtDeviceDll.getInstance().getsomeTest();
	}
	

	
	public static void todo(String st) {
		JSONObject jsonObject = new JSONObject(st).getJSONObject("Data");
		JSONObject BasicParams = jsonObject.getJSONObject("BasicParams");
		BasicParams.getString("DeviceUniqueCode");
		BasicParams.getString("DeviceIP");
		BasicParams.getInt("DevicePort");
		BasicParams.getString("DeviceName");
		BasicParams.getInt("DeviceType");
		BasicParams.getString("ServerIP");
		BasicParams.getInt("ServerPort");
		BasicParams.getString("SystemID");
		BasicParams.getInt("IsAutoRestart");
		BasicParams.getString("DailyRestartTime");
		BasicParams.getInt("IsUploadPassImg");
		BasicParams.getInt("RelayTime");
		BasicParams.getInt("TriggerActionInterval");
		BasicParams.getInt("QrCodeSwitch");
		BasicParams.getInt("IsSupportCard");
		BasicParams.getInt("WiegandType");
		BasicParams.getInt("WiegandIn");
		BasicParams.getInt("WiegandOut");
		BasicParams.getInt("MainUIType");
		BasicParams.getInt("EnableScreenSaver");
		BasicParams.getString("OpenDoorPassword");
		BasicParams.getInt("HeartBeatInterval");
		BasicParams.getInt("IsKqUse");
		JSONObject FeatureParams= jsonObject.getJSONObject("FeatureParams");
		FeatureParams.get("FeatureType");
		FeatureParams.getString("FeatureSDKValue");
		FeatureParams.getString("FeatureVersion");
		JSONObject RecognitionParams= jsonObject.getJSONObject("RecognitionParams");
		RecognitionParams.getString("SimilityThreshold");
		RecognitionParams.getString("QualityThreshold");
		RecognitionParams.getInt("MinFacePixel");
		RecognitionParams.getInt("MaxFacePixel");
		RecognitionParams.getInt("IsAlive");
		JSONObject AppParams= jsonObject.getJSONObject("AppParams");

		AppParams.getString("AppVersion");
		AppParams.getString("AppPassword");
		JSONObject AccessParams= jsonObject.getJSONObject("AccessParams");
		AccessParams.getString("AccessDeviceIP");
		AccessParams.getInt("AccessDoorID");
		AccessParams.getString("AccessDeviceUniqueCode");
		AccessParams.getInt("UDPPort");
		AccessParams.getInt("InOutFlag");
		JSONObject CameraParams= jsonObject.getJSONObject("CameraParams");
		CameraParams.getInt("CameraMode");
		CameraParams.getInt("BeautyScore");
		JSONObject HardWareParams= jsonObject.getJSONObject("HardWareParams");
		HardWareParams.getString("SuppleLightOpenTime");
		HardWareParams.getInt("SuppleLightMode");
		HardWareParams.getInt("DebugModeSwitch");
		JSONObject VoiceTipParams= jsonObject.getJSONObject("VoiceTipParams");
		VoiceTipParams.getString("BeforeJobTip");
		VoiceTipParams.getString("AfterJobTip"); //AfterJob
		VoiceTipParams.getString("RecognizeSuccessTip");
		VoiceTipParams.getString("RecognizeErrorTip");
		JSONObject ConsumeParams= jsonObject.getJSONObject("ConsumeParams");
		
		System.out.println(BasicParams.toString());
		System.out.println(FeatureParams.toString());
		System.out.println(RecognitionParams.toString());
		System.out.println(AppParams.toString());
		System.out.println(AccessParams.toString());
		System.out.println(CameraParams.toString());
		System.out.println(HardWareParams.toString());
		System.out.println(VoiceTipParams.toString());
		System.out.println(ConsumeParams.toString());
	}
	
}
