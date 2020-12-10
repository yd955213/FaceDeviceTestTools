package comm.myLibraryLoad.jvt;

/**
 * 测试用：验证距离设置参数丢失部分问题
 * @author yangdang
 *
 */
public class JvtGetDeviceParamGson {

	
	public JvtGetDeviceParamGson(){
		AccessParams accessParams = new AccessParams();
		AppParams appParams  = new AppParams();
		BasicParams basicParams  = new BasicParams();
		CameraParams cameraParams  = new CameraParams();
		ConsumeParams consumeParams  = new ConsumeParams();
		FeatureParams featureParams  = new FeatureParams();
		HardWareParams hardWareParams  = new HardWareParams();
		RecognitionParams recognitionParams  = new RecognitionParams();
		VoiceTipParams voiceTipParams  = new VoiceTipParams();
		setAccessParams(accessParams);
		setAppParams(appParams);
		setBasicParams(basicParams);
		setCameraParams(cameraParams);
		setConsumeParams(consumeParams);
		setFeatureParams(featureParams);
		setHardWareParams(hardWareParams);
		setRecognitionParams(recognitionParams);
		setVoiceTipParams(voiceTipParams);
	}
	
	public BasicParams BasicParams;
	public class BasicParams{
		public String DeviceUniqueCode;
		public String DeviceIP = "";
		public int DevicePort = 8015;
		public int DeviceType = 4;
		public String DeviceName = "厂测测试";
		public String ServerIP;
		public int ServerPort ;
		public String SystemID; 
		public int IsAutoRestart = 2;
		public String DailyRestartTime = "02:00:00" ;
		public String OpenDoorPassword = "123456";
		public int IsUploadPassImg = 1;
		public int RelayTime = 100;
		public int TriggerActionInterval = 1000;
		public int QrCodeSwitch = 2;
		public int IsSupportCard = 1;
		public int WiegandType = 34;
		public int WiegandIn = 1;
		public int WiegandOut = 2;
		public int MainUIType = 1;
		public int EnableScreenSaver = 1;
		public int HeartBeatInterval = 5000;
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
		public String getDeviceName() {
			return DeviceName;
		}
		public void setDeviceName(String deviceName) {
			DeviceName = deviceName;
		}
		public String getServerIP() {
			return ServerIP;
		}
		public void setServerIP(String serverIP) {
			ServerIP = serverIP;
		}
		public String getSystemID() {
			return SystemID;
		}
		public void setSystemID(String systemID) {
			SystemID = systemID;
		}
		public String getDailyRestartTime() {
			return DailyRestartTime;
		}
		public void setDailyRestartTime(String dailyRestartTime) {
			DailyRestartTime = dailyRestartTime;
		}
		public String getOpenDoorPassword() {
			return OpenDoorPassword;
		}
		public void setOpenDoorPassword(String openDoorPassword) {
			OpenDoorPassword = openDoorPassword;
		}
		public int getDevicePort() {
			return DevicePort;
		}
		public void setDevicePort(int devicePort) {
			DevicePort = devicePort;
		}
		public int getDeviceType() {
			return DeviceType;
		}
		public void setDeviceType(int deviceType) {
			DeviceType = deviceType;
		}
		public int getServerPort() {
			return ServerPort;
		}
		public void setServerPort(int serverPort) {
			ServerPort = serverPort;
		}
		public int getIsAutoRestart() {
			return IsAutoRestart;
		}
		public void setIsAutoRestart(int isAutoRestart) {
			IsAutoRestart = isAutoRestart;
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
		public int getHeartBeatInterval() {
			return HeartBeatInterval;
		}
		public void setHeartBeatInterval(int heartBeatInterval) {
			HeartBeatInterval = heartBeatInterval;
		}
		
	}
	
	public FeatureParams FeatureParams;
	public class FeatureParams{
		/*
		 * 1:云从
			2:旷世
			3:地平线
			4:商汤
			5:宇泛
			99:其它
		 */
		public int FeatureType = 4;
		public String FeatureSDKValue ;
		public String FeatureVersion ;
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
	
	public RecognitionParams RecognitionParams;
	public class RecognitionParams {
		public String SimilityThreshold = "75.00";
		public String QualityThreshold = "80.00" ;
		public int MinFacePixel = 30;
		public int MaxFacePixel = 1000;
		public int IsAlive = 2 ;
		public Double LivingThreshold = 80D ;
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
			return LivingThreshold;
		}
		public void setLivingThreshold(Double livingThreshold) {
			LivingThreshold = livingThreshold;
		}
		
	}
	
	public AppParams AppParams;
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
	
	public AccessParams AccessParams;
	public class AccessParams{
		public String AccessDeviceIP = "192.168.11.201";
		public String AccessDeviceUniqueCode = "HENHJ0S";

