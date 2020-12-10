package sqlite3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tools.SystemTimes;

public class FaceEmpPhoto {
	private int personID;
	private String photoUrl;
	private String photoBase64;
	private int featureType;
	private String feature;
	private int score;
	private String getFeatureDay;
	private String gmtCreate = SystemTimes.getSysTime();
	private String gmtModified;
	private List<String> list = null;
	public FaceEmpPhoto() {
		list = new ArrayList<String>();
	}
	/**
	 * 初始化参数，并完成写库功能:表 face_photo
	 * @param personID
	 * @param photoUrl
	 */
	public void getinitialization(int personID, String photoUrl) {
		this.personID = personID;
		this.photoUrl = photoUrl;
	}
	/**
	 * 初始化参数，并完成写库功能:表 face_photo
	 * @param personID
	 * @param photoUrl
	 * @param photoBase64
	 * @param featureType
	 */
	public void getinitialization(int personID, String photoUrl, String photoBase64, int featureType) {
		this.personID = personID;
		this.photoUrl = photoUrl;
		this.photoBase64 = photoBase64;
		this.featureType = featureType;
	}
	
	public void insertInfoDB_face_emp_photo() {
		list = facePhotoList();
		DataBaseExecute.getInstance().insertIntoDB("face_photo", new ArrayList<String>(), list);
				
	}
	public void updateInfoDB_face_emp_photo() {
			
		list = facePhotoList();
		DataBaseExecute.getInstance().updateDB("face_photo", Arrays.asList("Person_id"), Arrays.asList(Integer.toString(personID)), Arrays.asList("photo_url"), Arrays.asList(photoUrl));
				
	}
	
	protected List<String> facePhotoList() {
		list.clear();
		list.add(Integer.toString(personID));  //Person_id 自增长
		list.add(photoUrl);  //photo_url
		list.add(photoBase64);  //  PhotoBase64 下载时如果不想保存照片，则设置为空
		list.add(gmtCreate);  //gmt_create
		list.add(gmtModified);  //gmt_modified
		return list;
	}
	protected List<String> faceFeatureList() {
		list.clear();
		list.add(Integer.toString(personID));  //Person_id 自增长
		list.add(Integer.toString(featureType));  //feature_type
		list.add(Integer.toString(score));  //score
		list.add(feature);  //feature
		list.add(getFeatureDay);  //get_feature_day
		list.add(gmtCreate);  //gmt_create
		list.add(gmtModified);  //gmt_modified
		return list;
	}
	
	
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoBase64() {
		return photoBase64;
	}

	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public int getFeatureType() {
		return featureType;
	}
	public void setFeatureType(int featureType) {
		this.featureType = featureType;
	}
	public String getGetFeatureDay() {
		return getFeatureDay;
	}
	public void setGetFeatureDay(String getFeatureDay) {
		this.getFeatureDay = getFeatureDay;
	}
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	
}
