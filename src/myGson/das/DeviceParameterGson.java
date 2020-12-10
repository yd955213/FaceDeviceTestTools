package myGson.das;

import com.google.gson.annotations.SerializedName;

public class DeviceParameterGson {
	/**设备基本参数*/
	private BasicParams BasicParams;
	/**算法相关参数*/
	private FeatureParams FeatureParams;
	/**识别相关参数*/
	private RecognitionParams RecognitionParams;
	/**App相关参数*/
	private AppParams AppParams;
	/**高级门禁模式相关参数*/
	private AccessParams AccessParams;
	/**相机相关参数*/
	private CameraParams CameraParams;
	/**设备硬件参数*/
	private HardWareParams HardWareParams;
	/**语音提示语*/
	private VoiceTipParams VoiceTipParams;
	/**消费模式相关参数(消费金额统一保留2位小数)*/
	private ConsumeParams ConsumeParams;
	
	public DeviceParameterGson() {
		BasicParams = new BasicParams();
		FeatureParams = new FeatureParams();
		RecognitionParams = new RecognitionParams();
		AppParams = new AppParams();
		AccessParams = new AccessParams();
		CameraParams = new CameraParams();
		HardWareParams = new HardWareParams();
		VoiceTipParams = new VoiceTipParams();
		ConsumeParams = new ConsumeParams();
	}
	
	public class BasicParams{
		public String DeviceUniqueCode;
		public String DeviceIP;
		public int DevicePort;
		public String DeviceName;
		/**
		 * 1:仅身份识别、2:普通门禁、3:高级门禁、	4:消费、5:门禁一体机、99:其他
		 */
		public int DeviceType;
		public String ServerIP;
		public int ServerPort;
		public String SystemID;
		/**0:设备不支持、1:开启、2关闭*/
		public int IsAutoRestart;
		public String DailyRestartTime;
		public int IsUploadPassImg;
		public int RelayTime;
		public int TriggerActionInterval;
		/**0:设备不支持、1:二维码识别开启、2:二维码识别关闭*/
		public int QrCodeSwitch;
		/**0:设备不支持、1:刷卡开启、2:刷卡关闭*/
		public int IsSupportCard;
		/**0:设备不支持、26、34*/
		public int WiegandType;
		/**0:设备不支持、1:正序、2:反序*/
		public int WiegandIn;
		/**0:设备不支持、1:正序、2:反序*/
		public int WiegandOut;
		/**0:设备不支持、1:识别时不指定人脸识别区域、2:识别时指定人脸识别区域*/
		public int MainUIType;
		/**0:设备不支持、1:开启、2:关闭*/
		public int EnableScreenSaver;
		public String OpenDoorPassword;
		/**毫秒*/
		public int HeartBeatInterval;
		/**
		 * 0:不做考勤
		 * 1:上下班考勤
		 * 11:上班考勤
		 * 12:下班考勤
		 */
		public int IsKqUse;
		/**
		 * 文件服务器地址:格式http://ip端口或者域名
		 * http://172.168.120.190:19605、http://cloud.csdas.cn
		 */
		public String FileServerUrl;
		
