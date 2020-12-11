package httpFrame.http.dasApi;

import java.util.Arrays;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import faceEngine.MyPhoto;
import httpFrame.http.HttpCmd;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
import myGson.das.UploadAuthorityDealResultGson;
import myProgressBar.PrimeNumbersTask;
import sqlite3.DataBaseExecute;
import tools.LogsWriter;
import tools.MyString;
import tools.SystemTimes;
import tools.downLoadAuthority.DownLoadAuthorityStatu;
import view.MainIntfaceView;
/**
 * 3.3上报身份数据处理结果
 * @author yangdang
 *
 */
public class UploadAuthorityDealResult extends HttpCmd {
	static {
		HttpCmd.register(SERVERAPI +UploadAuthorityDealResult.class.getSimpleName(), UploadAuthorityDealResult.class);
	}
	@Override
	public void execute() {

		String requestStr = getRequestJsonStr();
		MainIntfaceView.writeLogsTextArea("[RequestURL：] " + requestUrl, requestStr);
		if (MyString.isJsonStr(requestStr)) {
			RequestGson<UploadAuthorityDealResultGson> requestGson = new Gson().fromJson(requestStr, 
					new TypeToken<RequestGson<UploadAuthorityDealResultGson>>(){}.getType());
			
			if (null !=DownLoadAuthorityStatu.getPhotoUrlList() && !DownLoadAuthorityStatu.getPhotoUrlList().isEmpty()) {
				if(!requestGson.getData().getMsg().toUpperCase().equals("OK")) {
			
				//写日志 将下载失败的照片写出来
				new MyPhoto().writePhotoToFile(DownLoadAuthorityStatu.getPhotoUrlList().get(requestGson.getData().getUniqueCode()));
				//写日志
				LogsWriter.writeInfo(LogsWriter.DOWN_LOAD_AUTHORITY_ERR, 
						DownLoadAuthorityStatu.getLogsMassage(requestGson.getData().getUniqueCode(),
								DownLoadAuthorityStatu.getPhotoUrlList().get(requestGson.getData().getUniqueCode()), 
								requestGson.getData().getMsg(),
								requestGson.getDeviceUniqueCode()
								)
						);
				}
				
				DownLoadAuthorityStatu.getPhotoUrlList().remove(requestGson.getData().getUniqueCode());
			}
			new Thread(() ->{
				updateDb(requestGson);
				PrimeNumbersTask.setNumber(PrimeNumbersTask.getNumber() + 1);
			}).start();
	
			ResponseGson<String> responseGson = new ResponseGson<String>();
			responseGson.setData(null);
			requestStr = gson.toJson(responseGson, new TypeToken<ResponseGson<String>>() {}.getType());
			response(requestStr);
			MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + requestStr);
		}
	}

	
	private void updateDb( RequestGson<UploadAuthorityDealResultGson> resultGson) {
		UploadAuthorityDealResultGson uploadAuthorityDealResultGson = resultGson.getData();
		
		//
		if (!DownLoadAuthorityStatu.getDownPersonInfo().isEmpty()) {
			DownLoadAuthorityStatu.getDownPersonInfo().remove(uploadAuthorityDealResultGson.getUniqueCode());
			DownLoadAuthorityStatu.setLotSize(DownLoadAuthorityStatu.getLotSize() + 1);
		}
		String devMac= null;
		String devID = null;
		Map<String,String> devInfo = null;
		//接口中无DeviceUniqueCode字段，以IP代替
		if(null == uploadAuthorityDealResultGson.getUniqueCode()) {
			devMac = resultGson.getDeviceUniqueCode();
			devID = MainIntfaceView.getDevInfo().get(devMac).getDevID();
		}else {
			String ip = getServerIpInfo();
			devInfo = DataBaseExecute.getInstance().listResult_Map("com_dev", Arrays.asList("dev_ip"),  Arrays.asList(ip) , Arrays.asList("dev_id", "dev_mac_address")).get(0);
			devMac = devInfo.get("dev_mac_address");
			devID = devInfo.get("dev_id");
		}
		String personId = DataBaseExecute.getInstance().getPersonID(uploadAuthorityDealResultGson.getUniqueCode());
		
		//更新表 face_dev_author_set
		DataBaseExecute.getInstance().updateDB("face_dev_author_set", 
				Arrays.asList("dev_id", "person_id" ), 
				Arrays.asList(devID, personId), 
				Arrays.asList("down_loaded", "last_down_time","remark", "gmt_modified"), 
				Arrays.asList("1", uploadAuthorityDealResultGson.getStartTime(), uploadAuthorityDealResultGson.getMsg(), SystemTimes.getSysTime()));
		
		if (uploadAuthorityDealResultGson.getCode().equals("0")) {
			
			
			MainIntfaceView.downloadAuthorLabel_success.setText(Integer.toString(DownLoadAuthorityStatu.getAddSuccess()));
			
		}else {
			MainIntfaceView.downloadAuthorLabel_err.setText(Integer.toString(DownLoadAuthorityStatu.getAddErr()));
		}
	}
}
