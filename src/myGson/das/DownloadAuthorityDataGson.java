package myGson.das;

import tools.SystemTimes;

public class DownloadAuthorityDataGson {
	/**
	 * 生成权限时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	private  String StartTime = SystemTimes.getSysTime1();
	/**
	 * 人员唯一标识
	 */
	private  String UniqueCode ;
	/**
	 * 人员类型
		0:一卡通系统员工
		1:访客
		99:其他
	 */
	private  int PersonType;
	/**
	 * 人员编号
	 */
	private  String PersonNo;
	/**
	 * 姓名
	 */
	private  String PersonName;
	/**
	 * 性别
		0:保密
		1:男
		2:女
	 */
	private  int Gender;
	/**
	 * 照片base64值
	 */
	private  String Photo;
	/**
	 * 照片特征值
	 */
	private  String Character;
	/**
	 * 人员部门
	 */
	private  String DptName  = "测试部";
	/**
	 * 证件类型 1:身份证 2:港澳通行证 3:驾驶证 4:护照 99:其他
	 */
	private  String IDType ;
	/**
	 * 证件号码
		为空:不支持刷证件
		非空:支持刷证件
	 */
	private  String IDNo;
	/**
	 * 卡片号码
		为空:不支持刷卡
		非空:根据一卡通类型返回卡流水号或卡固有号
	 */
	private  String CardNo;
	/**
	 * 身份合法性
		Y:合法，需要设备保存身份数据
		N:非法，需要设备删除人脸及身份数据
	 */
	private  String IsLegal = "Y";
	/**
	 * 人脸权限启用时间
	 */
	private  String FaceStartUseTime = SystemTimes.getSysTime1();
	/**
	 * 人脸权限停止使用时间
	 */
	private  String FaceStopUseTime = "2099-12-30 23:59:59";
	/**
	 * 卡片启用时间
	 */
	private  String CardStartUseTime = SystemTimes.getSysTime1();
	/**
	 * 卡片停止使用时间
	 */
	private  String CardEndUseTime = "2099-12-30 23:59:59";
	
	/**
	 * 生成权限时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	public String getStartTime() {
		return StartTime;
	}
	/**
	 * 生成权限时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	/**
	 * 人员唯一标识
	 * @return
	 */
	public String getUniqueCode() {
		return UniqueCode;
	}
	/**
	 * 人员唯一标识
	 * @param uniqueCode
	 */
	public void setUniqueCode(String uniqueCode) {
		UniqueCode = uniqueCode;
	}
	/**
	 * 人员类型
		0:一卡通系统员工
		1:访客
		99:其他
	 * @return
	 */
	public int getPersonType() {
		return PersonType;
	}
	/**
	 * 人员类型
		0:一卡通系统员工
		1:访客
		99:其他
	 * @return
	 */
	public void setPersonType(int personType) {
		PersonType = personType;
	}
	/**
	 * 人员编号
	 * @return
	 */
	public String getPersonNo() {
		return PersonNo;
	}
	/**
	 * 人员编号
	 * @param personNo
	 */
	public void setPersonNo(String personNo) {
		PersonNo = personNo;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName) {
		PersonName = personName;
	}
	/**
	 * 性别
		0:保密
		1:男
		2:女
	 * @return
	 */
	public int getGender() {
		return Gender;
	}
	/**
	 * 性别
		0:保密
		1:男
		2:女
	 * @param gender
	 */
	public void setGender(int gender) {
		Gender = gender;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getCharacter() {
		return Character;
	}

	public void setCharacter(String character) {
		Character = character;
	}

	public String getDptName() {
		return DptName;
	}

	public void setDptName(String dptName) {
		DptName = dptName;
	}
	/**
	 * 证件类型 1:身份证 2:港澳通行证 3:驾驶证 4:护照 99:其他
	 * @return
	 */
	public String getIDType() {
		return IDType;
	}
	/**
	 * 证件类型 1:身份证 2:港澳通行证 3:驾驶证 4:护照 99:其他
	 * @param iDType
	 */
	public void setIDType(String iDType) {
		IDType = iDType;
	}
	/**
	 * 证件号码
		为空:不支持刷证件
		非空:支持刷证件
	 * @return
	 */
	public String getIDNo() {
		return IDNo;
	}
	/**
	 * 证件号码
		为空:不支持刷证件
		非空:支持刷证件
	 * @param iDNo
	 */
	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}
	/**
	 * 卡片号码
		为空:不支持刷卡
		非空:根据一卡通类型返回卡流水号或卡固有号
	 * @return
	 */
	public String getCardNo() {
		return CardNo;
	}
	/**
	 * 卡片号码
		为空:不支持刷卡
		非空:根据一卡通类型返回卡流水号或卡固有号
	 * @param cardNo
	 */
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	/**
	 * 身份合法性
		Y:合法，需要设备保存身份数据
		N:非法，需要设备删除人脸及身份数据
	 * @return
	 */
	public String getIsLegal() {
		return IsLegal;
	}
	/**
	 * 身份合法性
		Y:合法，需要设备保存身份数据
		N:非法，需要设备删除人脸及身份数据
	 * @param isLegal
	 */
	public void setIsLegal(String isLegal) {
		IsLegal = isLegal;
	}

	public String getFaceStartUseTime() {
		return FaceStartUseTime;
	}

	public void setFaceStartUseTime(String faceStartUseTime) {
		FaceStartUseTime = faceStartUseTime;
	}

	public String getFaceStopUseTime() {
		return FaceStopUseTime;
	}

	public void setFaceStopUseTime(String faceStopUseTime) {
		FaceStopUseTime = faceStopUseTime;
	}

	public String getCardStartUseTime() {
		return CardStartUseTime;
	}

	public void setCardStartUseTime(String cardStartUseTime) {
		CardStartUseTime = cardStartUseTime;
	}

	public String getCardEndUseTime() {
		return CardEndUseTime;
	}

	public void setCardEndUseTime(String cardEndUseTime) {
		CardEndUseTime = cardEndUseTime;
	}
	

}
