package view.update;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import faceEngine.MyPhoto;
import myGson.das.DevInfo;
import myGson.das.DownloadAuthorityDataGson;
import myGson.das.RequestGson;
import mySocketClient.myHttpClient.api.DownloadAuthorityData;
import sqlite3.DataBaseExecute;
import sqlite3.FaceEmpPhoto;
import sqlite3.PersonInfo;
import tools.SystemTimes;

public class PersonInfoChange {
	private JComboBox<String> faceDevchoiseComboBox;
	private JComboBox<String> InfoComboBox;
	private JComboBox<String> infoGenderComboBox;
	private JComboBox<String> idTypeComboBox;
	private JComboBox<String> pageComboBox;
	private JTextField infoNameTextField;
	private JTextField infoOpeTextField;
	private JTextField infoIdTextField;
	private JTextField InfoCardTextField;
	private JTextField infoFaceStartTextField;
	private JTextField infoFaceEndTextField; 
	private JTextField infoCardStartTextField;
	private JTextField infoCardEndTextField;
	public PersonInfoChange(JComboBox<String> faceDevchoiseComboBox, JComboBox<String> InfoComboBox, JComboBox<String> infoGenderComboBox, JComboBox<String> idTypeComboBox, 
			JComboBox<String> pageComboBox,JTextField infoNameTextField, JTextField infoOpeTextField, JTextField infoIdTextField, JTextField InfoCardTextField, 
			JTextField infoFaceStartTextField, JTextField infoFaceEndTextField, JTextField infoCardStartTextField, JTextField infoCardEndTextField) {
		this.faceDevchoiseComboBox = faceDevchoiseComboBox;
		this.InfoComboBox = InfoComboBox;
		this.infoGenderComboBox = infoGenderComboBox;
		this.idTypeComboBox = idTypeComboBox;
		this.pageComboBox = pageComboBox;
		this.infoNameTextField = infoNameTextField;
		this.infoOpeTextField = infoOpeTextField;
		this.infoIdTextField = infoIdTextField;
		this.InfoCardTextField = InfoCardTextField;
		this.infoFaceStartTextField = infoFaceStartTextField;
		this.infoFaceEndTextField = infoFaceEndTextField; 
		this.infoCardStartTextField = infoCardStartTextField;
		this.infoCardEndTextField = infoCardEndTextField;
	}
	
	
	public void updatePersonInfoAndDownLoad(File files, String selectTablePersonID, HashMap<String, DevInfo> devInfoMap) {
		if(isOk(files)) {
			String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
			String photoBase64 = MyPhoto.getPhoteBASE64_Mime(files);
			PersonInfo pi = getPersonInfo(photoBase64, files.getPath());
			//写数据库
			pi.insertInfoDB_pearson_info(files.getName());
			InsertIntoDbAndView(files, macAddr, photoBase64, devInfoMap);
			
			DownloadAuthorityDataGson downloadAuthorityDataGson = DataBaseExecute.getInstance().getDownloadAuthorityDataGson();
			sendData(downloadAuthorityDataGson, macAddr);
		}
	}
	
	
	public void alterPersonInfoAndDownLoad(File files, String selectTablePersonID, HashMap<String, DevInfo> devInfoMap) {
		
		if(isOk(files)) {
			String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
			String photoBase64 = MyPhoto.getPhoteBASE64_Mime(files);
			PersonInfo personInfo = getPersonInfo(photoBase64, files.getPath());
			//修改表pearson_info
			personInfo.updateInfoDB_pearson_info(selectTablePersonID);
			//修改表face_photo
			updateDbAndView(files, macAddr,Integer.parseInt(selectTablePersonID), photoBase64, devInfoMap);
			
			DownloadAuthorityDataGson downloadAuthorityDataGson = DataBaseExecute.getInstance().getDownloadAuthorityDataGson(selectTablePersonID);
			sendData(downloadAuthorityDataGson, macAddr);
		}
	}
	
	
	private void sendData(DownloadAuthorityDataGson downloadAuthorityDataGson, String macAddr) {
		Gson gson = new Gson();
		RequestGson<List<DownloadAuthorityDataGson>> requestGson = new RequestGson<List<DownloadAuthorityDataGson>>();
		requestGson.setDeviceUniqueCode(macAddr);
		requestGson.setData(Arrays.asList(downloadAuthorityDataGson));
		
		String parameter = gson.toJson(requestGson, new TypeToken<RequestGson<List<DownloadAuthorityDataGson>>>() {}.getType());
		System.out.println("parameter = " + parameter);
		new Thread(()-> {
			new DownloadAuthorityData(parameter, macAddr).sendData();
		}).start(); 
	}
	