		public String getDeviceUniqueCode() {
			return DeviceUniqueCode;
		}
		public void setDeviceUniqueCode(String deviceUniqueCode) {
			DeviceUniqueCode = deviceUniqueCode;
		}
		public String getDeviceIP() {
			return DeviceIP;
		}
		public void setDeviceIP(String deviceIP) {
			DeviceIP = deviceIP;
		}
		public int getDevicePort() {
			return DevicePort;
		}
		public void setDevicePort(int devicePort) {
			DevicePort = devicePort;
		}
		public String getDeviceName() {
			return DeviceName;
		}
		public void setDeviceName(String deviceName) {
			DeviceName = deviceName;
		}
		public int getDeviceType() {
			return DeviceType;
		}
		public void setDeviceType(int deviceType) {
			DeviceType = deviceType;
		}
		public String getServerIP() {
			return ServerIP;
		}
		public void setServerIP(String serverIP) {
			ServerIP = serverIP;
		}
		public int getServerPort() {
			return ServerPort;
		}
		public void setServerPort(int serverPort) {
			ServerPort = serverPort;
		}
		public String getSystemID() {
			return SystemID;
		}
		public void setSystemID(String systemID) {
			SystemID = systemID;
		}
		public int getIsAutoRestart() {
			return IsAutoRestart;
		}
		public void setIsAutoRestart(int isAutoRestart) {
			IsAutoRestart = isAutoRestart;
		}
		public String getDailyRestartTime() {
			return DailyRestartTime;
		}
		public void setDailyRestartTime(String dailyRestartTime) {
			DailyRestartTime = dailyRestartTime;
		}
		public int getIsUploadPassImg() {
			return IsUploadPassImg;
		}
		public void setIsUploadPassImg(int isUploadPassImg) {
			IsUploadPassImg = isUploadPassImg;
		}
		public int getRelayTime() {
			return RelayTime;
		}
		public void setRelayTime(int relayTime) {
			RelayTime = relayTime;
		}
		public int getTriggerActionInterval() {
			return TriggerActionInterval;
		}
		public void setTriggerActionInterval(int triggerActionInterval) {
			TriggerActionInterval = triggerActionInterval;
		}
		public int getQrCodeSwitch() {
			return QrCodeSwitch;
		}
		public void setQrCodeSwitch(int qrCodeSwitch) {
			QrCodeSwitch = qrCodeSwitch;
		}
		public int getIsSupportCard() {
			return IsSupportCard;
		}
		public void setIsSupportCard(int isSupportCard) {
			IsSupportCard = isSupportCard;
		}
		public int getWiegandType() {
			return WiegandType;
		}
		public void setWiegandType(int wiegandType) {
			WiegandType = wiegandType;
		}
		public int getWiegandIn() {
			return WiegandIn;
		}
		public void setWiegandIn(int wiegandIn) {
			WiegandIn = wiegandIn;
		}
		public int getWiegandOut() {
			return WiegandOut;
		}
		public void setWiegandOut(int wiegandOut) {
			WiegandOut = wiegandOut;
		}
		public int getMainUIType() {
			return MainUIType;
		}
		public void setMainUIType(int mainUIType) {
			MainUIType = mainUIType;
		}
		public int getEnableScreenSaver() {
			return EnableScreenSaver;
		}
		public void setEnableScreenSaver(int enableScreenSaver) {
			EnableScreenSaver = enableScreenSaver;
		}
		public String getOpenDoorPassword() {
			return OpenDoorPassword;
		}
		public void setOpenDoorPassword(String openDoorPassword) {
			OpenDoorPassword = openDoorPassword;
		}
		public int getHeartBeatInterval() {
			return HeartBeatInterval;
		}
		public void setHeartBeatInterval(int heartBeatInterval) {
			HeartBeatInterval = heartBeatInterval;
		}
		public int getIsKqUse() {
			return IsKqUse;
		}
		public void setIsKqUse(int isKqUse) {
			IsKqUse = isKqUse;
		}
		public String getFileServerUrl() {
			return FileServerUrl;
		}
		public void setFileServerUrl(String fileServerUrl) {
			FileServerUrl = fileServerUrl;
		}
		
	}
	
	public class FeatureParams{
		/**
		 * 1:云从
			2:旷世
			3:地平线
			4:商汤
			5:宇泛
			99:其它
		 */
		public int FeatureType;
		/**只读*/
		public String FeatureSDKValue;

		/**只读*/
		public String FeatureVersion;
		
		
		public int getFeatureType() {
			return FeatureType;
		}
		public void setFeatureType(int featureType) {
			FeatureType = featureType;
		}
		public String getFeatureSDKValue() {
			return FeatureSDKValue;
		}
		public void setFeatureSDKValue(String featureSDKValue) {
			FeatureSDKValue = featureSDKValue;
		}
		public String getFeatureVersion() {
			return FeatureVersion;
		}
		public void setFeatureVersion(String featureVersion) {
			FeatureVersion = featureVersion;
		}
		
	}
	
	public class RecognitionParams{
		public String SimilityThreshold;
		public String QualityThreshold;
		public int MinFacePixel;
		public int MaxFacePixel;
		/**
		 * 0:设备不支持
			1:开启
			2:关闭
		 */
		public int IsAlive;
		public Double LivingThreshold = 0D;
		
