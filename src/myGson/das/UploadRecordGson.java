package myGson.das;

import com.google.gson.annotations.SerializedName;

import tools.SystemTimes;

public class UploadRecordGson {
	public int getIsKqUse() {
		return IsKqUse;
	}
	public void setIsKqUse(int isKqUse) {
		IsKqUse = isKqUse;
	}
	public int getPersonType() {
		return PersonType;
	}
	public void setPersonType(int personType) {
		PersonType = personType;
	}
	public int getInOutFlag() {
		return InOutFlag;
	}
	public void setInOutFlag(int inOutFlag) {
		InOutFlag = inOutFlag;
	}
	public String getPassImageId() {
		return PassImageIdEx;
	}
	public void setPassImageId(String passImageId) {
		PassImageIdEx = passImageId;
	}
	@SerializedName(value = "DeviceUniqueCode", alternate = {"DevMac", "DeviceMacAddress"})
	private String DeviceUniqueCodeEx;
	private int RecordID;
	private String RecordTime = SystemTimes.getSysTime1();
	/**
	 * 业务类型
		0:刷脸
		1:刷证件
		2:刷卡片
		3:扫二维码
		4:卡+人脸
		5:事件
		99:其他
	 */
	private int ActionType = 0;
	private int IsKqUse;
	private int DeviceType = 1;
	private String UniqueCode;
	private int PersonType;
	private String CapturePhoto;
	private String SimilarityScore = "98";
	private String SimilarityThreshold ;
	private String QualityScore;
	private String QualityThreshold;
	private String IsAlive = "0";
	private int AccessDoorID;
	private int InOutFlag;
	private String AccessCode;
	private String AccessResult;
	private int IDType;
	private String IDNo;
	private String CardNo;
	//抓拍照片名字，使用UUID算法生成，保证唯一性。照片上传到文件服务器后也是以此命名
	@SerializedName(value = "PassImageId", alternate = {"PassImageUrl"})
	private String PassImageIdEx;
	
	
	public String getDeviceUniqueCode() {
		return DeviceUniqueCodeEx;
	}
	public void setDeviceUniqueCode(String deviceUniqueCode) {
		DeviceUniqueCodeEx = deviceUniqueCode;
	}
	public int getRecordID() {
		return RecordID;
	}
	public void setRecordID(int recordID) {
		RecordID = recordID;
	}
	public String getRecordTime() {
		return RecordTime;
	}
	public void setRecordTime(String recordTime) {
		RecordTime = recordTime;
	}
	public int getActionType() {
		return ActionType;
	}
	public void setActionType(int actionType) {
		ActionType = actionType;
	}
	public int getDeviceType() {
		return DeviceType;
	}
	public void setDeviceType(int deviceType) {
		DeviceType = deviceType;
	}
	public String getUniqueCode() {
		return UniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		UniqueCode = uniqueCode;
	}
	public String getCapturePhoto() {
		return CapturePhoto;
	}
	public void setCapturePhoto(String capturePhoto) {
		CapturePhoto = capturePhoto;
	}
	public String getSimilarityScore() {
		return SimilarityScore;
	}
	public void setSimilarityScore(String similarityScore) {
		SimilarityScore = similarityScore;
	}
	public String getSimilarityThreshold() {
		return SimilarityThreshold;
	}
	public void setSimilarityThreshold(String similarityThreshold) {
		SimilarityThreshold = similarityThreshold;
	}
	public String getQualityScore() {
		return QualityScore;
	}
	public void setQualityScore(String qualityScore) {
		QualityScore = qualityScore;
	}
	public String getQualityThreshold() {
		return QualityThreshold;
	}
	public void setQualityThreshold(String qualityThreshold) {
		QualityThreshold = qualityThreshold;
	}
	public String getIsAlive() {
		return IsAlive;
	}
	public void setIsAlive(String isAlive) {
		IsAlive = isAlive;
	}
	public int getAccessDoorID() {
		return AccessDoorID;
	}
	public void setAccessDoorID(int accessDoorID) {
		AccessDoorID = accessDoorID;
	}
	public String getAccessCode() {
		return AccessCode;
	}
	public void setAccessCode(String accessCode) {
		AccessCode = accessCode;
	}
	public String getAccessResult() {
		return AccessResult;
	}
	public void setAccessResult(String accessResult) {
		AccessResult = accessResult;
	}
	public int getIDType() {
		return IDType;
	}
	public void setIDType(int iDType) {
		IDType = iDType;
	}
	public String getIDNo() {
		return IDNo;
	}
	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	
	
}
