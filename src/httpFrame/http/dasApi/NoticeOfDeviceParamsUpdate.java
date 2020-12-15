package httpFrame.http.dasApi;
import com.google.gson.reflect.TypeToken;
import httpFrame.http.HttpCmd;
import myGson.das.DeviceParameterGson;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
import mySocketClient.myHttpClient.api.GetDeviceParams;
import tools.MyString;
import view.MainIntfaceView;
/**
 * 3.4设备参数更新通知
 * @author yangdang
 *
 */
public class NoticeOfDeviceParamsUpdate extends HttpCmd{
	static {
		HttpCmd.register(SERVERAPI + NoticeOfDeviceParamsUpdate.class.getSimpleName(), NoticeOfDeviceParamsUpdate.class);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*
		 * {
		 * "Data":
		 * {"DailyRestartTime":"02:00","DeviceName":"达实物联","DevicePort":0,"DeviceType":5,"DeviceUniqueCode":"0","EnableScreenSaver":1,"HeartBeatInterval":20000,"IsAutoRestart":2,"IsSupportCard":0,"IsUploadPassImg":0,"MainUIType":1,"QrCodeSwitch":0,"RelayTime":0,"ServerIP":"172.168.120.230","ServerPort":19000,"SystemID":"120123","TriggerActionInterval":3000,"WiegandIn":0,"WiegandOut":2,"WiegandType":34},
		 * "DeviceUniqueCode":"BB0934",
		 * "TimeStamp":"2020-05-22 10:25:28"}

		 */

		String requestStr = getRequestJsonStr();
		MainIntfaceView.writeLogsTextArea("[RequestURL] " + requestUrl, requestStr);
		if (MyString.isJsonStr(requestStr)) {
//			RequestGson<NoticeOfDeviceParamsUpdateGson> requestGson = gson.fromJson(requestStr, 
//					new TypeToken<RequestGson<NoticeOfDeviceParamsUpdateGson>>() {}.getType());

			RequestGson<DeviceParameterGson> requestGson = gson.fromJson(requestStr, 
					new TypeToken<RequestGson<DeviceParameterGson>>() {}.getType());
//			updateSql(requestGson.getData(), requestGson.getDeviceUniqueCode());
			
			try {
				GetDeviceParams.updateFaceDevParams(requestGson.getData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseGson<String> responseGson = new ResponseGson<String>();
			responseGson.setData(null);
			requestStr = gson.toJson(responseGson, new TypeToken<ResponseGson<String>>() {}.getType());
			response(requestStr);
			MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + requestStr);
			
		}
	}
}