		public String getSimilityThreshold() {
			return SimilityThreshold;
		}
		public void setSimilityThreshold(String similityThreshold) {
			SimilityThreshold = similityThreshold;
		}
		public String getQualityThreshold() {
			return QualityThreshold;
		}
		public void setQualityThreshold(String qualityThreshold) {
			QualityThreshold = qualityThreshold;
		}
		public int getMinFacePixel() {
			return MinFacePixel;
		}
		public void setMinFacePixel(int minFacePixel) {
			MinFacePixel = minFacePixel;
		}
		public int getMaxFacePixel() {
			return MaxFacePixel;
		}
		public void setMaxFacePixel(int maxFacePixel) {
			MaxFacePixel = maxFacePixel;
		}
		public int getIsAlive() {
			return IsAlive;
		}
		public void setIsAlive(int isAlive) {
			IsAlive = isAlive;
		}
		public Double getLivingThreshold() {
			if(null != LivingThreshold) {
				LivingThreshold = 0D;
			}
			return LivingThreshold;
		}
		public void setLivingThreshold(Double livingThreshold) {
			LivingThreshold = livingThreshold;
		}
	}
	
	public class AppParams{
		public String AppVersion;
		public String AppPassword;
		
		public String getAppVersion() {
			return AppVersion;
		}
		public void setAppVersion(String appVersion) {
			AppVersion = appVersion;
		}
		public String getAppPassword() {
			return AppPassword;
		}
		public void setAppPassword(String appPassword) {
			AppPassword = appPassword;
		}
	}
	
	/**
	 * 高级门禁模式相关参数
	 */
	public class AccessParams{
		public String AccessDeviceIP;
		public int AccessDoorID;
		public String AccessDeviceUniqueCode;
		public int UDPPort;
		/**
		 * 门点进出标记
			0:不区分进出
			1:进
			2:出
		 */
		public int InOutFlag;
		
		public String getAccessDeviceIP() {
			return AccessDeviceIP;
		}
		public void setAccessDeviceIP(String accessDeviceIP) {
			AccessDeviceIP = accessDeviceIP;
		}
		public int getAccessDoorID() {
			return AccessDoorID;
		}
		public void setAccessDoorID(int accessDoorID) {
			AccessDoorID = accessDoorID;
		}
		public String getAccessDeviceUniqueCode() {
			return AccessDeviceUniqueCode;
		}
		public void setAccessDeviceUniqueCode(String accessDeviceUniqueCode) {
			AccessDeviceUniqueCode = accessDeviceUniqueCode;
		}
		public int getUDPPort() {
			return UDPPort;
		}
		public void setUDPPort(int uDPPort) {
			UDPPort = uDPPort;
		}
		public int getInOutFlag() {
			return InOutFlag;
		}
		public void setInOutFlag(int inOutFlag) {
			InOutFlag = inOutFlag;
		}
		
	}
	
	public class CameraParams{
		/**
		 * 相机模式
			0:设备不支持
			1:室内
			2:室外
			3:智能
		 */
		public int CameraMode;
		/**
		 * 美颜分值,范围0~100，0表示无美颜效果，100表示美颜效果最好
		 */
		public int BeautyScore;
		
		public int getCameraMode() {
			return CameraMode;
		}
		public void setCameraMode(int cameraMode) {
			CameraMode = cameraMode;
		}
		public int getBeautyScore() {
			return BeautyScore;
		}
		public void setBeautyScore(int beautyScore) {
			BeautyScore = beautyScore;
		}
	}
	
	public class HardWareParams{
		/**
		 * 补光灯模式
			0:设备不支持
			1:自动
			2:常开
			3:常关
		 */
		public int SuppleLightMode;
		public String SuppleLightOpenTime;
		/**
		 * 调试模式开关（ADB wifi,调试信息显示）
			0:设备不支持
			1:开
			2:关
		 */
		public int DebugModeSwitch;
		
		public int getSuppleLightMode() {
			return SuppleLightMode;
		}
		public void setSuppleLightMode(int suppleLightMode) {
			SuppleLightMode = suppleLightMode;
		}
		public String getSuppleLightOpenTime() {
			return SuppleLightOpenTime;
		}
		public void setSuppleLightOpenTime(String suppleLightOpenTime) {
			SuppleLightOpenTime = suppleLightOpenTime;
		}
		public int getDebugModeSwitch() {
			return DebugModeSwitch;
		}
		public void setDebugModeSwitch(int debugModeSwitch) {
			DebugModeSwitch = debugModeSwitch;
		}
		
	}
	
