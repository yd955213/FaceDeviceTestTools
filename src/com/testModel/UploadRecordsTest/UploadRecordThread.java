package com.testModel.UploadRecordsTest;

import java.io.IOException;

import com.google.gson.Gson;

import myGson.das.MyGson;
import myGson.das.SendGson;
import myGson.das.UploadRecordGson;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.LogsWriter;
import view.MainIntfaceView;

public class UploadRecordThread implements Runnable{
	private String macAddr;
	private String url ;
	private RecordID recordID ;
	private String uniqueCode;
	public UploadRecordThread(String url, String macAddr, RecordID recordID, String uniqueCode) {
		this.url = url + "/ServerApi/UploadRecords";
		this.macAddr = macAddr;
		this.recordID = recordID;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		UploadRecordGson uploadRecordJson = new UploadRecordGson();
		uploadRecordJson.setDeviceUniqueCode(macAddr);
		uploadRecordJson.setRecordID(recordID.getRecordId(macAddr));
		uploadRecordJson.setUniqueCode(uniqueCode);
		
		SendGson<UploadRecordGson> sg = new SendGson<UploadRecordGson>();
		sg.setDeviceUniqueCode(macAddr);
		sg.setData(uploadRecordJson);
		MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getRecordLabel_count());
		try {
			String recieveJson = OkHttpClientUtil.post(url, new Gson().toJson(sg, SendGson.class));
			if (new MyGson().validateJson(recieveJson)) {
				LogsWriter.writeUrlInfo(LogsWriter.UPLOAD_RECORDS, url, "设备【" + macAddr + "】上报人员【" + uniqueCode + "】记录号【" + recordID + "】记录成功");
				MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getRecordLabel_success());
			}else {
				LogsWriter.writeUrlInfo(LogsWriter.UPLOAD_RECORDS_ERR, url, "设备【" + macAddr + "】上报人员【" + uniqueCode + "记录号【" + recordID + "】记录失败：" + "\r\n" + recieveJson);
				MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getRecordLabel_fail());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogsWriter.writeUrlInfo(LogsWriter.UPLOAD_RECORDS_ERR, url, "设备【" + macAddr + "】上报人员【" + uniqueCode + "记录号【" + recordID + "】记录失败：" + "\r\n" + e.getMessage());
			MainIntfaceView.AddOneFromJLabel(MainIntfaceView.getRecordLabel_fail());
//			e.printStackTrace();
		}
	}
}
