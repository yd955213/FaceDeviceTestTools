package myGson.das.consume;

public class GetSecretSendGson {
	private String SystemID;
	private String OnlineCashNum;
	private String OnlineSecondCashNum;
	private String EncodeType = "0";
	public String getSystemID() {
		return SystemID;
	}
	public void setSystemID(String systemID) {
		SystemID = systemID;
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
	public String getEncodeType() {
		return EncodeType;
	}
	public void setEncodeType(String encodeType) {
		EncodeType = encodeType;
	}
	
}
