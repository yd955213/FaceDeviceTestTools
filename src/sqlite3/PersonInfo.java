package sqlite3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.annotations.Expose;

import myGson.das.DevInfo;
import tools.SystemTimes;

public class PersonInfo {
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
	@Expose
    private String photoPath ;
	private int personID;
//	private String photoBace64;
	private DevInfo devInfo;
	
	private Vector<String> vt  = null;
	private List<String> list = null; 
	public PersonInfo () {
		vt  = new Vector<String>();
		list = new ArrayList<String>(); 
	}
	
    public Vector<String> toVector() {
    	//按照表格格式形成list
    	vt.clear();
    	vt.add(PersonName);
    	vt.add(DptName);
    	vt.add(getPersonTypeDescrpt(PersonType));
    	vt.add(getSex(Gender));
    	vt.add(CardNo);
    	vt.add(getIdTypeDescription(IDType));
    	vt.add(IDNo);
    	vt.add(FaceStartUseTime);
    	vt.add(FaceStopUseTime);
    	vt.add(CardStartUseTime);
    	vt.add(CardEndUseTime);
    	vt.add(Integer.toString(personID));
    	
    	return vt;
    }
    /**
     * 写人员信息表，并让personID 加1
     */
	public void insertInfoDB_pearson_info(String filePath) {
		
		list = toPearsonInfoList();
		DataBaseExecute.getInstance().insertIntoDB("pearson_info", new ArrayList<String>(), list);
		
	}

	
	 /**
     * 写人员信息表，并让personID 加1
     */
	public void insertInfoDB_pearson_info(String filePath, String photoBase64, int feature_type) {
		
		list = toPearsonInfoList();
		boolean success = DataBaseExecute.getInstance().insertIntoDB("pearson_info", new ArrayList<String>(), list);
		if (success) {
			FaceEmpPhoto faceEmpPhoto = new FaceEmpPhoto();
			faceEmpPhoto.getinitialization(personID, filePath, photoBase64, feature_type); 	
			faceEmpPhoto.insertInfoDB_face_emp_photo();
		}
	}
	
	public void updateInfoDB_pearson_info(String personID) {
		System.out.println("DptName = " + DptName);
		DataBaseExecute.getInstance().updateDB("pearson_info", 
				Arrays.asList("person_id"), 
				Arrays.asList(personID),
				Arrays.asList("person_type", "person_name", "gender", "dpt_name", "id_type", "Id_no", "card_no", "face_start_use_time", "face_stop_use_time", "card_start_use_time", "card_end_use_time", "photo_url", "gmt_modified"), 
				Arrays.asList(Integer.toString(PersonType), PersonName , Integer.toString(Gender), DptName, IDType, IDNo, CardNo, FaceStartUseTime, FaceStopUseTime, CardStartUseTime, CardEndUseTime,photoPath, SystemTimes.getSysTime())); 
	}

	public  void insertTbale(JTable table){
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		Vector<String> vector = toVector();
		tableModel.addRow(vector);
	}
	
	public void updateTbale(JTable table,String personID) {
		// TODO Auto-generated method stub
		
	}

	protected List<String> toPearsonInfoList() {
		
		list.add(null);  //Person_id 自增长
		list.add(UniqueCode);  //unique_code
		list.add(Integer.toString(PersonType));  //person_type
		list.add(PersonNo);  //person_no
		list.add(PersonName);  //person_name
		list.add(Integer.toString(Gender));  //gender
		list.add(DptName);  //dpt_name
		list.add(IDType);  //id_type
		list.add(IDNo);  //Id_no
		list.add(CardNo);  //card_no
		list.add(IsLegal);  //is_legal
		list.add(FaceStartUseTime);  //face_start_use_time
		list.add(FaceStopUseTime);  //face_stop_use_time
		list.add(CardStartUseTime);  //card_start_use_time
		list.add(CardEndUseTime);  //card_end_use_time
		list.add(photoPath); //照片路径
		list.add(SystemTimes.getSysTime());  //gmt_create
		list.add("");  //gmt_modified
//		for (String st : list) {
//			System.out.println(st);
//		}
		return list;
	}
	
	
	protected String getPersonTypeDescrpt(int personType) {
    	String descrpt = "";
    	//0:一卡通系统员工 1:访客 99:其他
    	switch (personType) {
		case 0:
			descrpt = "内部员工";
			break;
		case 1:
			descrpt = "访客";
			break;

		case 99:
			descrpt = "其他 ";
			break;
		default:
			descrpt = "其他";
			break;
		}
    	return descrpt;
    }
    
    protected String getSex(int gender) {
    	String sex = "";
    	//0:保密 1:男 2:女
    	switch (gender) {
		case 0:
			sex = "保密";
			break;
		case 1:
			sex = "男";
			break;

		case 3:
			sex = "女 ";
			break;
		default:
			sex = "保密";
			break;
		}
    	return sex;
    }
    
	private String getIdTypeDescription(String idType) {
    	String description = "";
    	//证件类型 1:身份证 2:港澳通行证 3:驾驶证 4:护照 99:其他
    	switch (idType) {
		case "1":
			description = "身份证";
			break;
		case "2":
			description = "港澳通行证";
			break;

		case "3":
			description = "驾驶证 ";
			break;

		case "4":
			description = "护照";
			break;

		case "99":
			description = "其他";
			break;
		default:
			description = "其他";
			break;
		}
    	return description;
    }

    public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
//	public String getPhotoBace64() {
//		return photoBace64;
//	}
//	public void setPhotoBace64(String photoBace64) {
//		this.photoBace64 = photoBace64;
//	}
	public DevInfo getDevInfo() {
		return devInfo;
	}
	public void setDevInfo(DevInfo devInfo) {
		this.devInfo = devInfo;
	}
	public String getUniqueCode() {
		return UniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		UniqueCode = uniqueCode;
	}
	public int getPersonType() {
		return PersonType;
	}
	public void setPersonType(int personType) {
		PersonType = personType;
	}
	public String getPersonNo() {
		return PersonNo;
	}
	public void setPersonNo(String personNo) {
		PersonNo = personNo;
	}
	public String getPersonName() {
		return PersonName;
	}
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	public int getGender() {
		return Gender;
	}
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
	public String getIDType() {
		return IDType;
	}
	public void setIDType(String iDType) {
		IDType = iDType;
	}
	public String getIDNo() {
		return IDNo;
	}
	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getIsLegal() {
		return IsLegal;
	}
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

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	
	    
}
