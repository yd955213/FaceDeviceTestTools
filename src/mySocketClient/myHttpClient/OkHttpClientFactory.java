package mySocketClient.myHttpClient;

import okhttp3.OkHttpClient;

public class OkHttpClientFactory {
	private static volatile OkHttpClientFactory instance = null;
	private OkHttpClient httpClient = null;

	private OkHttpClientFactory() {
		httpClient = new OkHttpClient();
		;
	}

	public static OkHttpClientFactory getIntstance() {
		if (instance == null) {
			synchronized (OkHttpClientFactory.class) {
				if (instance == null) {
					instance = new OkHttpClientFactory();
				}
			}
		}
		return instance;
	}

	public OkHttpClient getHttpClient() {
		return httpClient;
	}

}
