package mySocketClient.myHttpClient.tianmo350Api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import com.google.gson.reflect.TypeToken;

import myGson.NumberJsonSerializerString;
import myGson.tianmo350.DeviceConfig;
import myGson.tianmo350.Response;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import sqlite3.DataBaseExecute;
import sqlite3.DeviceExecute;
import sqlite3.table.FaceDeviceParameterTable;
import tools.MyString;
import tools.SystemTimes;
import view.MainIntfaceView;

public class GetDeviveParamate {
	
	public GetDeviveParamate(String parameterJsonString, String macAddr) {
		String url = OkHttpClientUtil.getUrl350(macAddr, "/basic/device/getDeviceInfo");
		
		try {
			MainIntfaceView.writeLogsTextArea("[RequestURL] " + url, parameterJsonString);
			String responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonString);
			if (MyString.isJsonStr(responseStr)) {
				Response<DeviceConfig> response = new NumberJsonSerializerString().createSerializeNulls().fromJson(
						responseStr, 
						new TypeToken<Response<DeviceConfig>>() {}.getType());
				
				updateFaceDevicePatamater(response.getData(), macAddr);
			}
			MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + responseStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
	}

	private void updateFaceDevicePatamater(DeviceConfig deviceConfig, String macAddr) {
		// TODO Auto-generated method stub
		FaceDeviceParameterTable faceDeviceParameterTable = new FaceDeviceParameterTable();
		faceDeviceParameterTable.devId = MainIntfaceView.getDevInfo().get(macAddr).getDevID();
		faceDeviceParameterTable.similityThreshold = Double.valueOf(deviceConfig.detectThreshold);
		/*
		 *好多字 懒得写库了，后人自己添加 
		 */
		
		try {
			//更新表 face_dev_parameter
			new DeviceExecute().updateDeviceParams(faceDeviceParameterTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//更新表 com_dev
		DataBaseExecute.getInstance().updateDB(
				"com_dev", 
				Arrays.asList("dev_mac_address"), 
				Arrays.asList(macAddr), 
				Arrays.asList("dev_sever_ip", "dev_sever_port", "gmt_modified"),
				Arrays.asList(deviceConfig.reqAddress, deviceConfig.reqPort, SystemTimes.getSysTime()));
	}
}
