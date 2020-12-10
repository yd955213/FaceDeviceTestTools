package com.testModel.getCharacterTest;

import java.io.IOException;
import org.json.JSONObject;

import myGson.das.MyGson;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.LogsWriter;
import tools.SystemTimes;
import view.MainIntfaceView;

public class GetCharacterThread implements Runnable{

	private String macAddr ;
	private String url ;
	
	public GetCharacterThread(String url, String macAddr) {
		this.macAddr = macAddr;
		this.url = url + "/ServerApi/NoticeOfDownloadAuthorityData";
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IsReady", "Y");
		
		JSONObject sendJson = new JSONObject();
		sendJson.put("DeviceUniqueCode", macAddr);
		sendJson.put("TimeStamp", SystemTimes.getSysTime1());
		sendJson.put("Data", jsonObject);
		MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getCharacterLabel_count());
		try {
			String recievJson = OkHttpClientUtil.post(url,sendJson.toString());
			if (new MyGson().validateJson(recievJson)) {
				LogsWriter.writeUrlInfo(LogsWriter.DOWN_LOAD_AUTHORITY_DATA, url, "设备【" + macAddr + "】发送下发身份数据通知成功");
				MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getCharacterLabel_success());
			}else {
				LogsWriter.writeUrlInfo(LogsWriter.DOWN_LOAD_AUTHORITY_DATA_ERR, url, "设备【" + macAddr + "】发送下发身份数据通知失败" + "\r\n" + recievJson);
				MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getCharacterLabel_fail());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[连接失败]：" + e.getMessage() + "\t" + url);
			LogsWriter.writeUrlInfo(LogsWriter.DOWN_LOAD_AUTHORITY_DATA_ERR, url, "设备【" + macAddr + "】发送下发身份数据通知失败" + "\r\n" + e.getMessage());
			MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getCharacterLabel_fail());
		}
	}

}
