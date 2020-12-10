package com.testModel.getCharacterTest;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.testModel.TestParamInit;
import httpFrame.http.HttpCmd;
import myGson.das.DownloadAuthorityDataGson;
import myGson.das.ResponseGson;
import myGson.das.SendGson;
import tools.MyString;
import tools.SystemTimes;


public class DownloadAuthorityData extends HttpCmd{
	
	static {
		HttpCmd.register(DEVAPI+DownloadAuthorityData.class.getSimpleName(), DownloadAuthorityData.class);
	}
	
	@Override
	public void execute() {  //接口业务逻辑处理方法

		String requestStr = getRequestJsonStr();
		if (MyString.isJsonStr(requestStr)) {
			SendGson<List<DownloadAuthorityDataGson>> sendGson = 
					gson.fromJson(requestStr, new TypeToken<SendGson<List<DownloadAuthorityDataGson>>>(){}.getType());
			String macAddr = sendGson.getDeviceUniqueCode();
			List<DownloadAuthorityDataGson> data = sendGson.getData();
			
			ResponseGson<String> responseGson = new ResponseGson<String>();
			responseGson.setCode("0");
			responseGson.setMsg("OK");
			responseGson.setTimeStamp(SystemTimes.getSysTime1());
			responseGson.setData(null);
			
		    response(gson.toJson(responseGson, ResponseGson.class));  //响应请求
		    
		    doUploadAuthorityDealResult(macAddr, data);
		}
	}
	
	/*
	 * 1、接受到服务器权限下载接口DownloadAuthorityData
	 * 2、获取一次下载的人员数
	 * 3、调用接口UploadAuthorityDealResult，按人依次答复后台已完成权限下载；
	 * 4、完成全部权限下载后，调用接口NoticeOfDownloadAuthorityData，重新请求权限
	 */
	private void doUploadAuthorityDealResult(String macAddr, List<DownloadAuthorityDataGson> data) {
		String url = TestParamInit.getUrl() + "/ServerApi/UploadAuthorityDealResult";
		String uniqueCode = null;
		String cardNo = null;
//		JSONObject jsonObject = null;
		Thread thread = null;
		UploadAuthorityDealResulThead uadrt = null;
		for (DownloadAuthorityDataGson downloadAuthorityDataGson : data) {
			uniqueCode = downloadAuthorityDataGson.getUniqueCode();
			cardNo = downloadAuthorityDataGson.getCardNo();
			uadrt = new UploadAuthorityDealResulThead(url, macAddr, uniqueCode, cardNo);
			thread = new Thread(uadrt);
			thread.start();
		}
		url = TestParamInit.getUrl();
		new Thread(new GetCharacterThread(url, macAddr)).start();
	}
}


