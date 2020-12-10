package myGson.tianmo350;
/**
 * 查询时段设置
 * @author yangdang
 *
 */
public class FrameSettings {
	public Number cameraMode = 2;
	public Number cameraExposure = 136;
	public Number liveSensitivity = 3;
	public Number liveThreshold = 65;
	public Number detectThreshold = 90;
	public String startTime;
	public String endTime;
	public Number settingType;
	
	
	public Number getCameraMode() {
		return cameraMode;
	}
	public void setCameraMode(Number cameraMode) {
		this.cameraMode = cameraMode;
	}
	public Number getCameraExposure() {
		return cameraExposure;
	}
	public void setCameraExposure(Number cameraExposure) {
		this.cameraExposure = cameraExposure;
	}
	public Number getLiveSensitivity() {
		return liveSensitivity;
	}
	public void setLiveSensitivity(Number liveSensitivity) {
		this.liveSensitivity = liveSensitivity;
	}
	public Number getLiveThreshold() {
		return liveThreshold;
	}
	public void setLiveThreshold(Number liveThreshold) {
		this.liveThreshold = liveThreshold;
	}
	public Number getDetectThreshold() {
		return detectThreshold;
	}
	public void setDetectThreshold(Number detectThreshold) {
		this.detectThreshold = detectThreshold;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Number getSettingType() {
		return settingType;
	}
	public void setSettingType(Number settingType) {
		this.settingType = settingType;
	}
	
	
}
