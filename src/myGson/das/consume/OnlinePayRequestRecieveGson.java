package myGson.das.consume;

public class OnlinePayRequestRecieveGson {
	private String SystemID;
	private String BusinessFlag;
	private String CommunicationUUID;
	private String UniqueCode;
	private String CardNo;
	private String CardFixNum;
	private String ConsumeAmount;
	private String ExtraAmount;
	private String TrackCode;
	/**
	 * 消费结果
OK:消费成功
ERR:消费失败
	 */
	private String ConsumeResult;
	private String ErrCode;
	private String ConfirmPassword;
	public String getSystemID() {
		return SystemID;
	}
	public void setSystemID(String systemID) {
		SystemID = systemID;
	}
	public String getBusinessFlag() {
		return BusinessFlag;
	}
	public void setBusinessFlag(String businessFlag) {
		BusinessFlag = businessFlag;
	}
	public String getCommunicationUUID() {
		return CommunicationUUID;
	}
	public void setCommunicationUUID(String communicationUUID) {
		CommunicationUUID = communicationUUID;
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
	public String getExtraAmount() {
		return ExtraAmount;
	}
	public void setExtraAmount(String extraAmount) {
		ExtraAmount = extraAmount;
	}
	public String getTrackCode() {
		return TrackCode;
	}
	public void setTrackCode(String trackCode) {
		TrackCode = trackCode;
	}
	public String getConsumeResult() {
		return ConsumeResult;
	}
	public void setConsumeResult(String consumeResult) {
		ConsumeResult = consumeResult;
	}
	public String getErrCode() {
		return ErrCode;
	}
	public void setErrCode(String errCode) {
		ErrCode = errCode;
	}
	public String getConfirmPassword() {
		return ConfirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}
	
	
}
