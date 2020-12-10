package com.testModel.heartBeatTest;

import java.io.IOException;

import com.google.gson.Gson;

import myGson.das.DeviceHeartBeatTestGson;
import myGson.das.SendGson1;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.LogsWriter;
import view.MainIntfaceView;

public class HeartBeatThread implements Runnable {
	private String macAddr ;
	private String url ;
	public HeartBeatThread(String url, String macAddr) {
		this.macAddr = macAddr;
		this.url = url + "/ServerApi/DeviceHeartBeat";
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SendGson1<DeviceHeartBeatTestGson> sg = new SendGson1<DeviceHeartBeatTestGson>();
		sg.setDeviceUniqueCode(macAddr);
		sg.setData(new DeviceHeartBeatTestGson());
		Gson gson = new Gson();
		try {
			OkHttpClientUtil.post(url, gson.toJson(sg, SendGson1.class));

			LogsWriter.writeUrlInfo(LogsWriter.GETHEARTBEAT, url, "设备【" + macAddr + "】上报心跳成功");
//			LogsWriter.writeInfo(LogsWriter.GETCHARACTERLOG, );
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[连接失败]："+e.getMessage() +"\t" + url);
		}
	}

}
