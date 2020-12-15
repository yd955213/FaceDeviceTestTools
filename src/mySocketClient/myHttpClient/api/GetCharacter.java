package mySocketClient.myHttpClient.api;

import java.io.IOException;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import myGson.das.GetCharacterResponseGson;
import myGson.das.ResponseGson;
import myProgressBar.PrimeNumbersTask;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import sqlite3.DataBaseExecute;
import tools.MyString;
import tools.SystemTimes;
import tools.downLoadAuthority.DownLoadAuthorityStatu;
import view.MainIntfaceView;

public class GetCharacter extends OkHttpClientUtil{

	private String url = null;
//	private String personID = null;
	public GetCharacter(String parameterJsonStr, String macAddr) {
		url = getUrl(macAddr);
		url += GetCharacter.class.getSimpleName();
		try {
			String responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonStr);
			if (MyString.isJsonStr(responseStr)) {
				ResponseGson<GetCharacterResponseGson> responseGson = new Gson().fromJson(
						responseStr, 
						new TypeToken<ResponseGson<GetCharacterResponseGson>>() {}.getType());
				if (null != responseGson) {
					new Thread(()->{
						toDoSomeThing(responseGson.getData());
					}) .start();
				}
//				System.out.println("MainIntfaceView.downloadAuthorLabel_success = " + DownLoadAuthorityStatu.getAddSuccess());
				MainIntfaceView.downloadAuthorLabel_success.setText(Integer.toString(DownLoadAuthorityStatu.getAddSuccess()));
			}else {
				MainIntfaceView.downloadAuthorLabel_err.setText(Integer.toString(DownLoadAuthorityStatu.getAddErr()));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]：" + e.getMessage());
			MainIntfaceView.downloadAuthorLabel_err.setText(Integer.toString(DownLoadAuthorityStatu.getAddErr()));
		}finally {
			PrimeNumbersTask.setNumber(PrimeNumbersTask.getNumber() + 1);
		}
			
	}
	
	private void toDoSomeThing(GetCharacterResponseGson getCharacterGson){
		
		if (null != getCharacterGson && null != getCharacterGson.getUniqueCode()) {
			String personID = DataBaseExecute.getInstance().getPersonID(getCharacterGson.getUniqueCode());
			if (null != personID) {
				DataBaseExecute.getInstance().updateDB("face_feature", 
						Arrays.asList("person_id"), 
						Arrays.asList(personID), 
						Arrays.asList("feature", "get_feature_day", "gmt_modified"), 
						Arrays.asList(getCharacterGson.getCharacter() , SystemTimes.getSysTime(), SystemTimes.getSysTime())
				);
				
			}else {
				MainIntfaceView.downloadAuthorLabel_err.setText(Integer.toString(DownLoadAuthorityStatu.getAddErr()));
			}
		}
	}
	
}