package myGson.tianmo350;

import com.google.gson.annotations.SerializedName;

public class GetDeviceEmployeeForSyncResponse {
	@SerializedName(value = "Id", alternate = {"id"})
	public String Id;
	public String totalEmp;
	public String deviceVersionNum;
	public String deviceModel;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTotalEmp() {
		return totalEmp;
	}
	public void setTotalEmp(String totalEmp) {
		this.totalEmp = totalEmp;
	}
	public String getDeviceVersionNum() {
		return deviceVersionNum;
	}
	public void setDeviceVersionNum(String deviceVersionNum) {
		this.deviceVersionNum = deviceVersionNum;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	
	
}
