package com.testModel.OnlinePayRequestTest;

import java.io.IOException;

import com.DES.MySign;
import com.google.gson.Gson;
import com.testModel.TestParamInit;

import myGson.das.SendGson;
import myGson.das.consume.OfflinePayRequestSendGson;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.MyString;
import tools.SystemTimes;
import view.MainIntfaceView;

public class OffLinePayRequestThread implements Runnable {
	
	private String macAddr;
	private String url;
	private String uniqueCode;
	
	public OffLinePayRequestThread(String url, String macAddr, String uniqueCode) {
		this.url = url;
		this.macAddr = macAddr;
		this.uniqueCode = uniqueCode;
	}
	
	public void run() {
		OfflinePayRequestSendGson oprsg = new OfflinePayRequestSendGson();
		oprsg.setOfflineConsumeData(getInitString());
		Gson gson = new Gson();
		
		SendGson<OfflinePayRequestSendGson> sg = new SendGson<OfflinePayRequestSendGson>();
		sg.setDeviceUniqueCode(macAddr);
		sg.setData(oprsg);
		
		sg.setSign(MySign.getMySign(gson.toJson(sg, SendGson.class), TestParamInit.getDevSecret().get(macAddr)));
		
		
		try {
			OkHttpClientUtil.post(url, gson.toJson(sg, SendGson.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[ERROR] "+ e.getMessage()+ "  "+ url);
//			e.printStackTrace();
		}
		
	}

	public String getInitString() {
		// TODO Auto-generated method stub
		String data = "AUTO" + macAddr + "AD" + TestParamInit.getSystemID() + "AAFFXF40";
		
		String values = MyString.getFullString_l(MyString.getHigthToLow(Integer.toHexString(TestParamInit.getRecordNum())), "0", 8) + 	//记录号
				MyString.getFullString_l(MyString.getHigthToLow(Integer.toHexString(Integer.parseInt(uniqueCode))), "0", 6) +		//流水号
				MyString.getFullString_l(MyString.getHigthToLow(Integer.toHexString(Integer.parseInt(uniqueCode))), "0", 8) +		//卡号或者虚拟ID号
				"E8030000" + 	//消费额 10.00元
				new SystemTimes().getSysTimeYYMMDDHHMMSS() + 		//消费时间，未转化为二进制码
				"2FFFFF" + 		//验证方式:0---卡片；1---二维码；2---人脸
				"00" + 		//操作员
				"00" +  	//模 式
				"00" + 		//状态
				macAddr +  	//上次消费地址 (注:361消费机使用本机设备号)
				"FF" ; 		//记录状态标志：(361人脸消费机传递FF)
		values += SumCheck.getBCC(values.getBytes());
		
		data += values + "OOK";
		data += SumCheck.getSumCheck(data);
		
		return data.toUpperCase();
	}


}
