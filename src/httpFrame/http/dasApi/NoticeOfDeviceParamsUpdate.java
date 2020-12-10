package httpFrame.http.dasApi;

import java.sql.SQLException;
import com.google.gson.reflect.TypeToken;
import httpFrame.http.HttpCmd;
import myGson.das.DeviceParameterGson;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
import mySocketClient.myHttpClient.api.GetDeviceParams;
import sqlite3.DeviceExecute;
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
				new DeviceExecute().updateDeviceParams(new GetDeviceParams().getFaceDeviceParameterTable(requestGson.getData()));
			} catch (SQLException e) {
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
//	public void updateSql (DeviceParameterGson dpg, String macAddr) {
//		try {
//			
//			List<String> fields = Arrays.asList("dev_mac_address");
//			List<String> fields_data = Arrays.asList(macAddr);
//			List<String> tab_fields = Arrays.asList("dev_name", "dev_sever_ip", "dev_port", "gmt_modified");
//			List<String> tab_fields_data = Arrays.asList(dpg.getBasicParams().getDeviceName(), dpg.getBasicParams().getServerIP(), Integer.toString(dpg.getBasicParams().getServerPort()), SystemTimes.getSysTime());
//			DataBaseExecute.getInstance().updateDB("com_dev", fields, fields_data, tab_fields, tab_fields_data);
//			
//			tab_fields= new ArrayList<String>();
//			tab_fields_data  = new ArrayList<String>();
//			
//			tab_fields.add("is_auto_restart");
//			tab_fields_data.add(Integer.toString(dpg.getBasicParams().getIsAutoRestart()));
//			tab_fields.add("daily_restart_time");
//			tab_fields_data.add(dpg.getBasicParams().getDailyRestartTime());
//			tab_fields.add("is_support_qrcode");
//			tab_fields_data.add(Integer.toString(dpg.getBasicParams().getQrCodeSwitch()));
//			tab_fields.add("is_support_card");
//			tab_fields_data.add(Integer.toString(dpg.getBasicParams().getIsSupportCard()));
//			tab_fields.add("heart_beat_interval");
//			tab_fields_data.add(Integer.toString(dpg.getBasicParams().getHeartBeatInterval()));
//			tab_fields.add("main_ui_type");
//			tab_fields_data.add(Integer.toString(dpg.getBasicParams().getMainUIType()));
//			tab_fields.add("simility_threshold");
//			tab_fields_data.add(dpg.getRecognitionParams().getSimilityThreshold());
//			tab_fields.add("quality_threshold");
//			tab_fields_data.add(dpg.getRecognitionParams().getQualityThreshold());
//			tab_fields.add("min_face_pixel");
//			tab_fields_data.add(Integer.toString(dpg.getRecognitionParams().getMinFacePixel()));
//			tab_fields.add("max_ace_ixel");
//			tab_fields_data.add(Integer.toString(dpg.getRecognitionParams().getMaxFacePixel()));
//			tab_fields.add("is_alive");
//			tab_fields_data.add(Integer.toString(dpg.getRecognitionParams().getIsAlive()));
//			tab_fields.add("living_threshold");
//			tab_fields_data.add(Double.toString(dpg.getRecognitionParams().getLivingThreshold()));
//			tab_fields.add("debug_mode_switch");
//			tab_fields_data.add(Integer.toString(dpg.getHardWareParams().getDebugModeSwitch()));
//			tab_fields.add("gmt_modified");
//			tab_fields_data.add(SystemTimes.getSysTime());
//			
//			
//			fields = Arrays.asList("dev_mac_address");
//			DataBaseExecute.getInstance().updateDB("face_dev_parameter", fields, fields_data, tab_fields, tab_fields_data);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
}
