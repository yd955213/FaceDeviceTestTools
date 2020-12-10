package mySocketClient.myHttpClient.api;

import java.io.IOException;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import view.MainIntfaceView;

public class RestartDevice extends OkHttpClientUtil{
	public RestartDevice(String parameterJsonStr, String macAddr) {
	
		String url = getUrl(macAddr);
		url += RestartDevice.class.getSimpleName();
		
		try {
//			ResponseGson<String> responseGson = 
//				new Gson().fromJson(
					MySocketChannel.getSochetChannelReceiveData(url, parameterJsonStr);
//					new TypeToken<ResponseGson<String>>() {}.getType());
//			if (getRequest_OK(responseGson)) {
//				JOptionPane.showMessageDialog(null, "参数设置成功", "设置设备信息", JOptionPane.OK_OPTION);
//			}else {
//				JOptionPane.showMessageDialog(null, "参数设置失败、设备返回Msg:" + responseGson.getMsg() + " Code："+ responseGson.getCode(), "设置设备信息", JOptionPane.ERROR_MESSAGE);
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
		
	}
}