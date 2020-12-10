package mySocketClient.myHttpClient.api;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import myGson.das.ResponseGson;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import tools.MyString;
import view.MainIntfaceView;
import view.update.DbServerTest;

public class GetAllPerson{
	
	public GetAllPerson(String parameterJsonStr, String macAddr ) {
		
		String url = OkHttpClientUtil.getUrl(macAddr);
		url += GetAllPerson.class.getSimpleName();

		try {
			String responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonStr);
			if (MyString.isJsonStr(responseStr)) {
				System.out.println(responseStr);
				ResponseGson<List<String>> responseGson = new Gson().fromJson(
						responseStr, 
						new TypeToken<ResponseGson<List<String>>>() {}.getType());
				doSomeThing(macAddr, responseGson.getData());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
		}
		
	}
	
	public void doSomeThing(String macAddr, List<String> personList) {
		
		String data = DbServerTest.getInstance().doCompareAuthority(macAddr, personList);
		JOptionPane.showMessageDialog(null, data, "对比人脸权限", JOptionPane.INFORMATION_MESSAGE);
	}
}
