package httpFrame.http.dasApi;
import com.google.gson.reflect.TypeToken;
import httpFrame.http.HttpCmd;
import myGson.das.ResponseGson;
import view.MainIntfaceView;
/**
 * 3.7设备升级完成通知
 * @author yangdang
 *
 */
public class NoticeOfUpgradeApp extends HttpCmd{
	static {
		HttpCmd.register(SERVERAPI + NoticeOfUpgradeApp.class.getSimpleName(), NoticeOfUpgradeApp.class);
	}

	@Override
	public void execute() {

//		RequestGson<NoticeOfUpgradeAppGson> requestGson = gson.fromJson(getResponseJsonStr(), 
//				new TypeToken<RequestGson<NoticeOfUpgradeAppGson>>() {}.getType());
		MainIntfaceView.writeLogsTextArea("[RequestURL] " + requestUrl, getRequestJsonStr());
		ResponseGson<String> responseGson = new ResponseGson<String>();
		responseGson.setData(null);
		String requestStr = gson.toJson(responseGson, new TypeToken<ResponseGson<String>>() {}.getType());
		response(requestStr);

		MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + requestStr);
	}
}
