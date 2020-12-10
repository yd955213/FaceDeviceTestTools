package myGson.tianmo350;

public class DeviceConfig {
	/**识别阈值(调整范围：5-95) */
	public String discernThreshold = "65"; 
	/**身份证阈值(调整范围：35-95) */
	public String idCardThreshold = "40"; 
	/**
	 * 活体灵敏度 (0:关闭 8:慢 5:适中 3:快)
	 */
	public String livingSensitivity = "3"; 
	/**
	 * 活体阈值 (调整范围 ：45-95)
	 */
	public String livingThreshold = "65";
	/**
	 * 识别模式 (0:人脸验证 1:人或认证2:人卡验证 3:人证验证)
	 */
	public String discernMode = "0"; 
	/**
	 * 最小像素设置 (调整范围：80px—512px)
	 */
	public String minPixel = "120"; 
	/**
	 * 检测阈值设置 (调整范围 60-100)
	 */
	public String detectThreshold = "90"; 
	/**识别间隔设置 ( 调整范围 500ms-5000ms)*/
	public String discernInterval = "1500"; 
	/** 相机模式设置 (调整范围 0:室内模式 1:室外模式 2:智能模式)*/
	public String cameraMode = "2"; 
	/**自定义每日重启时间 调整范围：0:00-23:59 */
	public String rebootTime = "2019-07-0803:00:00"; 
	/** 韦根输出 (调整范围：0:正序 1:反序)*/
	public Number wiegandOut = 0; 
	/** 韦根输入 (调整范围：0:正序 1:反序)*/
	public Number wiegandIn = 0; 
	/**设备编码 */
	public String deviceCode = ""; 
	/**账户安全管理密码 */
	public String password = ""; 
	/**识别界面标题 */
	public String appTitle = ""; 
	/**识别失败提示次数(0-10 之间) */
	public Number recoFailCount = 0; 
	/**智能补光灯是否开启(0 关闭,1 开启) */
	public Number ledOn = 1;
	/** 屏保是否启用(0 关闭,1 开启)*/ 
	public Number screenOn = 1;
	/** 出现屏保时间(分钟为单位，有效范围 1-15)*/ 
	public Number screenTime = 3; 
	/**韦根类型(26 或 34) */
	public Number wiegandType = 26; 
	/** 是否上报陌生人记录(0 关闭,1 开启)*/
	public Number reportStranger = 0; 
	/**HTTP远程服务器地址（记录上报地址） */
	public String reqAddress = ""; 
	/**HTTP服务通讯端口 */
	public String reqPort = "";
	/**屏保URL地址 */ 
	public String screenUrl = ""; 
//	/**进入屏保时间 */
//	public String screenTime = "";
	/**屏保时钟透明度 */ 
	public String keepAlpha = ""; 
	/** 屏保时钟字体颜色*/
	public String keepColor = "";
	/**测温对准点颜色 */ 
	public int pointColor ; 
	/**测温额头区颜色 */
	public String pointRectColor = ""; 
	/**测温框颜色 */
	public String tempRectColor = ""; 
	/**测温框透明度 */
	public String tempRectColorAlpha = "";
	/**打开和关闭测温模式（打开0，关闭1） */ 
	public String tempMode = "0"; 
	/**自适环温补偿开关 */
	public String enMakeup = "false"; 
	/**测温16A和32A版本默认打开 */
	public String enableTemp = "false"; 
	/*告警体温数值（单位：℃/℉）* */
	public String tempAlarm = "37.3"; 
	/**最低测量体温数值（单位：℃/℉） */
	public String tempFilter = "35.8"; 
	/**是否打开华氏度转换 */
	public String isTempF = "false"; 
	/**语音播报速度值（TTS转语音有效） */
	public String speakSpeed = "1.5";
	
