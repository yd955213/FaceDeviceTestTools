package myGson.das;

public class DevInfo {
	private String macAddr ;
	private String devID;
	private String feature_type = null;
	private String devIP;
	private String devPort;
	private String devModel;
	
	public String getMacAddr() {
		return macAddr;
	}
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	public String getDevID() {
		return devID;
	}
	public void setDevID(String devID) {
		this.devID = devID;
	}
	public String getFeature_type() {
		return feature_type;
	}
	public void setFeature_type(String feature_type) {
		this.feature_type = feature_type;
		setModel();
	}
	public String getDevIP() {
		return devIP;
	}
	public void setDevIP(String devIP) {
		this.devIP = devIP;
	}
	public String getDevPort() {
		return devPort;
	}
	public void setDevPort(String devPort) {
		this.devPort = devPort;
	}
	public String getDevModel() {
		return devModel;
	}
	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}
	
	/**
	 * 设置界面显示面板模型
	 * @return 
	 */
	private void setModel() {
		if (null != feature_type && feature_type.length() > 0) {
			switch (feature_type) {
			case "350":
				devModel = "350";
				break;
			default:
				devModel = "devParameter";
				break;
			}
		}
	}
	
}
