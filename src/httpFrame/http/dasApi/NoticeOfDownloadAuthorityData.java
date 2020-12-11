package httpFrame.http.dasApi;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import faceEngine.MyPhoto;
import httpFrame.http.HttpCmd;
import myGson.das.DownloadAuthorityDataGson;
import myGson.das.NoticeOfDownloadAuthorityDataGson;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
import myGson.das.SendGson;
import mySocketClient.myHttpClient.api.DownloadAuthorityData;
import sqlite3.DownLoadAuthority;
import sqlite3.DownLoadAuthorityType;
import tools.SystemTimes;
import tools.downLoadAuthority.DownLoadAuthorityStatu;
import view.MainIntfaceView;
/**
 * 3.6下发身份数据通知
 * @author yangdang
 *
 */
public class NoticeOfDownloadAuthorityData extends HttpCmd{

	private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	private static SendGson<List<DownloadAuthorityDataGson>> sendGson = new SendGson<List<DownloadAuthorityDataGson>>();
	private static TypeToken<SendGson<List<DownloadAuthorityDataGson>>> typeToken = new TypeToken<SendGson<List<DownloadAuthorityDataGson>>>(){};
	private static List<DownloadAuthorityDataGson> personList = null;
	
	static {
		HttpCmd.register(SERVERAPI + NoticeOfDownloadAuthorityData.class.getSimpleName(), NoticeOfDownloadAuthorityData.class);
	}

	@Override
	public void execute() {
		String getRequestJsonStr = getRequestJsonStr();
		RequestGson<NoticeOfDownloadAuthorityDataGson> requestGson = gson.fromJson(getRequestJsonStr, 
				new TypeToken<RequestGson<NoticeOfDownloadAuthorityDataGson>>() {}.getType());
		MainIntfaceView.writeLogsTextArea("[RequestURL] " + requestUrl, getRequestJsonStr);
		ResponseGson<String> responseGson = new ResponseGson<String>();
		responseGson.setData(null);
		String requestStr = gson.toJson(responseGson, new TypeToken<ResponseGson<String>>() {}.getType());
		response(requestStr);
		MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + requestStr);
		
		doSomething(requestGson.getData().getIsReady(), requestGson.getDeviceUniqueCode());
	}
	
	/**
	 * 进行权限下载
	 * @param isReady
	 * @param deviceUniqueCode
	 */
	private void doSomething(String isReady, String deviceUniqueCode) {
		// TODO Auto-generated method stub
		if ("Y".equals(isReady.toUpperCase())) {
			personList = new DownLoadAuthority().listDownloadAuthorityDataGson(50, DownLoadAuthorityType.PHOTOURL, MainIntfaceView.getDevInfo().get(deviceUniqueCode).getDevID());
			int size = personList.size();
			if (size > 0) {
				HashMap<String, String> photoUrlList = new HashMap<String, String>();
				String filePath = null;
				for (int j = 0; j < size; j ++) {
					filePath = personList.get(j).getPhoto();
					photoUrlList.put(personList.get(j).getUniqueCode(), filePath);
					personList.get(j).setPhoto(MyPhoto.getPhoteBASE64_Mime(new File(filePath)));
				}
				sendGson = new SendGson<List<DownloadAuthorityDataGson>>();
				sendGson.setDeviceUniqueCode(deviceUniqueCode);
				sendGson.setTimeStamp(SystemTimes.getSysTime1());
				sendGson.setData(personList);
				String tempStr= gson.toJson(sendGson, typeToken.getType());
				
				DownLoadAuthorityStatu.setPhotoUrlList(photoUrlList);
				// 发送数据
				new DownloadAuthorityData(tempStr, deviceUniqueCode).sendData();
				tempStr = null;
				sendGson = null;
				personList.clear();
			}
			
		}
	}
}