	public String getDiscernThreshold() {
		return discernThreshold;
	}
	public void setDiscernThreshold(String discernThreshold) {
		this.discernThreshold = discernThreshold;
	}
	public String getIdCardThreshold() {
		return idCardThreshold;
	}
	public void setIdCardThreshold(String idCardThreshold) {
		this.idCardThreshold = idCardThreshold;
	}
	public String getLivingSensitivity() {
		return livingSensitivity;
	}
	public void setLivingSensitivity(String livingSensitivity) {
		this.livingSensitivity = livingSensitivity;
	}
	public String getLivingThreshold() {
		return livingThreshold;
	}
	public void setLivingThreshold(String livingThreshold) {
		this.livingThreshold = livingThreshold;
	}
	public String getDiscernMode() {
		return discernMode;
	}
	public void setDiscernMode(String discernMode) {
		this.discernMode = discernMode;
	}
	public String getMinPixel() {
		return minPixel;
	}
	public void setMinPixel(String minPixel) {
		this.minPixel = minPixel;
	}
	public String getDetectThreshold() {
		return detectThreshold;
	}
	public void setDetectThreshold(String detectThreshold) {
		this.detectThreshold = detectThreshold;
	}
	public String getDiscernInterval() {
		return discernInterval;
	}
	public void setDiscernInterval(String discernInterval) {
		this.discernInterval = discernInterval;
	}
	public String getCameraMode() {
		return cameraMode;
	}
	public void setCameraMode(String cameraMode) {
		this.cameraMode = cameraMode;
	}
	public String getRebootTime() {
		return rebootTime;
	}
	public void setRebootTime(String rebootTime) {
		this.rebootTime = rebootTime;
	}
	public Number getWiegandOut() {
		return wiegandOut;
	}
	public void setWiegandOut(Number wiegandOut) {
		this.wiegandOut = wiegandOut;
	}
	public Number getWiegandIn() {
		return wiegandIn;
	}
	public void setWiegandIn(Number wiegandIn) {
		this.wiegandIn = wiegandIn;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAppTitle() {
		return appTitle;
	}
	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}
	public Number getRecoFailCount() {
		return recoFailCount;
	}
	public void setRecoFailCount(Number recoFailCount) {
		this.recoFailCount = recoFailCount;
	}
	public Number getLedOn() {
		return ledOn;
	}
	public void setLedOn(Number ledOn) {
		this.ledOn = ledOn;
	}
	public Number getScreenOn() {
		return screenOn;
	}
	public void setScreenOn(Number screenOn) {
		this.screenOn = screenOn;
	}
	public Number getScreenTime() {
		return screenTime;
	}
	public void setScreenTime(Number screenTime) {
		this.screenTime = screenTime;
	}
	public Number getWiegandType() {
		return wiegandType;
	}
	public void setWiegandType(Number wiegandType) {
		this.wiegandType = wiegandType;
	}
	public Number getReportStranger() {
		return reportStranger;
	}
	public void setReportStranger(Number reportStranger) {
		this.reportStranger = reportStranger;
	}
	public String getReqAddress() {
		return reqAddress;
	}
	public void setReqAddress(String reqAddress) {
		this.reqAddress = reqAddress;
	}
	public String getReqPort() {
		return reqPort;
	}
	public void setReqPort(String reqPort) {
		this.reqPort = reqPort;
	}
	public String getScreenUrl() {
		return screenUrl;
	}
	public void setScreenUrl(String screenUrl) {
		this.screenUrl = screenUrl;
	}
	public String getKeepAlpha() {
		return keepAlpha;
	}
	public void setKeepAlpha(String keepAlpha) {
		this.keepAlpha = keepAlpha;
	}
	public String getKeepColor() {
		return keepColor;
	}
	public void setKeepColor(String keepColor) {
		this.keepColor = keepColor;
	}
	public int getPointColor() {
		return pointColor;
	}
	public void setPointColor(int pointColor) {
		this.pointColor = pointColor;
	}
	public String getPointRectColor() {
		return pointRectColor;
	}
	public void setPointRectColor(String pointRectColor) {
		this.pointRectColor = pointRectColor;
	}
	public String getTempRectColor() {
		return tempRectColor;
	}
	public void setTempRectColor(String tempRectColor) {
		this.tempRectColor = tempRectColor;
	}
	public String getTempRectColorAlpha() {
		return tempRectColorAlpha;
	}
	public void setTempRectColorAlpha(String tempRectColorAlpha) {
		this.tempRectColorAlpha = tempRectColorAlpha;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	public String getEnMakeup() {
		return enMakeup;
	}
	public void setEnMakeup(String enMakeup) {
		this.enMakeup = enMakeup;
	}
	public String getEnableTemp() {
		return enableTemp;
	}
	public void setEnableTemp(String enableTemp) {
		this.enableTemp = enableTemp;
	}
	public String getTempAlarm() {
		return tempAlarm;
	}
	public void setTempAlarm(String tempAlarm) {
		this.tempAlarm = tempAlarm;
	}
	public String getTempFilter() {
		return tempFilter;
	}
	public void setTempFilter(String tempFilter) {
		this.tempFilter = tempFilter;
	}
	public String getIsTempF() {
		return isTempF;
	}
	public void setIsTempF(String isTempF) {
		this.isTempF = isTempF;
	}
	public String getSpeakSpeed() {
		return speakSpeed;
	}
	public void setSpeakSpeed(String speakSpeed) {
		this.speakSpeed = speakSpeed;
	}
	
	
}
