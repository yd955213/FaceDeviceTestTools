package myGson.das;

public class RemoteOpenDoorRequestGson {
	/**设备端需要校验该Mac地址是否是自己绑定的门禁信息相符*/
	private String AccessDeviceUniqueCode;

	public String getAccessDeviceUniqueCode() {
		return AccessDeviceUniqueCode;
	}

	public void setAccessDeviceUniqueCode(String accessDeviceUniqueCode) {
		AccessDeviceUniqueCode = accessDeviceUniqueCode;
	}
	
}
