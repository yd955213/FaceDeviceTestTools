package httpFrame.http.dasApi;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.google.gson.reflect.TypeToken;

import comm.myLibraryLoad.jvt.FactoryTest;
import faceEngine.MyPhoto;
import httpFrame.http.HttpCmd;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
import myGson.das.UploadRecordGson;
import sqlite3.DataBaseExecute;
import tools.MyIdName;
import tools.MyString;
import tools.SystemTimes;
import view.MainIntfaceView;
import view.update.RecordUI;
/**
 * 3.2上报记录
 * @author yangdang
 *
 */
public class UploadRecords extends HttpCmd{
	private static UploadRecordGson urj = null;
	private static String name = null;
	static {
		HttpCmd.register(SERVERAPI+UploadRecords.class.getSimpleName(), UploadRecords.class);
		
	}
	
	@Override
	public void execute() {  //接口业务逻辑处理方法
		String requestStr = getRequestJsonStr();
		
		MainIntfaceView.writeLogsTextArea("[RequestURL] " + requestUrl, requestStr);
//		System.out.println("requestStr = " + requestStr);
		if (MyString.isJsonStr(requestStr)) {
			RequestGson<UploadRecordGson> requestGson = gson.fromJson(requestStr, 
					new TypeToken<RequestGson<UploadRecordGson>>() {}.getType());
			urj = requestGson.getData();
			
			if (null != requestGson) {
				insertIntoSql();
				doSomeThing();
				
				ResponseGson<String> responseGson = new ResponseGson<String>();
				responseGson.setData(null);
				requestStr = gson.toJson(responseGson, new TypeToken<ResponseGson<String>>() {}.getType());
				response(requestStr);
				MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + requestStr);
			}
			
		}
	}
	
	
	public void doSomeThing() {
		HashMap<String, String> map = new HashMap<String, String>();
		List<List<String>> personInfo = DataBaseExecute.getInstance().listResult("pearson_info", Arrays.asList("unique_code"), Arrays.asList(urj.getUniqueCode()), Arrays.asList("Person_id", "person_name"));
		name = null;
		if(!personInfo.isEmpty()) {
			name = personInfo.get(0).get(1);
			System.out.println("记录上报name = " + name);
			List<String> photInDb = DataBaseExecute.getInstance().listResult("face_photo", "person_id", personInfo.get(0).get(0), "Photo_url");
			//判断
			System.out.println("照片路径 = " + photInDb.get(0));
			if(!photInDb.isEmpty()) {
				String bese64 = MyPhoto.getPhoteBASE64_Mime(new File(photInDb.get(0)));
				if (bese64 != null) {
					map.put("photInDb", bese64);
				}else {
					map.put("photInDb", MainIntfaceView.getNoPersonInDbBase64());
				}
			}else {
				map.put("photInDb", MainIntfaceView.getNoPersonInDbBase64());
			}
		}else {
			map.put("photInDb", MainIntfaceView.getNoPersonInDbBase64());
		}
		
		map.put("similarityScore", urj.getSimilarityScore());
		if( null == urj.getCapturePhoto() || "".equals(urj.getCapturePhoto())) {
			map.put("capturePhoto", MainIntfaceView.getNoCapturePhotoBase64());
		}else {
			map.put("capturePhoto", urj.getCapturePhoto());
		}
		
//		Iterator<Entry<String, String>> entries =map.entrySet().iterator();
//		while(entries.hasNext()) {
//			Entry<String, String> entry = entries.next();
//			System.out.println("mapkey = " + entry.getKey() +"  " + entry.getValue().length());
//		}
		//界面显示抓拍照片
		RecordUI.getInstance().setQueueOffer(map);
		/*
		 * 厂测功能，用于判断记录是否上传
		 */
		if (null != FactoryTest.getWaiteAliveRecognitionList()) {
			FactoryTest.getWaiteAliveRecognitionList().add(name);
		}
	}
	public void insertIntoSql() {
		 /*
		  *     [record_id] [bigint] primary key NOT NULL,  --记录ID
			    [device_unique_code] [varchar](50) NULL,    --设备唯一Id mac
			    [record_time] [datetime] NULL,  --记录时间 格式:yyyy-MM-dd HH:mm:ss
			    [action_type] [int] NULL,   --业务类型 0:刷脸 1:刷证件 2:刷卡片 99:其他
			    [action_type_name] [varchar](50) NULL,
			    [device_type] [int] NULL,  --设备用途 1:仅身份识别 2:普通门禁 3:高级门禁 4:消费 99:其他
			    [device_type_name] [varchar](50) NULL,
			    [unique_code] [varchar](50) NULL,   --人员唯一标识
			    [capture_photo_base64] [text] NULL, --抓拍照base64
			    [similarity_score] [varchar](50) NULL,  --相似度分数
			    [similarity_threshold] [varchar](50) NULL,  --相似度阈值
			    [quality_score] [varchar](50) NULL,     --质量分分数
			    [quality_threshold] [varchar](50) NULL, --质量分阈值
			    [is_alive] [varchar](50) NULL,  --是否启用活体
			    [access_door_id] [int] NULL,    --门禁设备门号
			    [access_code] [varchar](50) NULL,   --门禁通行代码
			    [access_result] [varchar](50) NULL, --门禁通行返回值
			    [id_type] [int] NULL,   --证件类型 1:身份证 2:港澳通行证 3:驾驶证 4:护照 99:其他
			    [id_type_name] [varchar](50) NULL,
			    [id_no] [varchar](50) NULL, --证件号码
			    [card_no] [varchar](50) NULL,   --卡号
			    [gmt_create] [text] NULL    --创建时间
		  */
		 try {
			List<String> data = new ArrayList<String>();
			data.add(null);
			data.add(Integer.toString(urj.getRecordID()));
			data.add(urj.getDeviceUniqueCode());
			data.add(urj.getRecordTime());
			data.add(Integer.toString(urj.getActionType()));
			data.add(null);
			data.add(Integer.toString(urj.getDeviceType()));
			data.add(null);
			data.add(urj.getUniqueCode());
			data.add(urj.getCapturePhoto());
			data.add(urj.getSimilarityScore());
			data.add(urj.getSimilarityThreshold());
			data.add(urj.getQualityScore());
			data.add(urj.getQualityThreshold());
			data.add(urj.getIsAlive());
			data.add(Integer.toString(urj.getAccessDoorID()));
			data.add(urj.getAccessCode());
			data.add(urj.getAccessResult());
			data.add(Integer.toString(urj.getIDType()));
			data.add(MyIdName.getMyIdName(urj.getIDType()));
			data.add(urj.getIDNo());
			data.add(urj.getCardNo());
			data.add(SystemTimes.getSysTime());
			
			new Thread(()-> {
				DataBaseExecute.getInstance().insertIntoDB("face_dev_record", new ArrayList<String>(), data);
			}).start();
		 } catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
		}
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		UploadRecords.name = name;
	}

}