		public int AccessDoorID = 0;
		public int UDPPort = 100;
		public String getAccessDeviceIP() {
			return AccessDeviceIP;
		}
		public void setAccessDeviceIP(String accessDeviceIP) {
			AccessDeviceIP = accessDeviceIP;
		}
		public String getAccessDeviceUniqueCode() {
			return AccessDeviceUniqueCode;
		}
		public void setAccessDeviceUniqueCode(String accessDeviceUniqueCode) {
			AccessDeviceUniqueCode = accessDeviceUniqueCode;
		}
		public int getAccessDoorID() {
			return AccessDoorID;
		}
		public void setAccessDoorID(int accessDoorID) {
			AccessDoorID = accessDoorID;
		}
		public int getUDPPort() {
			return UDPPort;
		}
		public void setUDPPort(int uDPPort) {
			UDPPort = uDPPort;
		}
		
	}
	
	public CameraParams CameraParams;
	public class CameraParams{
		public int CameraMode = 2;
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
	
	public HardWareParams HardWareParams;
	public class HardWareParams{
		public String SuppleLightOpenTime;
		/**
		 * 补光灯模式 0:设备不支持 1:自动 2:常开 3:常关
		 */
		public int SuppleLightMode = 2;
		public int DebugModeSwitch = 2;
		public String getSuppleLightOpenTime() {
			return SuppleLightOpenTime;
		}
		public void setSuppleLightOpenTime(String suppleLightOpenTime) {
			SuppleLightOpenTime = suppleLightOpenTime;
		}
		public int getSuppleLightMode() {
			return SuppleLightMode;
		}
		public void setSuppleLightMode(int suppleLightMode) {
			SuppleLightMode = suppleLightMode;
		}
		public int getDebugModeSwitch() {
			return DebugModeSwitch;
		}
		public void setDebugModeSwitch(int debugModeSwitch) {
			DebugModeSwitch = debugModeSwitch;
		}
		
	}

	public VoiceTipParams VoiceTipParams;
	public class VoiceTipParams{
		public String AttendanceTime = "08:30-18:00";
		public String BeforeJobTip= "@打卡成功";
		public String AfterJobTip = "@上班一天辛苦了";
		public String RecognizeSuccessTip = "@识别成功";
		public String RecognizeErrorTip = "未登记";
		public String getAttendanceTime() {
			return AttendanceTime;
		}
		public void setAttendanceTime(String attendanceTime) {
			AttendanceTime = attendanceTime;
		}
		public String getBeforeJobTip() {
			return BeforeJobTip;
		}
		public void setBeforeJobTip(String beforeJobTip) {
			BeforeJobTip = beforeJobTip;
		}
		public String getAfterJobTip() {
			return AfterJobTip;
		}
		public void setAfterJobTip(String afterJobTip) {
			AfterJobTip = afterJobTip;
		}
		public String getRecognizeSuccessTip() {
			return RecognizeSuccessTip;
		}
		public void setRecognizeSuccessTip(String recognizeSuccessTip) {
			RecognizeSuccessTip = recognizeSuccessTip;
		}
		public String getRecognizeErrorTip() {
			return RecognizeErrorTip;
		}
		public void setRecognizeErrorTip(String recognizeErrorTip) {
			RecognizeErrorTip = recognizeErrorTip;
		}
		
	}
	
	public ConsumeParams ConsumeParams;
	public class ConsumeParams{
		public String PayUrl ;
		public String QueryUrl;
		public String DeviceNum ;
		public int SuccessInfoDuration;
		public String DisplayTitle;
		public String BreakfastTime;
		public String LunchTime;
		public String SupperTime;
		public String DinnerTime;
		public String BreakfastAmount;
		public String LunchAmount;
		public String SupperAmount;
		public String DinnerAmount;
		public int IsCardSystemInit;
//		public String CardSystemID = "00034900";
		public int OnlineWalletID1;
		public int OnlineWalletID2;
		public int OfflineMoneyLimit;
		public int OfflineNumberLimit;
		public int OfflineTimeLimit;
		public int OfflineMode;
		public int ConsumeMode;
		public String getPayUrl() {
			return PayUrl;
		}
		public void setPayUrl(String payUrl) {
			PayUrl = payUrl;
		}
		public String getQueryUrl() {
			return QueryUrl;
		}
		public void setQueryUrl(String queryUrl) {
			QueryUrl = queryUrl;
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
		public String getSupperTime() {
			return SupperTime;
		}
		public void setSupperTime(String supperTime) {
			SupperTime = supperTime;
		}
		public String getDinnerTime() {
			return DinnerTime;
		}
		public void setDinnerTime(String dinnerTime) {
			DinnerTime = dinnerTime;
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
		public String getSupperAmount() {
			return SupperAmount;
		}
		public void setSupperAmount(String supperAmount) {
			SupperAmount = supperAmount;
		}
		public String getDinnerAmount() {
			return DinnerAmount;
		}
		public void setDinnerAmount(String dinnerAmount) {
			DinnerAmount = dinnerAmount;
		}
		public int getIsCardSystemInit() {
			return IsCardSystemInit;
		}
		public void setIsCardSystemInit(int isCardSystemInit) {
			IsCardSystemInit = isCardSystemInit;
		}
//		public String getCardSystemID() {
//			return CardSystemID;
//		}
//		public void setCardSystemID(String cardSystemID) {
//			CardSystemID = cardSystemID;
//		}
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
