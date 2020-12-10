package mySocketClient.myHttpClient.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.reflect.TypeToken;
import myGson.NumberJsonSerializerString;
import myGson.das.DeviceParameterGson;
import myGson.das.ResponseExGson;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import sqlite3.DataBaseExecute;
import sqlite3.DeviceExecute;
import sqlite3.table.FaceDeviceParameterTable;
import tools.MyString;
import tools.SystemTimes;
import view.MainIntfaceView;

/**
 * 4.1读取设备信息
 * @author yangdang
 *
 */
public class GetDeviceParams extends OkHttpClientUtil {
	/**
	 * 测试模式时，不更新数据库
	 */
	private static boolean notUpdateDb = false;
	private static String responseStr = null;
	private static String passWordJvtTest = null;
	private ArrayList<String> tab_fields;
	private ArrayList<String> tab_fields_data;
	public GetDeviceParams() {
		
	}
	
	public GetDeviceParams(String parameterJsonStr, String macAddr ) {
		//生成发送URL
		String url = getUrl(macAddr);
		url += GetDeviceParams.class.getSimpleName();
		try {
			
			String responseStr = MySocketChannel.getSochetChannelReceiveData(url, parameterJsonStr);
			/*
			 * 处理接收数据
			 */
			if (MyString.isJsonStr(responseStr)) {
				
				ResponseExGson<DeviceParameterGson> responseGson= 
						new NumberJsonSerializerString().create().fromJson(responseStr, new TypeToken<ResponseExGson<DeviceParameterGson>>() {}.getType());
//				System.out.println(responseGson);
				//写库
//				macAddr = responseGson.getData().getBasicParams().getDeviceUniqueCode();
				updateFaceDevParams(responseGson.getData());
				
				MainIntfaceView.updateParameterVeiw(responseGson.getData());
				//厂测程序用于是否返回参数时的判断
				passWordJvtTest = responseGson.getData().getAppParams().getAppPassword();
				
			}
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[返回数据错误]："+e.getMessage());
//			System.out.println("通信错误："+ e.getMessage());
			e.printStackTrace();
			MainIntfaceView.getDevInfo().remove(macAddr);
		}
		
	}
	
