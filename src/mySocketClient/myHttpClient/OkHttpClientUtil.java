package mySocketClient.myHttpClient;

import java.io.IOException;
import myGson.das.ResponseGson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import view.MainIntfaceView;

public class OkHttpClientUtil {
	private static OkHttpClient client = null;
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	private static int devApiPort = 8090;
	private static String devApiIP = null;
	public static boolean requestOk = false;
    public static final String DEVAPI = "/DevApi/";
	
	public static String post(String url, String json) throws IOException {
		client = OkHttpClientFactory.getIntstance().getHttpClient();
		RequestBody body = RequestBody.create(json, JSON);
		Request request = new Request.Builder().url(url).post(body).build();
//		System.out.println("[SendURL] " + url + " [SendData] " + json);
		MainIntfaceView.writeLogsTextArea("[SendURL] " + url, "[SendData]" + json);
		Response response = client.newCall(request).execute();
		String recieve = response.body().string();
		MainIntfaceView.writeLogsTextArea("", "[ReceiveData] " + recieve);
//		System.out.println("接受到数据：" + recieve);
		return recieve;

	}

	public static String getUrl(String mac) {
		String urlStr = null;
//		System.out.println("mac = " + mac +"  MainIntfaceView.devInfoMap.size()="+MainIntfaceView.devInfoMap.size());
//		Iterator<Entry<String, DevInfo>> iterator = MainIntfaceView.devInfoMap.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Entry<String, DevInfo> entry = iterator.next();
//			System.out.println(entry.getKey() +" " + entry.getValue().getDevIP() + " " + entry.getValue().getDevPort());
//		}
				
		if (!MainIntfaceView.devInfoMap.isEmpty() && MainIntfaceView.devInfoMap.containsKey(mac)) {
			urlStr = "http://" + MainIntfaceView.devInfoMap.get(mac).getDevIP() + ":"
					+ MainIntfaceView.devInfoMap.get(mac).getDevPort() + DEVAPI;
		}else {
			urlStr = "http://" + devApiIP + ":" + devApiPort + DEVAPI;
		}
		 
		return urlStr;
	}
	/**
	 * getUrl需要改的地方太多，这里获取的url 不包括/DevApi/" 
	 * @param mac
	 * @return
	 */
	public static String getUrl350(String mac, String mothd) {
		String urlStr = null;	
		if (!MainIntfaceView.devInfoMap.isEmpty() && MainIntfaceView.devInfoMap.containsKey(mac)) {
			urlStr = "http://" + MainIntfaceView.devInfoMap.get(mac).getDevIP() + ":"
					+ MainIntfaceView.devInfoMap.get(mac).getDevPort() + mothd;
		}else {
			urlStr = "http://" + devApiIP + ":" + devApiPort + mothd;
		}
		 
		return urlStr;
	}

//	public static boolean getRequest_OK(String responseJson) {
//		requestOk = false;
//		if (null != responseJson && !"{}".equals(responseJson)) {
//			ResponseGson<?> responseGson = new Gson().fromJson(responseJson, ResponseGson.class);
//			if ("0".equals(responseGson.getCode()) && "OK".equals(responseGson.getMsg())) {
//				requestOk = true;
//			}
//		}
//		return requestOk;
//	}
	public static boolean getRequest_OK(ResponseGson<?> responseJson) {
		requestOk = false;
		if ("0".equals(responseJson.getCode()) || "OK".equals(responseJson.getMsg())) {
			requestOk = true;
		}
		return requestOk;
	}

	public static String getDevApiIP() {
		return devApiIP;
	}

	public static void setDevApiIP(String devApiIP) {
		OkHttpClientUtil.devApiIP = devApiIP;
	}

	public static int getDevApiPort() {
		return devApiPort;
	}

	public static void setDevApiPort(int devApiPort) {
		OkHttpClientUtil.devApiPort = devApiPort;
	}

}
