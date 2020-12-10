package com.testModel.OnlinePayRequestTest;

import java.io.IOException;

import com.DES.MySign;
import com.google.gson.Gson;
import com.testModel.TestParamInit;

import myGson.das.SendGson;
import myGson.das.consume.OnlinePayRequestSendGson;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import view.MainIntfaceView;

public class OnlinePayRequestThread implements Runnable {
	
	private String macAddr;
	private String url;
	
	public OnlinePayRequestThread(String url, String macAddr) {
		this.url = url;
		this.macAddr = macAddr;
	}
	
	public void run() {
		OnlinePayRequestSendGson oprsg = new OnlinePayRequestSendGson();
		oprsg.setDeviceMac(macAddr);
		oprsg.setDeviceNum(TestParamInit.getDevInfoMap().get(macAddr));
		Gson gson = new Gson();
		SendGson<OnlinePayRequestSendGson> sg = new SendGson<OnlinePayRequestSendGson>();
		sg.setDeviceUniqueCode(macAddr);
		sg.setData(oprsg);
		System.out.println(gson.toJson(sg, SendGson.class));
		
		sg.setSign(MySign.getMySign(gson.toJson(sg, SendGson.class), TestParamInit.getDevSecret().get(macAddr)));
		
		System.out.println(gson.toJson(sg, SendGson.class));
		
		try {
			OkHttpClientUtil.post(url, gson.toJson(sg, SendGson.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ERROR] "+ e.getMessage()+ "  "+ url);
//			e.printStackTrace();
		}
		
	}


}
