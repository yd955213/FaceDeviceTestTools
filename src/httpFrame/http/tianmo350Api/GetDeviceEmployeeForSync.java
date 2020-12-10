package httpFrame.http.tianmo350Api;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import com.google.gson.Gson;
import httpFrame.http.HttpCmd;
import myGson.das.DevInfo;
import myGson.tianmo350.DeviceCode;
import myGson.tianmo350.Response;
import mySocketClient.myHttpClient.tianmo350Api.GetDeviceInfo350;
import sqlite3.DataBaseExecute;
import sqlite3.DeviceExecute;
import sqlite3.table.ComDevTable;
import tools.SystemTimes;
import view.MainIntfaceView;

/**
 * 人员同步（此接口也可单独做为心跳接口使用）
 * @author yangdang
 *
 */
public class GetDeviceEmployeeForSync extends HttpCmd  {
	static {
		//注册接口 
		HttpCmd.register("/basic/Employee/GetDeviceEmployeeForSync", GetDeviceEmployeeForSync.class);
	}
	
	@Override
	/*
	 * 接口业务逻辑处理方法
	 */
	public void execute() {

		//接收到的请求数据
		JSONObject requestStrJson = null;
		try {
			requestStrJson = new JSONObject(getDecodeParams());

			MainIntfaceView.writeLogsTextArea("[RequestURL] " + requestUrl, requestStrJson.toString());
//			Iterator<Entry<String, String>> iterator = requestStr.entrySet().iterator();
//			Entry<String, String> entry = null;
//			while(iterator.hasNext()) {
//				entry = iterator.next();
//				System.out.println(entry.getKey() + "  " + entry.getValue());
//			}
			String deviceUniqueCode = requestStrJson.getString("Id");
			if (!MainIntfaceView.getDevInfo().containsKey(deviceUniqueCode)) {
				DevInfo devInfo = new DevInfo();
				devInfo.setMacAddr(deviceUniqueCode);
				devInfo.setDevIP(getServerIpInfo());
				devInfo.setDevPort("8090");
				devInfo.setFeature_type("350");
				devInfo.setDevModel("350");
				//写设备库，并返回设备ID
				devInfo.setDevID(insertIntoDb(devInfo));
				MainIntfaceView.getDevInfo().put(deviceUniqueCode, devInfo);
				
				doGetDeviceInfo(deviceUniqueCode);
				doGetDeviveParamate(deviceUniqueCode);
			}
			
			Response<List<String>> response = new Response<List<String>>();
			response.setData(new ArrayList<String>());
			response.setMsg("success");
			response.setCode(0);
			
			response(gson.toJson(response, Response.class));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Response<List<String>> response = new Response<List<String>>();
			response.setData(Arrays.asList(e.getMessage()));
			response.setMsg("Error");
			response.setCode(1);
			
			response(gson.toJson(response, Response.class));
		}
		
	}
	public String insertIntoDb(DevInfo devInfo) {
		ComDevTable comDevTable = new ComDevTable();
		comDevTable.devMacAddress = devInfo.getMacAddr();
		comDevTable.devIp = devInfo.getDevID();
		comDevTable.devPort = Integer.parseInt(devInfo.getDevPort());
		comDevTable.devIsUsed = "1";
		comDevTable.devIsOnline = "1";
		comDevTable.gmtCreate = SystemTimes.getSysTime1();
		//写数据库
		String devID = null;
		try {
			devID = new DeviceExecute().insertIntoDevInfo(comDevTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return devID;
	}
	/**
	 * 获取设备参数信息
	 */
	public void doGetDeviveParamate(String deviceId){
		
	}
	/**
	 * 获取获取设备信息（固件、版本）
	 */
	public void doGetDeviceInfo(String deviceId){
		DeviceCode deviceCode = new DeviceCode();
		deviceCode.setDeviceCode(deviceId);
		new GetDeviceInfo350(new Gson().toJson(deviceCode, DeviceCode.class), deviceId);
	}
	/**
	 * 获取可下载的人员信息，下载权限
	 */
	public void doGetAuthority(String deviceId){
		int authoritySize = DataBaseExecute.getInstance().getCountResultData(
				"select COUNT(*) from face_dev_author_set LEFT JOIN com_dev ON face_dev_author_set.dev_id = com_dev.dev_id where face_dev_author_set.down_loaded = 0 and com_dev.dev_mac_address = '"+deviceId+"';");
		if (authoritySize > 0) {
			String sql = "select pearson_info.person_id,pearson_info.person_name,pearson_info.dpt_name,pearson_info.gender,pearson_info.person_id,pearson_info.card_no,pearson_info.Id_no,pearson_info.person_id,face_photo.photo_url,pearson_info.face_start_use_time,pearson_info.face_stop_use_time\r\n" + 
					" FROM\r\n" + 
					"	pearson_info \r\n" + 
					"	LEFT JOIN face_dev_author_set ON pearson_info.person_id = face_dev_author_set.person_id\r\n" + 
					"	LEFT JOIN face_photo ON  pearson_info.person_id = face_photo.person_id\r\n" + 
					"	LEFT JOIN com_dev ON face_dev_author_set.dev_id = com_dev.dev_id\r\n" + 
					" WHERE\r\n" + 
					"	face_dev_author_set.author_status = 0 and com_dev.dev_mac_address = "+deviceId + ";";
		}
	}
}