	public void updateFaceDevParams(DeviceParameterGson deviceParameterGson, String macAddr) {
		getDevicParameterSqlData(deviceParameterGson);
		tab_fields.add("gmt_modified");
		tab_fields_data.add(SystemTimes.getSysTime());
		DataBaseExecute.getInstance().updateDB(
				"face_dev_parameter", 
				Arrays.asList("dev_id"), 
				Arrays.asList(MainIntfaceView.getDevInfo().get(macAddr).getDevID()), 
				tab_fields, 
				tab_fields_data);
	}
	public void updateFaceDevParams(DeviceParameterGson deviceParameterGson) {
		/*
		 * 如果表face_dev_parameter没有该设备，则读取设备参数
		 * 当读取参数成功时，写表com_dev，face_dev_parameter
		 * 写库成功则devTableMap移除相应的设备，MainIntfaceView.getDevInfo()添加相应的设备信息
		 */
		try {
//			Iterator<Entry<String, DevInfo>> entries = MainIntfaceView.getDevInfo().entrySet().iterator();
//			while(entries.hasNext()) {
//				Entry<String, DevInfo> entry = entries.next();
//				System.out.println(entry.getKey() + "  " + entry.getValue().getDevID() + "  " + entry.getValue().getDevIP() + "  " + entry.getValue().getDevPort() + "  " + entry.getValue().getFeature_type());
//			}
			FaceDeviceParameterTable faceDeviceParameterTable = getFaceDeviceParameterTable(deviceParameterGson);
			
			faceDeviceParameterTable.devId = MainIntfaceView.getDevInfo().get(faceDeviceParameterTable.devMacAddress).getDevID();
			faceDeviceParameterTable.gmtModified = SystemTimes.getSysTime();
			
			new DeviceExecute().updateDeviceParams(faceDeviceParameterTable);
			
			MainIntfaceView.getDevInfo().get(faceDeviceParameterTable.devMacAddress).setFeature_type(Integer.toString(faceDeviceParameterTable.featureType));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  FaceDeviceParameterTable getFaceDeviceParameterTable(DeviceParameterGson deviceParameterGson) {
		FaceDeviceParameterTable faceDeviceParameterTable = new FaceDeviceParameterTable();
//		FaceDeviceParameterTable.devId;    //设备ID，与com_dev.dev_Id相关(数据库自增长)   
		faceDeviceParameterTable.devMacAddress = deviceParameterGson.getBasicParams().getDeviceUniqueCode();    //设备Mac地址，与com_dev.dev_mac_address相关
		//设备基本功能
		faceDeviceParameterTable.appPassWord = deviceParameterGson.getAppParams().getAppPassword();    //应用程序管理密码
		faceDeviceParameterTable.devUseType = deviceParameterGson.getBasicParams().getDeviceType();    //设备使用类型(1:仅身份识别 2:普通门禁 3:高级门禁 4:消费 99:其他)
		faceDeviceParameterTable.isSupportWrcode = deviceParameterGson.getBasicParams().getQrCodeSwitch();   //二维码识别(0:设备不支持 1:二维码识别开启 2:二维码识别关闭)
		faceDeviceParameterTable.isSupportCard = deviceParameterGson.getBasicParams().getIsSupportCard(); //是否支持刷卡(1:刷卡开启 2:刷卡关闭)
		faceDeviceParameterTable.triggerActionErval = deviceParameterGson.getBasicParams().getTriggerActionInterval();      //同一人识别间隔时间(毫秒)
		faceDeviceParameterTable.relayTime = deviceParameterGson.getBasicParams().getRelayTime();    //继电器脉冲时间(毫秒)
		//语音提示相关
		faceDeviceParameterTable.recognizeSuccessTip = deviceParameterGson.getVoiceTipParams().getRecognizeSuccessTip();    //识别成功提示语：@识别成功，@代表姓名
		faceDeviceParameterTable.recognizeErrorTip = deviceParameterGson.getVoiceTipParams().getRecognizeErrorTip();     //识别失败提示语
		faceDeviceParameterTable.attendanceTime = deviceParameterGson.getVoiceTipParams().getAttendanceTime();     //工作时段(HH:MM-HH:MM)
		faceDeviceParameterTable.beforeJobTip = deviceParameterGson.getVoiceTipParams().getBeforeJobTip();        //上班之前提示语：@识别成功，@代表姓名
		faceDeviceParameterTable.afterJobTip = deviceParameterGson.getVoiceTipParams().getAfterJob();     //下班之后提示语：@上班一天辛苦了，@代表姓名
		//高级功能基本参数
		faceDeviceParameterTable.isAutoRestart = deviceParameterGson.getBasicParams().getIsAutoRestart();     //设备是否自动重启(0:设备不支持 1:开启 2:关闭)
		faceDeviceParameterTable.dailyTestartTime = deviceParameterGson.getBasicParams().getDailyRestartTime();     //设备每天重启时间(HH:MM:SS)
		faceDeviceParameterTable.isUploadPassImg = deviceParameterGson.getBasicParams().getIsUploadPassImg();      //是否上传通行记录照片(1:开启 2关闭)
		faceDeviceParameterTable.mainUiType  = deviceParameterGson.getBasicParams().getMainUIType();        //首页UI类型(0:设备不支持 1:不指定识别区域 2:指定识别区域)
		faceDeviceParameterTable.enableScreenSaver = deviceParameterGson.getBasicParams().getEnableScreenSaver();     //是否开启屏保(0:设备不支持 1:开启 2:关闭)
		faceDeviceParameterTable.heartBeatErval = deviceParameterGson.getBasicParams().getHeartBeatInterval();     //心跳间隔时间(毫秒)
		faceDeviceParameterTable.wiegandType = deviceParameterGson.getBasicParams().getWiegandType();    //韦根类型(26 = ;34)
		faceDeviceParameterTable.wiegandIn = deviceParameterGson.getBasicParams().getWiegandIn();   //韦根输入(0:设备不支持 1:大端格式 2:小端格式)
		faceDeviceParameterTable.wiegandOut = deviceParameterGson.getBasicParams().getWiegandOut();  //韦根输出(0:大端格式 1:小端格式)
		faceDeviceParameterTable.opendDoorPassWord = deviceParameterGson.getBasicParams().getOpenDoorPassword();   //开门密码
		//算法参数
		faceDeviceParameterTable.featureType   = deviceParameterGson.getFeatureParams().getFeatureType();   //算法类别(只读，设备厂家自定义 1:云从 2:旷世 3:地平线 99:其他)
		faceDeviceParameterTable.featureSdkValue = deviceParameterGson.getFeatureParams().getFeatureSDKValue();    //算法SDK值(只读)
		faceDeviceParameterTable.featureVersion = deviceParameterGson.getFeatureParams().getFeatureVersion();       //算法版本号(只读)
		//识别参数
		if (null != deviceParameterGson.getRecognitionParams().getSimilityThreshold()) {
			faceDeviceParameterTable.similityThreshold = Double.parseDouble(deviceParameterGson.getRecognitionParams().getSimilityThreshold());  //相似度阈值
		}else {
			faceDeviceParameterTable.similityThreshold = 0D;
		}
		
		if(null == deviceParameterGson.getRecognitionParams().getQualityThreshold()) {
			faceDeviceParameterTable.qualityThreshold = 0D;
		}else {
			faceDeviceParameterTable.qualityThreshold = Double.parseDouble(deviceParameterGson.getRecognitionParams().getQualityThreshold());   //质量分阈值
		}
		
		faceDeviceParameterTable.isAlive = deviceParameterGson.getRecognitionParams().getIsAlive();    //活体检测开关(0:设备不支持 1:开启 2关闭)
		faceDeviceParameterTable.livingThreshold = deviceParameterGson.getRecognitionParams().getLivingThreshold();      //活体阈值
		faceDeviceParameterTable.minFacePixel = deviceParameterGson.getRecognitionParams().getMinFacePixel();     //抓拍人脸最小像素
		faceDeviceParameterTable.maxAcePixel = deviceParameterGson.getRecognitionParams().getMaxFacePixel();      //抓拍人脸最大像素
		//硬件参数
		faceDeviceParameterTable.suppleLightMode = deviceParameterGson.getHardWareParams().getSuppleLightMode();       //补光灯模式(0:设备不支持 1:自动 2:常开 3:常关)
		faceDeviceParameterTable.suppleLightOpenTime = deviceParameterGson.getHardWareParams().SuppleLightOpenTime;      //补光灯常开时间段(HH:MM-HH:MM) = ; 选择常开时启用
		faceDeviceParameterTable.debugModeSwitch = deviceParameterGson.getHardWareParams().DebugModeSwitch;       //调试模式开关(0:设备不支持 1:开启 2关闭)
		//高级门禁功能
		faceDeviceParameterTable.remoteCtrlDoorId   = Integer.toString(deviceParameterGson.getAccessParams().getAccessDoorID());   //远程控制门点ID
		//其它与设备无关的统计参数
		faceDeviceParameterTable.reginId = 0; //设备区域ID
		faceDeviceParameterTable.inOutFlag = deviceParameterGson.getAccessParams().getInOutFlag();     //faceDeviceParameterTable.进区域 2:出区域
		faceDeviceParameterTable.isKqUse = deviceParameterGson.getBasicParams().getIsKqUse();      //识别记录是否做考勤，0:不做考勤 1:上下班考勤 11:上班考勤 12:下班考勤
		//消费相关参数
		faceDeviceParameterTable.payUrl = deviceParameterGson.getConsumeParams().getPayUrl();      //支付服务URL(IP:Port)
		faceDeviceParameterTable.queryUrl = deviceParameterGson.getConsumeParams().getQueryUrl();    //查询服务URL(IP:Port)
		if (null == deviceParameterGson.getConsumeParams().getDeviceNum() || deviceParameterGson.getConsumeParams().getDeviceNum().length() == 0) {
			faceDeviceParameterTable.deviceNum = 0;    //设备号
		}else {
			faceDeviceParameterTable.deviceNum = Integer.parseInt(deviceParameterGson.getConsumeParams().getDeviceNum());    //设备号
		}
		
		faceDeviceParameterTable.successInfoDuration = deviceParameterGson.getConsumeParams().getSuccessInfoDuration();     //消费成功界面显示时长(单位为秒 = ;限制最大不超过12S，最少不超过3S)
		faceDeviceParameterTable.DisplayTitle = deviceParameterGson.getConsumeParams().getDisplayTitle();    //副屏幕标语
		faceDeviceParameterTable.breakfastTime = deviceParameterGson.getConsumeParams().getBreakfastTime();    //早餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
		faceDeviceParameterTable.lunchTime = deviceParameterGson.getConsumeParams().getLunchTime();     //午餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
		faceDeviceParameterTable.dinnerTime = deviceParameterGson.getConsumeParams().getDinnerTime();    //晚餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
		faceDeviceParameterTable.supperTime = deviceParameterGson.getConsumeParams().getSupperTime();    //夜宵时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
		faceDeviceParameterTable.offlineSwitch = deviceParameterGson.getConsumeParams().getOfflineMode();    //离线模式权限开关(0:不支持 1:支持)
		faceDeviceParameterTable.offlineSumLimit   = deviceParameterGson.getConsumeParams().getOfflineMoneyLimit();     //离线消费金额限制(离线消费模式才能设置)
		faceDeviceParameterTable.offlineCountLimit   = deviceParameterGson.getConsumeParams().getOfflineNumberLimit();   //离线消费次数限制(离线消费模式才能设置)
		faceDeviceParameterTable.offlineTimeLimit   = deviceParameterGson.getConsumeParams().getOfflineTimeLimit();     //离线消费时间限制(单位：分钟。离线消费模式才能设置)
		faceDeviceParameterTable.ssCardSystemInit   = deviceParameterGson.getConsumeParams().getIsCardSystemInit();    //是否已刷系统子卡(0:未刷 1:已刷)，未刷系统卡才能设置系统卡编号
		faceDeviceParameterTable.onlineWalletId1   = deviceParameterGson.getConsumeParams().getOnlineWalletID1();     //无子卡模式在线主钱包号(未刷系统子卡才能设置)
		faceDeviceParameterTable.onlineWalletId2   = deviceParameterGson.getConsumeParams().getOnlineWalletID2();     //无子卡模式在线辅助钱包号(未刷系统子卡才能设置)
		faceDeviceParameterTable.gmtCreate = SystemTimes.getSysTime1();    //格式为 "YYYY-MM-DD HH:MM:SS.SSS" 
		faceDeviceParameterTable.gmtModified = SystemTimes.getSysTime1();   //格式为 "YYYY-MM-DD HH:MM:SS.SSS" 
		return faceDeviceParameterTable;
		
//		/*
//		 * 由于表com_dev有触发器：
//		 */
//		if(!notUpdateDb) {
//			
//			List<String> list = DataBaseExecute.getInstance().listResult("com_dev", "dev_mac_address", deviceParameterGson.getBasicParams().getDeviceUniqueCode(), "dev_id");
//			
//			if (list.isEmpty()) {
//				//插入数据
//				getDeviceCommunicationParameter(deviceParameterGson);
//				tab_fields.add("dev_mac_address");
//				tab_fields_data.add(deviceParameterGson.getBasicParams().getDeviceUniqueCode());
//				tab_fields.add("gmt_create");
//				tab_fields_data.add(SystemTimes.getSysTime());
//				DataBaseExecute.getInstance().insertIntoDB("com_dev", tab_fields, tab_fields_data);
//				
//				getDevicParameterSqlData(deviceParameterGson);
//				tab_fields.add("dev_id");
//				tab_fields_data.add(DataBaseExecute.getInstance().listResult("com_dev", "dev_mac_address", deviceParameterGson.getBasicParams().getDeviceUniqueCode(), "dev_id").get(0));
//				tab_fields.add("dev_mac_address");
//				tab_fields_data.add(deviceParameterGson.getBasicParams().getDeviceUniqueCode());
//				tab_fields.add("gmt_create");
//				tab_fields_data.add(SystemTimes.getSysTime());
//				DataBaseExecute.getInstance().insertIntoDB("face_dev_parameter", tab_fields, tab_fields_data);
//				
//			}else {
//				//更新数据
//				String devId = list.get(0);
//				
//				getDeviceCommunicationParameter(deviceParameterGson);
//				tab_fields.add("gmt_modified");
//				tab_fields_data.add(SystemTimes.getSysTime());
//				DataBaseExecute.getInstance().updateDB("com_dev", Arrays.asList("dev_id"), Arrays.asList(devId), tab_fields, tab_fields_data);
//				
//				getDevicParameterSqlData(deviceParameterGson);
//				tab_fields.add("gmt_modified");
//				tab_fields_data.add(SystemTimes.getSysTime());
//				DataBaseExecute.getInstance().updateDB("face_dev_parameter", Arrays.asList("dev_id"), Arrays.asList(devId), tab_fields, tab_fields_data);
//			}
//		}
	}
	
//	/**
//	 * 数据库插入时，必须加上字段 dev_mac_address、gmt_create
//	 * 数据库修改时，必须加上字段gmt_modified,并以dev_id作为判断条件
//	 * @param deviceParameterGson
//	 */
//	private void getDeviceCommunicationParameter(DeviceParameterGson deviceParameterGson) {
//		/*表： com_dev
//		 * dev_id integer primary key autoincrement,
//		    dev_use_id tinyint,
//		    dev_is_online character(1),     --是否在线 0：否， 1: 是
//		    dev_is_used character(1),       --是否使用 0：否， 1: 是
//		    dev_ip varchar(20),
//		    dev_gate_ip varchar(20),        --网关IP
//		    dev_mac_ip varchar(20),         --子网掩码
//		    dev_mac_address varchar(30),        --mac地址
//		    dev_port int,
//		    dev_sever_ip varchar(20),
//		    dev_sever_port int,
//		    dev_name varchar(100),
//		    gmt_create text,    --创建时间
//		    gmt_modified text,    --修改时间
//		    constraint uk_DevMac unique(dev_mac_address)
//		 */
//		tab_fields = new ArrayList<String>();
//		tab_fields_data = new ArrayList<String>();
//		tab_fields.add("dev_is_used");
//		tab_fields_data.add("1");
//		tab_fields.add("dev_ip");
//		tab_fields_data.add(deviceParameterGson.getBasicParams().getDeviceIP());
//		tab_fields.add("dev_port");
//		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getDevicePort()));
//		tab_fields.add("dev_sever_ip");
//		tab_fields_data.add(deviceParameterGson.getBasicParams().getServerIP());
//		tab_fields.add("dev_sever_port");
//		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getServerPort()));
//		tab_fields.add("dev_name");
//		tab_fields_data.add(deviceParameterGson.getBasicParams().getDeviceName());
//	}
	/**
	 * 数据库插入时，必须加上字段dev_id、dev_mac_address、gmt_create
	 * 数据库修改时，必须加上字段gmt_modified,并以dev_id作为判断条件
	 * @param deviceParameterGson
	 */
	private void getDevicParameterSqlData(DeviceParameterGson deviceParameterGson) {
		tab_fields = new ArrayList<String>();
		tab_fields_data = new ArrayList<String>();
		/*表：face_dev_parameter
		 *--设备基本功能
		    app_pass_word varchar(16) default '123456' NOT NULL,    --应用程序管理密码
		    dev_use_type int default 2 NOT NULL,    --设备使用类型(1:仅身份识别 2:普通门禁 3:高级门禁 4:消费 99:其他)
		    is_support_qrcode int default 0 NOT NULL,   --二维码识别(0:设备不支持 1:二维码识别开启 2:二维码识别关闭)
		    is_support_card int default 2 NOT NULL, --是否支持刷卡(1:刷卡开启 2:刷卡关闭)
		    trigger_action_interval int default 1000 NOT NULL,      --同一人识别间隔时间(毫秒)
		    relay_time int default 100 NOT NULL,    --继电器脉冲时间(毫秒)
		 */
		tab_fields.add("app_pass_word");
		tab_fields_data.add(deviceParameterGson.getAppParams().getAppPassword());
		tab_fields.add("dev_use_type");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getDeviceType()));
		tab_fields.add("is_support_qrcode");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getQrCodeSwitch()));
		tab_fields.add("is_support_card");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getIsSupportCard()));
		tab_fields.add("trigger_action_interval");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getTriggerActionInterval()));
		tab_fields.add("relay_time");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getRelayTime()));
		/*
		 * --语音提示相关
		    recognize_success_tip varchar(20) default '识别成功' NOT NULL,    --识别成功提示语：@识别成功，@代表姓名
		    recognize_error_tip varchar(20) default '未登记' NOT NULL,     --识别失败提示语
		    attendance_time varchar(16) default '00:00-00:00' NULL,     --工作时段(HH:MM-HH:MM)
		    before_job_tip varchar(20) NULL,        --上班之前提示语：@识别成功，@代表姓名
		    after_job_tip varchar(20) NULL,     --下班之后提示语：@上班一天辛苦了，@代表姓名
		 */
		tab_fields.add("recognize_success_tip");
		tab_fields_data.add(deviceParameterGson.getVoiceTipParams().getRecognizeSuccessTip());
		tab_fields.add("recognize_error_tip");
		tab_fields_data.add(deviceParameterGson.getVoiceTipParams().getRecognizeErrorTip());
		tab_fields.add("attendance_time");
		tab_fields_data.add(deviceParameterGson.getVoiceTipParams().getAttendanceTime());
		tab_fields.add("before_job_tip");
		tab_fields_data.add(deviceParameterGson.getVoiceTipParams().getBeforeJobTip());
		tab_fields.add("after_job_tip");
		tab_fields_data.add(deviceParameterGson.getVoiceTipParams().getAfterJob());
		/*
		 * --高级功能基本参数
		    is_auto_restart int default 0 NOT NULL,     --设备是否自动重启(0:设备不支持 1:开启 2:关闭)
		    daily_restart_time varchar(8) default '02:00' NULL,     --设备每天重启时间(HH:MM:SS)
		    is_upload_pass_img int default 2 NOT NULL,      --是否上传通行记录照片(1:开启 2关闭)
		    main_ui_type int default 0 NOT NULL,        --首页UI类型(0:设备不支持 1:不指定识别区域 2:指定识别区域)
		    enable_screen_saver int default 0 NOT NULL,     --是否开启屏保(0:设备不支持 1:开启 2:关闭)
		    heart_beat_interval int NULL default 30000,     --心跳间隔时间(毫秒)
		    wiegand_type int default 34 NOT NULL,    --韦根类型(26,34)
		    wiegand_in int default 0 NOT NULL,   --韦根输入(0:设备不支持 1:大端格式 2:小端格式)
		    wiegand_out int default 2 NOT NULL,  --韦根输出(0:大端格式 1:小端格式)
		    opend_door_pass_word varchar(16) default '12345678' NULL,   --开门密码
		 */
		tab_fields.add("is_auto_restart");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getIsAutoRestart()));
		tab_fields.add("daily_restart_time");
		tab_fields_data.add(deviceParameterGson.getBasicParams().getDailyRestartTime());
		tab_fields.add("is_upload_pass_img");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getIsUploadPassImg()));
		tab_fields.add("main_ui_type");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getMainUIType()));
		tab_fields.add("enable_screen_saver");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getEnableScreenSaver()));
		tab_fields.add("heart_beat_interval");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getHeartBeatInterval()));
		tab_fields.add("wiegand_type");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getWiegandType()));
		tab_fields.add("wiegand_in");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getWiegandIn()));
		tab_fields.add("wiegand_out");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getWiegandOut()));
		tab_fields.add("opend_door_pass_word");
		tab_fields_data.add(deviceParameterGson.getBasicParams().getOpenDoorPassword());
		/*
		 * --算法参数
		    feature_type int NULL,   --算法类别(只读，设备厂家自定义 1:云从 2:旷世 3:地平线 99:其他)
		    feature_sdk_value varchar(250) NULL,    --算法SDK值(只读)
		    feature_version varchar(50) NULL,       --算法版本号(只读)
		 */
		tab_fields.add("feature_type");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getFeatureParams().getFeatureType()));
		tab_fields.add("feature_sdk_value");
		tab_fields_data.add(deviceParameterGson.getFeatureParams().getFeatureSDKValue());
		tab_fields.add("feature_version");
		tab_fields_data.add(deviceParameterGson.getFeatureParams().getFeatureVersion());
		 /*--识别参数
		    simility_threshold int default 85 NOT NULL,  --相似度阈值
		    quality_threshold int default 70 NOT NULL,   --质量分阈值
		    is_alive int default 0 NOT NULL,    --活体检测开关(0:设备不支持 1:开启 2关闭)
		    living_threshold numeric(6,3) default 99.999 NULL,      --活体阈值
		    min_face_pixel int default 60 NULL,     --抓拍人脸最小像素
		    max_ace_ixel int default 400 NULL,      --抓拍人脸最大像素
		  */
		
		if (null != deviceParameterGson.getRecognitionParams().getSimilityThreshold()) {
			tab_fields.add("simility_threshold");
			tab_fields_data.add(deviceParameterGson.getRecognitionParams().getSimilityThreshold());
		}
		if (null != deviceParameterGson.getRecognitionParams().getQualityThreshold()) {
			tab_fields.add("quality_threshold");
			tab_fields_data.add(deviceParameterGson.getRecognitionParams().getQualityThreshold());
		}
		tab_fields.add("is_alive");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getRecognitionParams().getIsAlive()));
		if (null != deviceParameterGson.getRecognitionParams().getLivingThreshold()) {
			tab_fields.add("living_threshold");
			tab_fields_data.add(Double.toString(deviceParameterGson.getRecognitionParams().getLivingThreshold()));
		}
		tab_fields.add("min_face_pixel");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getRecognitionParams().getMinFacePixel()));
		tab_fields.add("max_ace_ixel");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getRecognitionParams().getMaxFacePixel()));

		 /* --硬件参数
		    supple_light_mode int default 0 NOT NULL,       --补光灯模式(0:设备不支持 1:自动 2:常开 3:常关)
		    supple_light_open_time varchar(16) default '18:00-07:00' NULL,      --补光灯常开时间段(HH:MM-HH:MM), 选择常开时启用
		    debug_mode_switch int default 0 NOT NULL,       --调试模式开关(0:设备不支持 1:开启 2关闭)
	    */	
		tab_fields.add("supple_light_mode");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getHardWareParams().getSuppleLightMode()));
		tab_fields.add("supple_light_open_time");
		tab_fields_data.add(deviceParameterGson.getHardWareParams().getSuppleLightOpenTime());
		tab_fields.add("debug_mode_switch");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getHardWareParams().getDebugModeSwitch()));
		/*--高级门禁功能
		    remote_ctrl_door_id int NULL,   --远程控制门点ID
		    --其它与设备无关的统计参数
		    regin_id int NULL, --设备区域ID
		    in_out_flag int default 0 NULL,     --进出标记 0:不分进出 1:进区域 2:出区域
		    is_kq_use  int default 0 NULL,      --识别记录是否做考勤，0:不做考勤 1:上下班考勤 11:上班考勤 12:下班考勤
		*/	
		tab_fields.add("remote_ctrl_door_id");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getAccessParams().getAccessDoorID()));
		tab_fields.add("in_out_flag");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getAccessParams().getInOutFlag()));
		tab_fields.add("is_kq_use");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getBasicParams().getIsKqUse()));
		/*
		 * --消费相关参数	
			pay_url varchar(100) NULL,      --支付服务URL(IP:Port)
			query_url varchar(100) NULL,    --查询服务URL(IP:Port)
			device_num int  NULL,    --设备号
			success_info_duration int NULL,     --消费成功界面显示时长(单位为秒,限制最大不超过12S，最少不超过3S)
			display_title varchar(200) NULL,    --副屏幕标语
			breakfast_time varchar(16) NULL,    --早餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
			lunch_time varchar(16) NULL,     --午餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
			dinner_time varchar(16) NULL,    --晚餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
			supper_ime varchar(16) NULL,    --夜宵时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
			offline_switch int NULL,    --离线模式权限开关(0:不支持 1:支持)
			offline_sum_limit int NULL,     --离线消费金额限制(离线消费模式才能设置)
			offline_count_limit int NULL,   --离线消费次数限制(离线消费模式才能设置)
			offline_time_imit int NULL,     --离线消费时间限制(单位：分钟。离线消费模式才能设置)
			ssCard_system_init int NULL,    --是否已刷系统子卡(0:未刷 1:已刷)，未刷系统卡才能设置系统卡编号
			online_wallet_id1 int NULL,     --无子卡模式在线主钱包号(未刷系统子卡才能设置)
			online_wallet_id2 int NULL,     --无子卡模式在线辅助钱包号(未刷系统子卡才能设置)
		 */
		tab_fields.add("pay_url");
		tab_fields_data.add(deviceParameterGson.getConsumeParams().getPayUrl());
		tab_fields.add("query_url");
		tab_fields_data.add(deviceParameterGson.getConsumeParams().getQueryUrl());
		tab_fields.add("device_num");
		tab_fields_data.add(deviceParameterGson.getConsumeParams().getDeviceNum());
		tab_fields.add("success_info_duration");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getConsumeParams().getSuccessInfoDuration()));
		tab_fields.add("display_title");
		tab_fields_data.add(deviceParameterGson.getConsumeParams().getDisplayTitle());
		tab_fields.add("breakfast_time");
		tab_fields_data.add(deviceParameterGson.getConsumeParams().getBreakfastTime());
		tab_fields.add("lunch_time");
		tab_fields_data.add(deviceParameterGson.getConsumeParams().getLunchTime());
		tab_fields.add("dinner_time");
		tab_fields_data.add(deviceParameterGson.getConsumeParams().getDinnerTime());
		tab_fields.add("supper_ime");
		tab_fields_data.add(deviceParameterGson.getConsumeParams().getSupperTime());
		tab_fields.add("offline_switch");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getConsumeParams().getOfflineMode()));
		tab_fields.add("offline_sum_limit");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getConsumeParams().getOfflineMoneyLimit()));
		tab_fields.add("offline_count_limit");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getConsumeParams().getOfflineNumberLimit()));
		tab_fields.add("offline_time_imit");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getConsumeParams().getOfflineTimeLimit()));
		tab_fields.add("ssCard_system_init");
		tab_fields_data.add(deviceParameterGson.getBasicParams().getSystemID());
		tab_fields.add("online_wallet_id1");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getConsumeParams().getOnlineWalletID1()));
		tab_fields.add("online_wallet_id2");
		tab_fields_data.add(Integer.toString(deviceParameterGson.getConsumeParams().getOnlineWalletID2()));
	}
//	
	public static boolean isNotUpdateDb() {
		return notUpdateDb;
	}
	public static void setNotUpdateDb(boolean notUpdateDb) {
		GetDeviceParams.notUpdateDb = notUpdateDb;
	}

	public static String getResponseStr() {
		return responseStr;
	}

	public static String getPassWordJvtTest() {
		return passWordJvtTest;
	}

	public static void setPassWordJvtTest(String passWordJvtTest) {
		GetDeviceParams.passWordJvtTest = passWordJvtTest;
	}
	
}
