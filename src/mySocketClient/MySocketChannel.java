package mySocketClient;

import java.io.IOException;

import mySocketClient.myHttpClient.OkHttpClientUtil;

public class MySocketChannel {
	public final static String MQTT = "MQTT";
	public final static String HTTP = "HTTP";
	
	private static String selectSocketType;
	
	private static String receiveData = null;
	public static String getSochetChannelReceiveData(String url, String JsonData) throws IOException {
		switch (selectSocketType) {
		case HTTP:
			receiveData = OkHttpClientUtil.post(url, JsonData);
			break;
			
		case MQTT:
			
			break;

		default:
			break;
		}
		return receiveData;
	}
	public static String getSelectSocketType() {
		return selectSocketType;
	}
	public static void setSelectSocketType(String selectSocketType) {
		MySocketChannel.selectSocketType = selectSocketType;
	}
	
	
}
