package httpFrame.http.readSense;

import java.io.File;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import httpFrame.http.Http;
import httpFrame.http.HttpCmd;
import httpFrame.http.dasApi.DownLoadApk;
import myGson.readSense.RegisterGson;
import myGson.readSense.ResponseReadSenseGson;
import myGson.readSense.ResultGson;
import myGson.readSense.UpdateLocalDatabaseRequestGson;
import tools.MyString;

public class UpdateLocalDatabase extends HttpCmd {
	
	private String url = null;
	private final String heand = "http://";
	private final String colon = ":";
	private final String backslash = "/";
	private static String filePath = ".\\test\\readSense\\";
	static {
		//注册接口 /ServerApi/DeviceHeartBeat
		HttpCmd.register("/api/v1/service/updatelocaldatabase",UpdateLocalDatabase.class);
	}
	
	public void execute() {  //接口业务逻辑处理方法

		String requestStr = getRequestJsonStr();
		System.out.println(requestStr);
		if (MyString.isJsonStr(requestStr)) {
			UpdateLocalDatabaseRequestGson updateLocalDatabaseRequestGson = gson.fromJson(requestStr, UpdateLocalDatabaseRequestGson.class);
			System.out.println(updateLocalDatabaseRequestGson.getTerminal_id());
			System.out.println(updateLocalDatabaseRequestGson.getTs());
			doSomeThing();
		}
	}
	
	private void doSomeThing() {
		File[] fileList = new File(filePath).listFiles();
		RegisterGson registerPerson = null;
		int i = 0 ; 
		ArrayList<RegisterGson> server = new ArrayList<RegisterGson>();
		for (File file:fileList) {
			i += 1;
			url = heand + Http.getServerIP() + colon + Http.getPort() + DownLoadApk.URL_NAME + backslash + file.getName();
			System.out.println(url);
			registerPerson = new RegisterGson();
			registerPerson.setName(file.getName());
			registerPerson.setPerson_id(Integer.toString(i));
			registerPerson.setCard_id(Integer.toString(i));
			registerPerson.setFace_image(url);
			registerPerson.setPassword("123456");
//			registerPerson.setTime(SystemTimes.getSysTime1());
			server.add(registerPerson);
		}
		ResultGson resultGson = new ResultGson();  
		resultGson.setUpdated_data_device(server);
		resultGson.setDeleted_data(new ArrayList<RegisterGson>());
//		updateLocalDatabaseGson.setUpdated_data_service(server);
		
		ResponseReadSenseGson<ResultGson> responseGson = new ResponseReadSenseGson<ResultGson>();
		responseGson.setResult(resultGson);
		System.out.println(responseGson.getResult().getUpdated_data_device().get(0).getName());
		String st = new GsonBuilder().serializeNulls().create().toJson(responseGson, new TypeToken<ResponseReadSenseGson<ResultGson>>() {}.getType());
		System.out.println(st);
		response(st);
	}

	public static String getFilePath() {
		return filePath;
	}

	
}