	private boolean isOk(File files) {
		String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
		if(files == null || macAddr== null || infoNameTextField.getText().replace(" ", " ") == null || 
				infoNameTextField.getText().replace(" ", " ").length() < 1) {
			JOptionPane.showMessageDialog(null, "无设备或者姓名、照片为空", "下载人员信息", JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			return true;
		}
	}
	
	private PersonInfo getPersonInfo (String photoBase64 , String photoPath) {
		/*
		 * 插入数据，UniqueCode为空
		 */
		
		PersonInfo pi = new PersonInfo();
		pi.setPersonName(infoNameTextField.getText().replace(" ", ""));
		pi.setDptName(getTextFieldValues(infoOpeTextField, "测试部"));
		pi.setPersonNo("test" + DataBaseExecute.getInstance().getPersonID());
		pi.setPersonType(InfoComboBox.getSelectedIndex());
		pi.setPhoto(photoBase64);
		pi.setGender(infoGenderComboBox.getSelectedIndex());
		pi.setIDType(Integer.toString(idTypeComboBox.getSelectedIndex()));
		pi.setIDNo(getTextFieldValues(infoIdTextField, "0"));
		pi.setCardNo(getTextFieldValues(InfoCardTextField, null));
		pi.setFaceStartUseTime(getTextFieldValues(infoFaceStartTextField, SystemTimes.getSysTime1()));
		pi.setFaceStopUseTime(getTextFieldValues(infoFaceEndTextField, "2099-09-09 09:09:09"));
		pi.setCardStartUseTime(getTextFieldValues(infoCardStartTextField, SystemTimes.getSysTime1()));
		pi.setCardEndUseTime(getTextFieldValues(infoCardEndTextField, "2099-09-09 09:09:09"));
		pi.setPhotoPath(photoPath);
		System.out.println(pi.getDptName());
		return pi;
	}
	
	private void updateDbAndView(File files,String macAddr, int personID, String photoBase64, HashMap<String, DevInfo> devInfoMap) {
		
		FaceEmpPhoto fep = new FaceEmpPhoto();
		fep.getinitialization(
				personID,
				files.getPath(), 
				photoBase64, 
				Integer.parseInt(devInfoMap.get(macAddr).getFeature_type()));
		fep.updateInfoDB_face_emp_photo();
		
		//刷新表格
		pageComboBox.setSelectedIndex(pageComboBox.getModel().getSize() - 1);

	}
	
	private void InsertIntoDbAndView(File files,String macAddr, String photoBase64, HashMap<String, DevInfo> devInfoMap) {
		
		FaceEmpPhoto fep = new FaceEmpPhoto();
		fep.getinitialization(
				DataBaseExecute.getInstance().getPersonID(),
				files.getPath(), 
				photoBase64, 
				Integer.parseInt(devInfoMap.get(macAddr).getFeature_type()));
		fep.insertInfoDB_face_emp_photo();
		//刷新表格
		pageComboBox.setSelectedIndex(pageComboBox.getModel().getSize() - 1);
	}
	
	private String getTextFieldValues(JTextField jTextField ,String values) {
		String textFieldValues = jTextField.getText().replace(" ", "");
		if ( null == jTextField || textFieldValues.length() <=0) {
			textFieldValues = values;
		}
		return textFieldValues;
	}
}
