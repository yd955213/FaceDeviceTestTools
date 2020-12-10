package mySocketClient.myHttpClient.tianmo350Api;

import java.io.IOException;
import com.google.gson.reflect.TypeToken;
import comm.myLibraryLoad.jvt.DeviceInfo;
import myGson.NumberJsonSerializerString;
import myGson.tianmo350.Response;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.MyString;
import view.MainIntfaceView;

public class GetDeviceInfo350 extends OkHttpClientUtil{
	public GetDeviceInfo350(String parameterJsonString, String macAddr) {
		String url = OkHttpClientUtil.getUrl350(macAddr, "/basic/device/getDeviceInfo");
		
		try {
			MainIntfaceView.writeLogsTextArea("[RequestURL] " + url, parameterJsonString);
			String responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonString);
			if (MyString.isJsonStr(responseStr)) {
				Response<DeviceInfo> response = new NumberJsonSerializerString().createSerializeNulls().fromJson(
						responseStr, 
						new TypeToken<Response<DeviceInfo>>() {}.getType());
				
				doSomeThing(response.getData());
			}
			
			MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + responseStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
		
	}
	
	private void doSomeThing(DeviceInfo deviceInfo) {
		//更新表 com_dev
//		DataBaseExecute.getInstance().updateDB(
//				"face_dev_parameter", 
//				Arrays.asList("dev_mac_address"), 
//				Arrays.asList(deviceInfo.deviceCode), 
//				Arrays.asList("app_pass_word", "dev_sever_port", "gmt_modified"),
//				Arrays.asList(deviceConfig.reqAddress, deviceConfig.reqPort, SystemTimes.getSysTime()));
			
	}
}
