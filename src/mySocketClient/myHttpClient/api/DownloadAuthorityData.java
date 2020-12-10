package mySocketClient.myHttpClient.api;

import java.io.IOException;
import com.google.gson.Gson;
import myGson.das.ResponseExGson;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.MyString;
import tools.downLoadAuthority.DownLoadAuthorityStatu;
import view.MainIntfaceView;

public class DownloadAuthorityData extends OkHttpClientUtil{
	private String url = null;
	private String parameterJsonStr = null;
	public DownloadAuthorityData(String parameterJson, String macAddr) {
		url = getUrl(macAddr);
		url += DownloadAuthorityData.class.getSimpleName();
		this.parameterJsonStr = parameterJson;
	}
	
	public void reSendData() {
		if (null != parameterJsonStr) {
			sendData();
		}
	}
	
	public String sendData() {
		String responseStr = null;
		try {
//			parameter = post(url, parameterJson);
			responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonStr);
			parameterJsonStr = null;
			if (MyString.isJsonStr(responseStr)) {
				DownLoadAuthorityStatu.setDownLoadAuthorityFaile(false);
				requestOk = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DownLoadAuthorityStatu.setDownLoadAuthorityFaile(true);
			MainIntfaceView.writeLogsTextArea(null, "[连接失败]："+e.getMessage() +"\t" + url);
	//			e.printStackTrace();

			ResponseExGson<String> responseGson = new ResponseExGson<String>();
			responseGson.setMsg("Err");
			responseGson.setErr(e.getMessage());
			responseGson.setUrl(url);
			responseStr = new Gson().toJson(responseGson, ResponseExGson.class);
			requestOk = false;
		}
		return responseStr;
	}
	
}
