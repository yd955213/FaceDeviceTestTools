package mySocketClient.myHttpClient.api;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import myGson.das.DeviceParameterGson;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import sqlite3.DataBaseExecute;
import tools.MyString;
import view.MainIntfaceView;

public class SetDeviceParams extends OkHttpClientUtil {
	
	public SetDeviceParams(String parameterJsonStr, String macAddr) {
		
		String url = getUrl(macAddr);
		url += SetDeviceParams.class.getSimpleName();
		
		try {
			
			String responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonStr);
			if (MyString.isJsonStr(responseStr)) {
				Gson gson = new GsonBuilder().serializeNulls().create();
				@SuppressWarnings("unchecked")
				ResponseGson<String> responseGson = gson.fromJson(responseStr, ResponseGson.class);
				if (getRequest_OK(responseGson)) {		
					
					if ("0".equals(responseGson.getCode())) {
						requestOk = true;
					}else {
						requestOk = false;
					}
					/*
					 * 序列化传入参数，
					 * 修改数据库
					 */
					RequestGson<DeviceParameterGson> requestGson = gson.fromJson(parameterJsonStr, new TypeToken<RequestGson<DeviceParameterGson>>() {}.getType());
					requestGson.getData().getBasicParams().DeviceUniqueCode = macAddr;
					//改表 face_dev_parameter
					new GetDeviceParams().updateFaceDevParams(requestGson.getData());
					//改表 com_dev
					DataBaseExecute.getInstance().updateDB(
							"com_dev", 
							Arrays.asList("dev_mac_address"), 
							Arrays.asList(macAddr), 
							Arrays.asList("dev_ip", "dev_name", "dev_sever_ip", "dev_sever_port"), 
							Arrays.asList(requestGson.getData().getBasicParams().DeviceIP, requestGson.getData().getBasicParams().DeviceName, 
									requestGson.getData().getBasicParams().ServerIP, Integer.toString(requestGson.getData().getBasicParams().ServerPort)));
				}else {
					if (null != responseGson) {
						parameterJsonStr = responseGson.getMsg();
					}else {
						parameterJsonStr = "Json反序列化识别，请检查传入字段";
					}
//					JOptionPane.showMessageDialog(null, "参数设置失败:" + parameterJsonStr, "设置设备信息", JOptionPane.ERROR_MESSAGE);
					requestOk = false;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
		
	}
	

}
