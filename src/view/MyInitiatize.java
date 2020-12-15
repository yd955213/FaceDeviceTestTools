package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testModel.recognitionRateTest.RecognitonRateTest;
import httpFrame.http.Http;
import myGson.das.DevInfo;
import myGson.das.RequestGson;
import mySocketClient.myHttpClient.api.GetDeviceParams;
import sqlite3.DataBaseExecute;
import sqlite3.PageBean;
import sqlite3.PageBeanTool;
import sqlite3.PersonInfo;
import tools.Ipv4FromLocal;
import tools.SystemTimes;

public class MyInitiatize {

	public MyInitiatize() {
	}
	public void getInitialization() {

		try {
			
			MainIntfaceView.updateComboBox(MainIntfaceView.getServerIPComboBox(), new Ipv4FromLocal().getIpv4FromLocal());
			
			DataBaseExecute.getInstance().getConnect();
			initializeSocket();
			initializeDevInfo();
			
			RecognitonRateTest.getInit();
			new Thread(()->{
				inintializePersonInfoTable();
			}).start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	/**
	 * 打开软件是，如果数据库中有人，则分页显示人员信息
	 */
	private void inintializePersonInfoTable() {
		//分页显示，更新表格
		PageBean<PersonInfo> pb = new PageBeanTool().getPage(1);
		pb.setPageCount(34);
//		updateTable(pb.getPageData()); //pageComboBox 更新时会促发更新
		MainIntfaceView.pageCountLabel1.setText(Integer.toString(pb.getTotalPage()));
		MainIntfaceView.pagePersonCount.setText(Integer.toString(pb.getTotalCount()));
		List<String> list = new ArrayList<String>();
		for(int i = 0 ; i < pb.getTotalPage();i++) {
			list.add(Integer.toString(i + 1));
		}
		MainIntfaceView.updateComboBox(MainIntfaceView.pageComboBox, list); 
		
	}
	/**
	 * 启动服务 0:HTTP 1:mqtt
	 */
	private void initializeSocket() {
		//开启监听服务
		List<String> list = getCommunicationParamter();
		int port = Integer.parseInt(list.get(0));
		if ("0".equals(list.get(1))) {
			Http.setServerIP(MainIntfaceView.getServerIPComboBox().getSelectedItem().toString());
			Http.setPort(port);
			MainIntfaceView.http = new Http();
			MainIntfaceView.serverPortTextField.setText(Integer.toString(port));
			MainIntfaceView.getRdbtnHttp().setSelected(true);
		}else {
			String ip = list.get(2);
			MainIntfaceView.getMqttServerIPTextField().setText(ip);
			MainIntfaceView.getMqttServerPortTextField().setText(Integer.toString(port));
			MainIntfaceView.getRdbtnMqtt().setSelected(true);
		}
		
	}
	/**
	 * 初始化设备信息
	 */
	public void initializeDevInfo() {
		List<List<String>> devInfoList = DataBaseExecute.getInstance().listResultData(
				"select com_dev.dev_id, com_dev.dev_mac_address, com_dev.dev_ip, dev_port, face_dev_parameter.feature_type from com_dev,face_dev_parameter where com_dev.dev_id = face_dev_parameter.dev_id;");
		
		if (devInfoList != null && !devInfoList.isEmpty()) {
			DevInfo devInfo = null;
			List<String> macList = new ArrayList<String>();
			for(List<String> dev : devInfoList) {
				devInfo = new DevInfo();
				devInfo.setDevID(dev.get(0));
				devInfo.setMacAddr(dev.get(1));
				devInfo.setDevIP(dev.get(2));
				devInfo.setDevPort(dev.get(3));
				devInfo.setFeature_type(dev.get(4));
				
				MainIntfaceView.devInfoMap.put(devInfo.getMacAddr(), devInfo);
				macList.add(dev.get(1));
			}
			MainIntfaceView.updateComboBox(MainIntfaceView.faceDevchoiseComboBox,macList); 
			MainIntfaceView.updateComboBox(MainIntfaceView.faceDevComboBox, macList); 
			
			new Thread(()-> {
				// 重新读取设备信息
				RequestGson<String> requestGson2 = null;
				Gson gson = new GsonBuilder().serializeNulls().create();
				for(List<String> dev : devInfoList) {
					requestGson2 = new RequestGson<String>();
					requestGson2.setDeviceUniqueCode(dev.get(1));
					requestGson2.setData(null);
					new GetDeviceParams(gson.toJson(requestGson2, RequestGson.class), dev.get(1));
				}
			}).start();
		}
	}
	/**
	 * list = {server_prot, communication_type, mqtt_server_ip}
	 * @return list
	 */
	private List<String> getCommunicationParamter() {
		List<List<String>> list = DataBaseExecute.getInstance().listResult("system_parameter", new ArrayList<String>(), new ArrayList<String>(), Arrays.asList("server_prot", "communication_type", "mqtt_server_ip"));
		
		if (list.isEmpty()) {
			int port = 19000;
			DataBaseExecute.getInstance().insertIntoDB("system_parameter", new ArrayList<String>(), Arrays.asList(null, Integer.toString(port), null, "0", SystemTimes.getSysTime(), ""));
			return Arrays.asList(Integer.toString(port),"0", "");
		}else {
			return list.get(0);
		}
		
	}
}
