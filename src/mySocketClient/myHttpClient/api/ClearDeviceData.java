package mySocketClient.myHttpClient.api;

import java.io.IOException;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import view.MainIntfaceView;

public class ClearDeviceData{
	public ClearDeviceData(String parameterJsonString, String macAddr) {
		String url = OkHttpClientUtil.getUrl(macAddr);
		url += ClearDeviceData.class.getSimpleName();
		
		try {
			MySocketChannel.getSochetChannelReceiveData(url, parameterJsonString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
		
	}
	
}
