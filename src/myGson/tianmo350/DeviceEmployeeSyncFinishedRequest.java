package myGson.tianmo350;

import com.google.gson.annotations.SerializedName;

public class DeviceEmployeeSyncFinishedRequest {
	@SerializedName(value = "Id", alternate = {"ID", "id"})
	public String Id;
	public String DeviceId;
	/**
	 * 大于等于 0，设备注册人员成
		功；否则为失败
		失败类型：
		-1 为人脸图片文件过大（限制
		不得超过 2MB） -2 为未检测到人脸
		-3 人脸瞳距小于 128像素（目
		前未启用）
		-4 人脸图片质量太差
		-5 删除旧注册照片失败
		-6 注册人脸图片失败
		-7 无此删除人员信息
		-8 该人脸已注册，同时返回
		EmpIdExist 已存在的人员
		empId
		EmpIdDuplicate 重复的人员
		empId
		RealNameExist 已存在人
		员姓名
		RealNameDuplicate 重复人
		员姓名
		-9 未注册，且没有人脸图片
		-10 为头像未保存成功
		同时返回 EmpIdExist 已存
		在的人员 empId
	 */
	public int ResultCode;
	/**
	 * 已存在的人员 empId,ResultCode 为-8 或者-10 时返回
	 */
	public String EmpIdExist;
	/**
	 * 重复的人员 empId, ResultCodeate 为-8 时返回
	 */
	public String EmpIdDuplicate;
	/**
	 * 已存在的人员姓名, ResultCodest 为-8 时返回
	 */
	public String RealNameExist;
	/**
	 * 重复的人员姓名, ResultCode 为-8 时返回
	 */
	public String RealNameDuplicate;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getDeviceId() {
		return DeviceId;
	}
	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}
	public int getResultCode() {
		return ResultCode;
	}
	public void setResultCode(int resultCode) {
		ResultCode = resultCode;
	}
	public String getEmpIdExist() {
		return EmpIdExist;
	}
	public void setEmpIdExist(String empIdExist) {
		EmpIdExist = empIdExist;
	}
	public String getEmpIdDuplicate() {
		return EmpIdDuplicate;
	}
	public void setEmpIdDuplicate(String empIdDuplicate) {
		EmpIdDuplicate = empIdDuplicate;
	}
	public String getRealNameExist() {
		return RealNameExist;
	}
	public void setRealNameExist(String realNameExist) {
		RealNameExist = realNameExist;
	}
	public String getRealNameDuplicate() {
		return RealNameDuplicate;
	}
	public void setRealNameDuplicate(String realNameDuplicate) {
		RealNameDuplicate = realNameDuplicate;
	}
	
	
}
