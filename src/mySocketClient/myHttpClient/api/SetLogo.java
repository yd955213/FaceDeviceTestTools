package mySocketClient.myHttpClient.api;

import java.io.IOException;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import view.MainIntfaceView;

public class SetLogo{
	
	public SetLogo(String parameterJsonStr, String macAddr) {
		
		String url = OkHttpClientUtil.getUrl(macAddr);
		url += SetLogo.class.getSimpleName();
		
		try {
			MySocketChannel.getSochetChannelReceiveData( url, parameterJsonStr);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
		
	}
	
}