	public class VoiceTipParams{
		public String AttendanceTime;
		/**
		 * 上班之前提示语;@打卡成功，@代表姓名（最大设置10位数字）
		 */
		@SerializedName(value = "BeforeJobTip", alternate = {"BeforeJob", "beforeJobTip", "before_job_tip"})
		public String BeforeJobTipEx;
		@SerializedName(value = "AfterJob", alternate = {"AfterJobTip", "afterJob", "afterJobTip", "after_job_tip"})
		public String AfterJobEx;
		@SerializedName(value = "RecognizeSuccessTip", alternate = {"RecognizeSuccessJob"})
		public String RecognizeSuccessTipEx;
		@SerializedName(value = "RecognizeErrorTip", alternate = {"RecognizeErrorJob"})
		public String RecognizeErrorTipEx;
		
		public String getAttendanceTime() {
			return AttendanceTime;
		}
		public void setAttendanceTime(String attendanceTime) {
			AttendanceTime = attendanceTime;
		}
		public String getBeforeJobTip() {
			return BeforeJobTipEx;
		}
		public void setBeforeJobTip(String beforeJobTip) {
			BeforeJobTipEx = beforeJobTip;
		}
		public String getAfterJob() {
			return AfterJobEx;
		}
		public void setAfterJob(String afterJob) {
			AfterJobEx = afterJob;
		}
		public String getRecognizeSuccessTip() {
			return RecognizeSuccessTipEx;
		}
		public void setRecognizeSuccessTip(String recognizeSuccessTip) {
			RecognizeSuccessTipEx = recognizeSuccessTip;
		}
		public String getRecognizeErrorTip() {
			return RecognizeErrorTipEx;
		}
		public void setRecognizeErrorTip(String recognizeErrorTip) {
			RecognizeErrorTipEx = recognizeErrorTip;
		}
		
	}
	
