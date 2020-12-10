package mySocketClient.myHttpClient.api;

import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import myGson.das.RemoteOpenDoorResponseGson;
import myGson.das.ResponseGson;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.MyString;
import view.MainIntfaceView;

public class RemoteOpenDoor extends OkHttpClientUtil{
public RemoteOpenDoor(String parameterJsonStr, String macAddr ) {
	
		String url = getUrl(macAddr);
		url += RemoteOpenDoor.class.getSimpleName();
		
		try {
			String responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonStr);
			if (MyString.isJsonStr(responseStr)) {
				ResponseGson<RemoteOpenDoorResponseGson> ResponseGson = new Gson().fromJson(
						responseStr,
						new TypeToken<ResponseGson<RemoteOpenDoorResponseGson>>() {}.getType());
				
				if (getRequest_OK(ResponseGson)) {
					JOptionPane.showMessageDialog(null, "远程开门成功", "设置设备信息", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "远程开门失败:Msg:" + ResponseGson.getMsg() + " Code："+ ResponseGson.getCode(), "设置设备信息", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
		
	}
}