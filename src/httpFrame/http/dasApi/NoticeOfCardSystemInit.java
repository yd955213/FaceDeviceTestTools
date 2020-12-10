package httpFrame.http.dasApi;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import httpFrame.http.HttpCmd;
import myGson.das.NoticeOfCardSystemInitGsom;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
import sqlite3.DataBaseExecute;
import tools.MyString;
import tools.SystemTimes;
import view.MainIntfaceView;
/**
 * 3.5设备子卡初始化通知
 * @author yangdang
 *
 */
public class NoticeOfCardSystemInit extends HttpCmd{
	private String deviceUniqueCode = null;
	static {
		HttpCmd.register(SERVERAPI + NoticeOfCardSystemInit.class.getSimpleName(), NoticeOfCardSystemInit.class);
	}

	@Override
	public void execute() {

		String requestStr = getRequestJsonStr();
		MainIntfaceView.writeLogsTextArea("[RequestURL] " + requestUrl, requestStr);
		if (MyString.isJsonStr(requestStr)) {
			RequestGson<NoticeOfCardSystemInitGsom> requestGson = gson.fromJson(requestStr, 
					new TypeToken<RequestGson<NoticeOfCardSystemInitGsom>>() {}.getType());
			
			deviceUniqueCode = requestGson.getDeviceUniqueCode();
			insertIntoSql(requestGson.getData().getIsCardSystemInit());
			
			ResponseGson<String> responseGson = new ResponseGson<String>();
			responseGson.setCode("0");
			responseGson.setMsg("OK");
			responseGson.setTimeStamp(SystemTimes.getSysTime1());
			responseGson.setData(null);
			
			requestStr = new GsonBuilder().serializeNulls().create().toJson(responseGson, new TypeToken<ResponseGson<String>>() {}.getType());
			response(requestStr);
			MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + requestStr);
		}
	}
	
	public void insertIntoSql (String isCardSystemInit) {
		try {
			List<String> tab_fields = new ArrayList<String>();
			tab_fields.add("ssCard_system_init");
			tab_fields.add("gmt_modified");
			
			List<String> tab_fields_data = new ArrayList<String>();
			tab_fields_data.add(isCardSystemInit);
			tab_fields_data.add(SystemTimes.getSysTime());
			
			List<String> fields = new ArrayList<String>();
			fields.add("ssCard_system_init");
			
			List<String> data = new ArrayList<String>();
			data.add(deviceUniqueCode);
			DataBaseExecute.getInstance().updateDB("face_dev_parameter", fields, data, tab_fields, tab_fields_data);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