	public class ConsumeParams{
		@SerializedName(value = "PayUrl", alternate = {"PayPort"})
		public String PayUrlEx;
		@SerializedName(value = "QueryUrl", alternate = {"QueryPort"})
		public String QueryUrlEx;
		public String DeviceNum;
		public int SuccessInfoDuration;
		public String DisplayTitle;
		public String BreakfastTime;
		public String LunchTime;
		public String DinnerTime;
		public String SupperTime;
		public String BreakfastAmount;
		public String LunchAmount;
		public String DinnerAmount;
		public String SupperAmount;
		public int IsCardSystemInit;
		public int OnlineWalletID1;
		public int OnlineWalletID2;
		public int OfflineMoneyLimit;
		public int OfflineNumberLimit;
		public int OfflineTimeLimit;
		/**
		 * 离线模式支持开关。0：不支持，1：支持
		 */
		public int OfflineMode;
		/**
		 * 消费模式
			0：单价模式(默认)
			1：时段定值模式
			2：时间段模式
		 */
		public int ConsumeMode;
		public String getPayUrl() {
			return PayUrlEx;
		}
		public void setPayUrl(String payUrl) {
			PayUrlEx = payUrl;
		}
		public String getQueryUrl() {
			return QueryUrlEx;
		}
		public void setQueryUrl(String queryUrl) {
			QueryUrlEx = queryUrl;
		}
		public String getDeviceNum() {
			return DeviceNum;
		}
		public void setDeviceNum(String deviceNum) {
			DeviceNum = deviceNum;
		}
		public int getSuccessInfoDuration() {
			return SuccessInfoDuration;
		}
		public void setSuccessInfoDuration(int successInfoDuration) {
			SuccessInfoDuration = successInfoDuration;
		}
		public String getDisplayTitle() {
			return DisplayTitle;
		}
		public void setDisplayTitle(String displayTitle) {
			DisplayTitle = displayTitle;
		}
		public String getBreakfastTime() {
			return BreakfastTime;
		}
		public void setBreakfastTime(String breakfastTime) {
			BreakfastTime = breakfastTime;
		}
		public String getLunchTime() {
			return LunchTime;
		}
		public void setLunchTime(String lunchTime) {
			LunchTime = lunchTime;
		}
		public String getDinnerTime() {
			return DinnerTime;
		}
		public void setDinnerTime(String dinnerTime) {
			DinnerTime = dinnerTime;
		}
		public String getSupperTime() {
			return SupperTime;
		}
		public void setSupperTime(String supperTime) {
			SupperTime = supperTime;
		}
		public String getBreakfastAmount() {
			return BreakfastAmount;
		}
		public void setBreakfastAmount(String breakfastAmount) {
			BreakfastAmount = breakfastAmount;
		}
		public String getLunchAmount() {
			return LunchAmount;
		}
		public void setLunchAmount(String lunchAmount) {
			LunchAmount = lunchAmount;
		}
		public String getDinnerAmount() {
			return DinnerAmount;
		}
		public void setDinnerAmount(String dinnerAmount) {
			DinnerAmount = dinnerAmount;
		}
		public String getSupperAmount() {
			return SupperAmount;
		}
		public void setSupperAmount(String supperAmount) {
			SupperAmount = supperAmount;
		}
		public int getIsCardSystemInit() {
			return IsCardSystemInit;
		}
		public void setIsCardSystemInit(int isCardSystemInit) {
			IsCardSystemInit = isCardSystemInit;
		}
		public int getOnlineWalletID1() {
			return OnlineWalletID1;
		}
		public void setOnlineWalletID1(int onlineWalletID1) {
			OnlineWalletID1 = onlineWalletID1;
		}
		public int getOnlineWalletID2() {
			return OnlineWalletID2;
		}
		public void setOnlineWalletID2(int onlineWalletID2) {
			OnlineWalletID2 = onlineWalletID2;
		}
		public int getOfflineMoneyLimit() {
			return OfflineMoneyLimit;
		}
		public void setOfflineMoneyLimit(int offlineMoneyLimit) {
			OfflineMoneyLimit = offlineMoneyLimit;
		}
		public int getOfflineNumberLimit() {
			return OfflineNumberLimit;
		}
		public void setOfflineNumberLimit(int offlineNumberLimit) {
			OfflineNumberLimit = offlineNumberLimit;
		}
		public int getOfflineTimeLimit() {
			return OfflineTimeLimit;
		}
		public void setOfflineTimeLimit(int offlineTimeLimit) {
			OfflineTimeLimit = offlineTimeLimit;
		}
		public int getOfflineMode() {
			return OfflineMode;
		}
		public void setOfflineMode(int offlineMode) {
			OfflineMode = offlineMode;
		}
		public int getConsumeMode() {
			return ConsumeMode;
		}
		public void setConsumeMode(int consumeMode) {
			ConsumeMode = consumeMode;
		}
	}

	public BasicParams getBasicParams() {
		return BasicParams;
	}

	public void setBasicParams(BasicParams basicParams) {
		BasicParams = basicParams;
	}

	public FeatureParams getFeatureParams() {
		return FeatureParams;
	}

	public void setFeatureParams(FeatureParams featureParams) {
		FeatureParams = featureParams;
	}

	public RecognitionParams getRecognitionParams() {
		return RecognitionParams;
	}

	public void setRecognitionParams(RecognitionParams recognitionParams) {
		RecognitionParams = recognitionParams;
	}

	public AppParams getAppParams() {
		return AppParams;
	}

	public void setAppParams(AppParams appParams) {
		AppParams = appParams;
	}

	public AccessParams getAccessParams() {
		return AccessParams;
	}

	public void setAccessParams(AccessParams accessParams) {
		AccessParams = accessParams;
	}

	public CameraParams getCameraParams() {
		return CameraParams;
	}

	public void setCameraParams(CameraParams cameraParams) {
		CameraParams = cameraParams;
	}

	public HardWareParams getHardWareParams() {
		return HardWareParams;
	}

	public void setHardWareParams(HardWareParams hardWareParams) {
		HardWareParams = hardWareParams;
	}

	public VoiceTipParams getVoiceTipParams() {
		return VoiceTipParams;
	}

	public void setVoiceTipParams(VoiceTipParams voiceTipParams) {
		VoiceTipParams = voiceTipParams;
	}

	public ConsumeParams getConsumeParams() {
		return ConsumeParams;
	}

	public void setConsumeParams(ConsumeParams consumeParams) {
		ConsumeParams = consumeParams;
	}
	
}
