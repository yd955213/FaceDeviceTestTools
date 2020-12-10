package com.testModel.OnlinePayRequestTest;

import java.io.IOException;
import org.json.JSONObject;
import com.DES.MyDesSecretKey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testModel.TestParamInit;

import myGson.das.RequestGson;
import myGson.das.SendGson;
import myGson.das.consume.GetSecretRecieveGson;
import myGson.das.consume.GetSecretSendGson;
import mySocketClient.myHttpClient.OkHttpClientUtil;

public class GetSecretThread implements Runnable{
	private String macAddr;
	private String url;
	
	
	public GetSecretThread(String url, String macAddr) {
		this.macAddr = macAddr;
		this.url = url + "/ServerApi/GetSecret";
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		GetSecretSendGson getSecretSendGson = new GetSecretSendGson();
		getSecretSendGson.setSystemID(TestParamInit.getSystemID());
		getSecretSendGson.setOnlineCashNum("94");
		getSecretSendGson.setOnlineSecondCashNum("95");
		
		SendGson<GetSecretSendGson> sg = new SendGson<GetSecretSendGson>();
		sg.setDeviceUniqueCode(macAddr);
//		sg.setDeviceUniqueCode("FBABA6");
		sg.setData(getSecretSendGson);
		try {
			doRecieve(OkHttpClientUtil.post(url, new Gson().toJson(sg, new TypeToken<SendGson<GetSecretSendGson>>() {}.getType())));
		} catch (IOException e) {
//			 TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doRecieve(String recieveData) {
		RequestGson<GetSecretRecieveGson> resultGson = new RequestGson<GetSecretRecieveGson>();
		resultGson = new Gson().fromJson(recieveData, new TypeToken<RequestGson<GetSecretRecieveGson>>(){}.getType());
		
		if (JSONObject.NULL == resultGson.getData().getSecret() || resultGson.getData().getSecret().length() < 1) {
			//获取密钥为空，重新获取
			url = url.split("/ServerApi/")[0];
			new Thread(new GetSecretThread(url, macAddr)).start();
		}else {
//			System.out.println(resultGson.getData().getSecret());
//			System.out.println(resultGson.getData().getDesIV());
			MyDesSecretKey myDesSecretKey = new MyDesSecretKey();
			try {
				TestParamInit.getDevSecret().put(macAddr, 
						new String(
								myDesSecretKey.decrypt(
										myDesSecretKey.getBase64Decoded(resultGson.getData().getSecret()), 
								TestParamInit.getSystemID() + macAddr + resultGson.getData().getDesIV(), 
								resultGson.getData().getDesIV())));
				System.out.println("密钥 = " + TestParamInit.getDevSecret().get(macAddr));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		
	}
}
