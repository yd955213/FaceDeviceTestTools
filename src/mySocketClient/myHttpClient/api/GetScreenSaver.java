package mySocketClient.myHttpClient.api;

import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import myGson.das.ResponseGson;
import myGson.das.ScreenSaverGson;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.MyString;
import view.MainIntfaceView;

public class GetScreenSaver extends OkHttpClientUtil{
public GetScreenSaver(String parameterJsonStr, String macAddr) {
	
		String url = getUrl(macAddr);
		url += GetScreenSaver.class.getSimpleName();
		
		try {
			String responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonStr);
			if (MyString.isJsonStr(responseStr)) {
				ResponseGson<ScreenSaverGson> responseGson = new Gson().fromJson(responseStr, new TypeToken<ResponseGson<ScreenSaverGson>>() {}.getType());
				if (null != responseGson) {
					if("OK".equals(responseGson.getMsg().toUpperCase())) {
						JOptionPane.showMessageDialog(null, "设备(" + macAddr + ")返回信息:" + responseGson.getMsg(), "读取屏保文字和图片", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "设备(" + macAddr + ")返回信息:" + responseGson.getMsg(), "读取屏保文字和图片", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
		
	}
}