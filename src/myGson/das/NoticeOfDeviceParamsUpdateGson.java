package myGson.das;

public class NoticeOfDeviceParamsUpdateGson {
	public NoticeOfDeviceParamsUpdateGson() {
	}
	public BasicParams getBasicParams() {
		return BasicParams;
	}
	public void setBasicParams(BasicParams basicParams) {
		BasicParams = basicParams;
	}
	public RecognitionParams getRecognitionParams() {
		return RecognitionParams;
	}
	public void setRecognitionParams(RecognitionParams recognitionParams) {
		RecognitionParams = recognitionParams;
	}
	public HardWareParams getHardWareParams() {
		return HardWareParams;
	}
	public void setHardWareParams(HardWareParams hardWareParams) {
		HardWareParams = hardWareParams;
	}
	public BasicParams BasicParams;
	public RecognitionParams RecognitionParams;
	public HardWareParams HardWareParams;
	
	
	public class BasicParams{
		private String DeviceName;
		private String ServerIP;
		private int ServerPort;
		private int IsAutoRestart;
		private String DailyRestartTime;
		private int QrCodeSwitch;
		private int IsSupportCard;
		private int WiegandType;
		private int HeartBeatInterval;
		
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
		public String getDailyRestartTime() {
			return DailyRestartTime;
		}
		public void setDailyRestartTime(String dailyRestartTime) {
			DailyRestartTime = dailyRestartTime;
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
		public int getHeartBeatInterval() {
			return HeartBeatInterval;
		}
		public void setHeartBeatInterval(int heartBeatInterval) {
			HeartBeatInterval = heartBeatInterval;
		}
		
		
	}
	public class RecognitionParams{
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
		private String SimilityThreshold;
		private String QualityThreshold;
		private int MinFacePixel;
		private int MaxFacePixel;
		private int IsAlive;
		private Double LivingThreshold;
		
		
	}
	public class HardWareParams{
		public int getDebugModeSwitch() {
			return DebugModeSwitch;
		}

		public void setDebugModeSwitch(int debugModeSwitch) {
			DebugModeSwitch = debugModeSwitch;
		}

		private int DebugModeSwitch;
		
		
	}
}
