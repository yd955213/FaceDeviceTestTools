package myGson.tianmo350;

public class DeviceInfo350 {
	/**设备id 唯一编码 */
	public String deviceCode;
	/**内部软件版本号 */
	public String appVer;
	/**软件版本号 */
	public String appVerName;
	/**软件编译时间 */
	public String buildTime;
	/**相机固件版本 */
	public String cameraVer;
	/**系统固件版本 */
	public String firmwareVer;
	/**设备语言*/
	public String lang;
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getAppVer() {
		return appVer;
	}
	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}
	public String getAppVerName() {
		return appVerName;
	}
	public void setAppVerName(String appVerName) {
		this.appVerName = appVerName;
	}
	public String getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}
	public String getCameraVer() {
		return cameraVer;
	}
	public void setCameraVer(String cameraVer) {
		this.cameraVer = cameraVer;
	}
	public String getFirmwareVer() {
		return firmwareVer;
	}
	public void setFirmwareVer(String firmwareVer) {
		this.firmwareVer = firmwareVer;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
}
