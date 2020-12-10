package sqlite3;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sqlite3.table.ComDevTable;
import sqlite3.table.FaceDeviceParameterTable;

public class DeviceExecute extends DataBaseExecute{
	private List<String> tableName = null;
	private List<String> tableNameValuse = null;
	
	public DeviceExecute() {
	}
	
	/**
	 * 设备入库
	 * @param comDevTable 表com_dev 的插入值
	 * @param faceDeviceParameterTable  表face_dev_parameter 的插入值
	 * @throws SQLException
	 */
	public synchronized String insertIntoDevInfo(ComDevTable comDevTable) throws SQLException {
		String sql = null;
		String devId = null;
		if (DataBaseExecute.getInstance().getCountResultData("com_dev", "dev_mac_address", comDevTable.devMacAddress) < 1) {
			try {
				conn.setAutoCommit(false);
				sql = "insert into com_dev( dev_is_online , dev_ip , dev_mac_address , dev_port , gmt_create) values(?,?,?,?,?);";
				ps = conn.prepareStatement(sql);
				ps = getComDev(ps, comDevTable);
				ps.executeUpdate();
				
				devId = DataBaseExecute.getInstance().listResult("com_dev", "dev_mac_address", comDevTable.devMacAddress, "dev_id").get(0);
				
				sql = "insert into face_dev_parameter(dev_id, dev_mac_address, gmt_create) "
						+ "values(?,?,?);";
				ps = conn.prepareStatement(sql);
				//dev_id
				ps.setString(1, devId);
				//dev_mac_address
				ps.setString(2, comDevTable.devMacAddress);
				//gmt_create
				ps.setString(3, comDevTable.gmtCreate);
				ps.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else {
			devId = DataBaseExecute.getInstance().listResult("com_dev", "dev_mac_address", comDevTable.devMacAddress, "dev_id").get(0);
		}
		return devId;
	}
	
	public synchronized boolean updateDeviceParams(FaceDeviceParameterTable faceDeviceParameterTable) throws SQLException {
		try {
			getFaceDevParameter(faceDeviceParameterTable);
			
			DataBaseExecute.getInstance().updateDB("face_dev_parameter",
					Arrays.asList("dev_id"), 
					Arrays.asList(faceDeviceParameterTable.devId), 
					tableName, 
					tableNameValuse);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	private PreparedStatement getComDev(PreparedStatement ps, ComDevTable comDevTable) throws SQLException {
		ps.setString(1, comDevTable.devIsOnline);
		ps.setString(2, comDevTable.devIp);
		ps.setString(3, comDevTable.devMacAddress);
		ps.setInt(4, comDevTable.devPort);
		ps.setString(5, comDevTable.gmtCreate);
		return ps;
	}
	
	private void getFaceDevParameter(FaceDeviceParameterTable faceDeviceParameterTable)  throws SQLException {
		if (null == tableName) {
			tableName = new ArrayList<String>();
		}else {
			tableName.clear();
		}
		
		if (null == tableNameValuse) {
			tableNameValuse = new ArrayList<String>();
		}else {
			tableNameValuse.clear();
		}
		/*
		 *这里可以运用反射的原理去赋值，懒得改，下一个接手的 看不下去自己改 
		 */
		//app_pass_word
		addElements(faceDeviceParameterTable.appPassWord, "app_pass_word");
		//dev_use_type
		addElements(Integer.toString(faceDeviceParameterTable.devUseType),"dev_use_type");
		//is_support_qrcode
		addElements(Integer.toString(faceDeviceParameterTable.isSupportCard), "is_support_qrcode");
		//is_support_card
		addElements(Integer.toString(faceDeviceParameterTable.isSupportCard), "is_support_card");
		//trigger_action_interval
		addElements(Integer.toString(faceDeviceParameterTable.triggerActionErval), "trigger_action_interval");
		//relay_time
		addElements(Integer.toString(faceDeviceParameterTable.relayTime), "relay_time");
		//recognize_success_tip
		addElements(faceDeviceParameterTable.recognizeSuccessTip, "recognize_success_tip");
		//recognize_error_tip
		addElements(faceDeviceParameterTable.recognizeErrorTip, "recognize_error_tip");
		//attendance_time
		addElements(faceDeviceParameterTable.attendanceTime, "attendance_time");
		//before_job_tip
		addElements(faceDeviceParameterTable.beforeJobTip, "before_job_tip");
		//after_job_tip
		addElements(faceDeviceParameterTable.afterJobTip, "after_job_tip");
		//is_auto_restart
		addElements(Integer.toString(faceDeviceParameterTable.isAutoRestart), "is_auto_restart");
		//daily_restart_time
		addElements(faceDeviceParameterTable.dailyTestartTime, "daily_restart_time");
		//is_upload_pass_img
		addElements(Integer.toString(faceDeviceParameterTable.isUploadPassImg), "is_upload_pass_img");
		//main_ui_type
		addElements(Integer.toString(faceDeviceParameterTable.mainUiType), "main_ui_type");
		//enable_screen_saver
		addElements(Integer.toString(faceDeviceParameterTable.enableScreenSaver), "enable_screen_saver");
		//heart_beat_interval
		addElements(Integer.toString(faceDeviceParameterTable.heartBeatErval), "heart_beat_interval");
		//wiegand_type
		addElements(Integer.toString(faceDeviceParameterTable.wiegandType), "wiegand_type");
		//wiegand_in
		addElements(Integer.toString(faceDeviceParameterTable.wiegandIn), "wiegand_in");
		//wiegand_out
		addElements(Integer.toString(faceDeviceParameterTable.wiegandOut), "wiegand_out");
		//opend_door_pass_word
		addElements(faceDeviceParameterTable.opendDoorPassWord, "opend_door_pass_word");
		//feature_type
		addElements(Integer.toString(faceDeviceParameterTable.featureType), "feature_type");
		//feature_sdk_value
		addElements(faceDeviceParameterTable.featureSdkValue, "feature_sdk_value");
		//feature_version
		addElements(faceDeviceParameterTable.featureVersion, "feature_version");
		//simility_threshold
		addElements(Double.toString(faceDeviceParameterTable.similityThreshold), "simility_threshold");
		//quality_threshold
		addElements(Double.toString(faceDeviceParameterTable.qualityThreshold), "quality_threshold");
		//is_alive
		addElements(Integer.toString(faceDeviceParameterTable.isAlive), "is_alive");
		//living_threshold
		addElements(Double.toString(faceDeviceParameterTable.livingThreshold), "living_threshold");
		//min_face_pixel
		addElements(Integer.toString(faceDeviceParameterTable.minFacePixel), "min_face_pixel");
		//max_ace_ixel
		addElements(Integer.toString(faceDeviceParameterTable.maxAcePixel), "max_ace_ixel");
		//supple_light_mode
		addElements(Integer.toString(faceDeviceParameterTable.suppleLightMode), "supple_light_mode");
		//supple_light_open_time
		addElements(faceDeviceParameterTable.suppleLightOpenTime, "supple_light_open_time");
		//debug_mode_switch
		addElements(Integer.toString(faceDeviceParameterTable.debugModeSwitch), "debug_mode_switch");
		//remote_ctrl_door_id
		addElements(faceDeviceParameterTable.remoteCtrlDoorId, "remote_ctrl_door_id");
		//in_out_flag
		addElements(Integer.toString(faceDeviceParameterTable.inOutFlag), "in_out_flag");
		//is_kq_use
		addElements(Integer.toString(faceDeviceParameterTable.isKqUse), "is_kq_use");
		//pay_url
		addElements(faceDeviceParameterTable.payUrl, "pay_url");
		//query_url
		addElements(faceDeviceParameterTable.queryUrl, "query_url");
		//device_num
		addElements(Integer.toString(faceDeviceParameterTable.deviceNum), "device_num");
		//success_info_duration
		addElements(Integer.toString(faceDeviceParameterTable.successInfoDuration), "success_info_duration");
		//display_title
		addElements(faceDeviceParameterTable.DisplayTitle, "display_title");
		//breakfast_time
		addElements(faceDeviceParameterTable.breakfastTime, "breakfast_time");
		//lunch_time
		addElements(faceDeviceParameterTable.lunchTime, "lunch_time");
		//dinner_time
		addElements(faceDeviceParameterTable.dinnerTime, "dinner_time");
		//supper_ime
		addElements(faceDeviceParameterTable.supperTime, "supper_ime");
		//offline_switch
		addElements(Integer.toString(faceDeviceParameterTable.offlineSwitch), "offline_switch");
		//offline_sum_limit
		addElements(Integer.toString(faceDeviceParameterTable.offlineSumLimit), "offline_sum_limit");
		//offline_count_limit
		addElements(Integer.toString(faceDeviceParameterTable.offlineCountLimit), "offline_count_limit");
		//offline_time_imit
		addElements(Integer.toString(faceDeviceParameterTable.offlineTimeLimit), "offline_time_imit");
		//ssCard_system_init
		addElements(Integer.toString(faceDeviceParameterTable.ssCardSystemInit), "ssCard_system_init");
		//online_wallet_id1
		addElements(Integer.toString(faceDeviceParameterTable.onlineWalletId1), "online_wallet_id1");
		//online_wallet_id2
		addElements(Integer.toString(faceDeviceParameterTable.onlineWalletId2), "online_wallet_id2");
		
		addElements(faceDeviceParameterTable.gmtModified, "gmt_modified");

		addElements(faceDeviceParameterTable.devId, "dev_id");
	}
	
	private void addElements(String valuse, String tableCloumeName) {
		if (null != valuse && valuse.length() > 0) {
			tableNameValuse.add(valuse);
			tableName.add(tableCloumeName);
		}
	}
}
