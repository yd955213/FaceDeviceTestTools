package myGson.tianmo350;

/**
 * 平台人员下发json类
 * @author yangdang
 *
 */
public class SyncEmp {
	/**
	 * 人员 id（唯一标识）
	 */
	public String empId;
	public String name;
	/**
	 * 性别（1 男 0 女 2 未知）
	 */
	public Number sex = 1;
	public Number age;
	public String birthday;
	public String enCode;
	public String cardNum;
	public String mobile;
	public String idNum;
	/**
	 * 名单类型（0 永久名单，1 临时名单（未启用)
	 */
	public Number employeeType = 0;
	public String expTimeStart;
	public String expTimeEnd;
	/**
	 * 头像 base64 数据(去头的base64 字符串)
	 */
	public String headImgData;
	public String feature;
	/**
	 * 头像下载路径（headImgData 中有头像数据时，此字段无效）（未启用）
	 */
	public String headImgPath;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Number getSex() {
		return sex;
	}
	public void setSex(Number sex) {
		this.sex = sex;
	}
	public Number getAge() {
		return age;
	}
	public void setAge(Number age) {
		this.age = age;
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
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public Number getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(Number employeeType) {
		this.employeeType = employeeType;
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
	public String getHeadImgData() {
		return headImgData;
	}
	public void setHeadImgData(String headImgData) {
		this.headImgData = headImgData;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getHeadImgPath() {
		return headImgPath;
	}
	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}
	
	
}
