package httpFrame.http.dasApi;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
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
	private static ReentrantLock lock = new ReentrantLock();
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
			List<DownloadAuthorityDataGson> personList = new DownLoadAuthority().listDownloadAuthorityDataGson(50, DownLoadAuthorityType.PHOTOURL_AND_FEATURE, MainIntfaceView.getDevInfo().get(deviceUniqueCode).getDevID());
			int size = personList.size();
			if (size > 0) {
				ConcurrentHashMap<String, String> photoUrlList = new ConcurrentHashMap<String, String>();
				synchronized (personList) {
					Iterator<DownloadAuthorityDataGson> iterator = personList.iterator();
					DownloadAuthorityDataGson downloadAuthorityDataGson = null;
					while (iterator.hasNext()) {
						lock.lock();
						downloadAuthorityDataGson = iterator.next();
						// 将文件地址存放着hashtable中，在类UploadAuthorityDealResult中，如果返回下载失败，则将照片文件写：下载失败照片文件夹
						photoUrlList.put(downloadAuthorityDataGson.getUniqueCode(), downloadAuthorityDataGson.getPhoto());
						//将人脸图片转换为base64字符串
						downloadAuthorityDataGson.setPhoto(MyPhoto.getPhoteBASE64_Mime(new File(downloadAuthorityDataGson.getPhoto())));
						lock.unlock();
					}
					downloadAuthorityDataGson = null;
					iterator = null;
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
				personList = null;
			}
		}
	}
}

