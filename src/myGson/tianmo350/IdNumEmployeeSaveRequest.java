package myGson.tianmo350;

public class IdNumEmployeeSaveRequest {
	/**
	 * 1：员工库；2：访客库；3：外派库
	 */
	public int libraryType;
	public String idNum;
	public String realName;
	public String address;
	public String headImgData;
	public int getLibraryType() {
		return libraryType;
	}
	public void setLibraryType(int libraryType) {
		this.libraryType = libraryType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHeadImgData() {
		return headImgData;
	}
	public void setHeadImgData(String headImgData) {
		this.headImgData = headImgData;
	}
	
	
}
