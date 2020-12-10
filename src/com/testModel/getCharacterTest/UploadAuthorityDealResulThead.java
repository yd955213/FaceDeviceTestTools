package com.testModel.getCharacterTest;

import java.io.IOException;
import org.json.JSONObject;

import myGson.das.MyGson;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.LogsWriter;
import tools.SystemTimes;
import view.MainIntfaceView;

public class UploadAuthorityDealResulThead implements Runnable{
	private String url;
	private String macAddr;
	private String uniqueCode;
	private String cardNo;
	private JSONObject dataJson, sendJson;
	public UploadAuthorityDealResulThead(String url, String macAddr, String uniqueCode, String cardNo) {
		this.url = url;
		this.macAddr = macAddr;
		this.uniqueCode = uniqueCode;
		this.cardNo = cardNo;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		dataJson = new JSONObject();
		dataJson.put("Code", "0");
		dataJson.put("Msg", "OK");
		dataJson.put("UniqueCode", uniqueCode);
		dataJson.put("CardNo", cardNo);
		dataJson.put("StartTime", SystemTimes.getSysTime1());
		dataJson.put("IsLegal", "Y");
		
		sendJson = new JSONObject();
		sendJson.put("DeviceUniqueCode", macAddr);
		sendJson.put("TimeStamp", SystemTimes.getSysTime1());
		sendJson.put("Data", dataJson);
		MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getCharacterLabel_count());
		try {
			String recievJson = OkHttpClientUtil.post(url,sendJson.toString());
			if (new MyGson().validateJson(recievJson)) {
				LogsWriter.writeUrlInfo(LogsWriter.DOWN_LOAD_AUTHORITY_DATA, url, "设备【" + macAddr + "】上报身份数据处理结果： " + uniqueCode+ " 已下载成功");
	//			LogsWriter.writeInfo(LogsWriter.GETCHARACTERLOG, sendJson.toString());
				MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getCharacterLabel_success());
			}else {
				LogsWriter.writeUrlInfo(LogsWriter.DOWN_LOAD_AUTHORITY_DATA_ERR, url, "设备【" + macAddr + "】上报身份数据处理结果失败" + "\r\n" + recievJson);
				MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getCharacterLabel_fail());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[连接失败]："+ e.getMessage() +"\t" + url);
			LogsWriter.writeUrlInfo(LogsWriter.DOWN_LOAD_AUTHORITY_DATA_ERR, url, "设备【" + macAddr + "】上报身份数据处理结果失败" + "\r\n" + e.getMessage());
			MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getCharacterLabel_fail());
		}
	}

}
