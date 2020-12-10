package myGson.tianmo350;

import com.google.gson.annotations.SerializedName;

public class GetDeviceEmployeeForSyncRequest {
	@SerializedName(value = "Id", alternate = {"id"})
	public String Id;
	public String IsRegister;
	public String empId;
	public String realName;
	public String deptName;
	public String sex;
	public String birthday;
	public String enCode;
	public String IdcardNum;
	public String idNum;
	public String mobile;
	public String headImgId;
	public String headImgPath;
	public String headImgData;
	public String auth;
	public String expTimeStart;
	public String expTimeEnd;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getIsRegister() {
		return IsRegister;
	}
	public void setIsRegister(String isRegister) {
		IsRegister = isRegister;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEnCode() {
		return enCode;
	}
	public void setEnCode(String enCode) {
		this.enCode = enCode;
	}
	public String getIdcardNum() {
		return IdcardNum;
	}
	public void setIdcardNum(String idcardNum) {
		IdcardNum = idcardNum;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHeadImgId() {
		return headImgId;
	}
	public void setHeadImgId(String headImgId) {
		this.headImgId = headImgId;
	}
	public String getHeadImgPath() {
		return headImgPath;
	}
	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}
	public String getHeadImgData() {
		return headImgData;
	}
	public void setHeadImgData(String headImgData) {
		this.headImgData = headImgData;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getExpTimeStart() {
		return expTimeStart;
	}
	public void setExpTimeStart(String expTimeStart) {
		this.expTimeStart = expTimeStart;
	}
	public String getExpTimeEnd() {
		return expTimeEnd;
	}
	public void setExpTimeEnd(String expTimeEnd) {
		this.expTimeEnd = expTimeEnd;
	}
	
	
}
