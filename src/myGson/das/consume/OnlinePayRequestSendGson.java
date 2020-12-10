package myGson.das.consume;

import com.testModel.TestParamInit;

public class OnlinePayRequestSendGson {
	private String SystemID = TestParamInit.getSystemID();
	private String CommunicationUUID = Long.toHexString(System.currentTimeMillis()).substring(0,6).toUpperCase();
	private String BusinessFlag = "20";
	private String UniqueCode = "1048577";
	private String CardNo= "645622918";
	private String CardFixNum = "645622918";
	private String ConsumeAmount = "0.01";
	private String SingleConsumeLimitFlag = "00";
	private String DeviceNum = "10003";
	private String DeviceName = "TEST";
	private String DeviceMac;
	private String ConfirmPassword;
	private String TrackCode;
	private String OnlineCashNum = "91";
	private String OnlineSecondCashNum = "92";
	private String OperatorNum = "0";
	public String getSystemID() {
		return SystemID;
	}
	public void setSystemID(String systemID) {
		SystemID = systemID;
	}
	public String getCommunicationUUID() {
		return CommunicationUUID;
	}
	public void setCommunicationUUID(String communicationUUID) {
		CommunicationUUID = communicationUUID;
	}
	public String getBusinessFlag() {
		return BusinessFlag;
	}
	public void setBusinessFlag(String businessFlag) {
		BusinessFlag = businessFlag;
	}
	public String getUniqueCode() {
		return UniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		UniqueCode = uniqueCode;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getCardFixNum() {
		return CardFixNum;
	}
	public void setCardFixNum(String cardFixNum) {
		CardFixNum = cardFixNum;
	}
	public String getConsumeAmount() {
		return ConsumeAmount;
	}
	public void setConsumeAmount(String consumeAmount) {
		ConsumeAmount = consumeAmount;
	}
	public String getSingleConsumeLimitFlag() {
		return SingleConsumeLimitFlag;
	}
	public void setSingleConsumeLimitFlag(String singleConsumeLimitFlag) {
		SingleConsumeLimitFlag = singleConsumeLimitFlag;
	}
	public String getDeviceNum() {
		return DeviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		DeviceNum = deviceNum;
	}
	public String getDeviceName() {
		return DeviceName;
	}
	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}
	public String getDeviceMac() {
		return DeviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		DeviceMac = deviceMac;
	}
	public String getConfirmPassword() {
		return ConfirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}
	public String getTrackCode() {
		return TrackCode;
	}
	public void setTrackCode(String trackCode) {
		TrackCode = trackCode;
	}
	public String getOnlineCashNum() {
		return OnlineCashNum;
	}
	public void setOnlineCashNum(String onlineCashNum) {
		OnlineCashNum = onlineCashNum;
	}
	public String getOnlineSecondCashNum() {
		return OnlineSecondCashNum;
	}
	public void setOnlineSecondCashNum(String onlineSecondCashNum) {
		OnlineSecondCashNum = onlineSecondCashNum;
	}
	public String getOperatorNum() {
		return OperatorNum;
	}
	public void setOperatorNum(String operatorNum) {
		OperatorNum = operatorNum;
	}
	
	
}
