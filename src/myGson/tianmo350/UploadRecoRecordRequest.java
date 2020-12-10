package myGson.tianmo350;

public class UploadRecoRecordRequest {
	public String Id;
	public String deviceId;
	public String empId;
	public String compareTime;
	public int isLive;
	public Number similarity;
	public int isFrontFace;
	public String recoImgId;
	/**
	 * 来源（0：人脸库对比；1：身份证对比；2 陌生人比对）
	 */
	public int fromType;
	public String idNum;
	public String recoImgData;
	public String chargeMode;
	public String chargeAmount;
	public String online;
	/**
	 * （单位℃）数据范围：33—42
	 */
	public String temp;
	/**
	 * 通行结果：0未通行，1已通行
	 */
	public String isPassing;
	/**
	 * 通行时间：（格式为时间戳）1596448985
	 */
	public String passingTime;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getCompareTime() {
		return compareTime;
	}
	public void setCompareTime(String compareTime) {
		this.compareTime = compareTime;
	}
	public int getIsLive() {
		return isLive;
	}
	public void setIsLive(int isLive) {
		this.isLive = isLive;
	}
	public Number getSimilarity() {
		return similarity;
	}
	public void setSimilarity(Number similarity) {
		this.similarity = similarity;
	}
	public int getIsFrontFace() {
		return isFrontFace;
	}
	public void setIsFrontFace(int isFrontFace) {
		this.isFrontFace = isFrontFace;
	}
	public String getRecoImgId() {
		return recoImgId;
	}
	public void setRecoImgId(String recoImgId) {
		this.recoImgId = recoImgId;
	}
	public int getFromType() {
		return fromType;
	}
	public void setFromType(int fromType) {
		this.fromType = fromType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getRecoImgData() {
		return recoImgData;
	}
	public void setRecoImgData(String recoImgData) {
		this.recoImgData = recoImgData;
	}
	public String getChargeMode() {
		return chargeMode;
	}
	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}
	public String getChargeAmount() {
		return chargeAmount;
	}
	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getIsPassing() {
		return isPassing;
	}
	public void setIsPassing(String isPassing) {
		this.isPassing = isPassing;
	}
	public String getPassingTime() {
		return passingTime;
	}
	public void setPassingTime(String passingTime) {
		this.passingTime = passingTime;
	}
	
	
}
