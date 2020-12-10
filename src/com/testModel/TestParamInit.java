package com.testModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.DES.MySign;
import com.google.gson.Gson;
import com.testModel.OnlinePayRequestTest.ADD_UUID;
import com.testModel.OnlinePayRequestTest.GetSecretThread;
import com.testModel.OnlinePayRequestTest.OffLinePayRequestThread;
import com.testModel.UploadRecordsTest.RecordID;
import com.testModel.UploadRecordsTest.UploadRecordThread;
import com.testModel.getCharacterTest.GetCharacterThread;
import com.testModel.heartBeatTest.ResponseGetDeviceParams;
import com.testModel.heartBeatTest.HeartBeatThread;

import httpFrame.http.Http;
import myGson.das.SendGson;
import myGson.das.consume.OfflinePayRequestSendGson;
import myGson.das.consume.OnlinePayRequestSendGson;
import tools.Ipv4FromLocal;
import tools.LogsWriter;
import tools.MyString;
import view.update.DbServerTest;

public class TestParamInit {
	private static JTextField serverIPJTextField;
	private static JTextField serverProtJTextField;
	private static JTextField devPortJTextField;
	private static JTextField devNoJTextField;
	private static JTextField systemIDJTextField;
//	private static JTextField uniqueCodeTextField;
	private static String url = null;
	private static boolean isOk = true;
	private static String serverIP;
	private static String systemID;
	private static int serverProt;
	private static int devPort;
	private static String uniqueCode = null;
	private static int devNo;
	private static HashMap<String, String> devInfoMap = null;
	private static List<String> macList = null;
	private static int recordNum = 0;
	private static boolean setButtonEnabled = false;
	private boolean setBreak = false;
	
	/**
	 * HashMap<String, List<String>> {"DeviceUniqueCode": {Secret, DesIV}} 密钥(3DES加密后的密文) 3DES加密向量 
	 */
	private static HashMap<String, String> devSecret = null;
	/**
	 * 一次权限下载的数量，当UploadAuthorityDealResulThead完成权限处理时，downloardAuthorityNumber的数量为零，重新请求权限下载
	 */
	private static HashMap<String, Integer> downloardAuthorityNumber = null;
	
	
	public TestParamInit(){
		
	}
	
	private static class TestParamInitHolder {
		private static final TestParamInit testParamInit = new TestParamInit();
	}
	
	public static void setInitiantize(JTextField serverIPJTextField, JTextField serverProtJTextField, JTextField devPortJTextField, 
			JTextField devNoJTextField, JTextField systemIDJTextField) {
		TestParamInit.serverIPJTextField = serverIPJTextField;
		TestParamInit.serverProtJTextField = serverProtJTextField;
		TestParamInit.devPortJTextField = devPortJTextField;
		TestParamInit.devNoJTextField = devNoJTextField;
		TestParamInit.systemIDJTextField = systemIDJTextField;
	}
	
	public static TestParamInit getInstance() {
		
//		TestParamInit.uniqueCodeTextField = uniqueCodeTextField;
		getParamInit();
		if (!new Ipv4FromLocal().isLoclePortUsing(devPort)) {
			new Http(devPort);
		}
		
		return TestParamInitHolder.testParamInit;
	}
	

	public void doHeartBeat() {
		HeartBeatThread heartBeatThread = null;
		if (isOk) {
			for (int i = 0 ; i < devNo ; i ++) {
	    		heartBeatThread = new HeartBeatThread(url, macList.get(i));
	    		new Thread(heartBeatThread).start();
	    	}
		}
	}
	
	public void doCharacter() {
		if (isOk) {
			downloardAuthorityNumber = new HashMap<String, Integer>();
			GetCharacterThread getCharacterThread = null;
			for (int i = 0 ; i < devNo ; i ++) {
				getCharacterThread = new GetCharacterThread(url, macList.get(i));
	    		new Thread(getCharacterThread).start();
	    	}
		}
	}
	
	public void doGetSecret() {
		if (isOk) {
			if (DbServerTest.getInstance().getConn() != null) {
				setButtonEnabled = true;
				devSecret = new HashMap<String, String>();
				GetSecretThread getSecretThread = null;
				for (int i = 0 ; i < devNo ; i ++) {
					getSecretThread = new GetSecretThread(url, macList.get(i));
		    		new Thread(getSecretThread).start();
		    	}
			}else {
				setButtonEnabled = false;
			}
			
		}
	}
	
	public void doTest() {
		while(!setBreak) {
			System.out.println("循环中····");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("结束");
	}
	
	/**
	 * 模拟记录上报
	 * @param type type = 0 单机测试  type = 1 连接数据库测试
	 * @param uniqueCode
	 */
	public void doUploadRecord(int type, String uniqueCode, DbServerTest dbServerTest) {
		
		List<String> macAddrList = null;
		
		RecordID recordID = null;
		UploadRecordThread uploadRecordThread = null;
		ExecutorService pool = null;
		if (type == 0 ) {
			macAddrList = macList;
			recordID = new RecordID(macAddrList);
			pool = new ThreadPoolExecutor(3, 6, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
			while (!setBreak) {
				for (String macAddr : macAddrList) {
					if(setBreak) {
						pool.shutdown();
					}
					uploadRecordThread = new UploadRecordThread(url, macAddr, recordID, uniqueCode);
					pool.execute(uploadRecordThread);
				}
			}
		}else if (type == 1) {
			/*
			 * 连接数据库后，按人（uniqueCode升序排序）按设备上报心跳（macAddr升序排序）
			 */
			if (dbServerTest.getConn() != null) {
				macAddrList = dbServerTest.getDevMacList_MJ();
				recordID = new RecordID(macAddrList);
				List<String> uniqueCodeList = dbServerTest.getUniqueCodeList();
				//线程池
				pool = new ThreadPoolExecutor(3, 6, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
				while (!setBreak) {
					for (String uniqueCode_person : uniqueCodeList) {
						for (String macAddr : macAddrList) {
							if(setBreak) {
								pool.shutdown();
							}
							uploadRecordThread = new UploadRecordThread(url, macAddr, recordID, uniqueCode_person);
							pool.execute(uploadRecordThread);
						}
					}
				}
//				System.out.println("关闭");
			}
		}
	}
	
	public void doGetOnlinePayData() {
		if (!getTextFieldIsNull(systemIDJTextField)) {
			Double dataSize = 20000D;
			dataSize = Math.ceil(dataSize / Double.valueOf(devNo)) ;
			int devMacListSize = macList.size();
			
			LogsWriter.isExistsToDelect(LogsWriter.GETONLINEPAYDATA);
			
			OnlinePayRequestSendGson oprsg = new OnlinePayRequestSendGson();
			Gson gson = new Gson();
			SendGson<OnlinePayRequestSendGson> sg = new SendGson<OnlinePayRequestSendGson>();
			
			new ADD_UUID();
			
			List<String> personList = DbServerTest.getInstance().getUniqueCodeList();
		
			if (personList.isEmpty()) {
				String macAddr = null;
				//我这里为啥要用Double？ 脑抽了？    dataSize = Math.ceil(dataSize / Double.valueOf(devNo)) 取整后为Double类型
				for (Double i = 0D ; i < dataSize; i++) {
					for (int j = 0 ; j < devMacListSize; j++) {
						oprsg = new OnlinePayRequestSendGson();
						sg = new SendGson<OnlinePayRequestSendGson>();
						macAddr = macList.get(j);
						oprsg.setDeviceMac(macAddr);
						oprsg.setCommunicationUUID(oprsg.getCommunicationUUID() + Integer.toHexString(i.intValue()).toUpperCase());
//						oprsg.setDeviceNum(TestParamInit.getDevInfoMap().get(macAddr));
						sg.setDeviceUniqueCode(macAddr);
						sg.setData(oprsg);
						
						sg.setSign(MySign.getMySign(gson.toJson(sg, SendGson.class), TestParamInit.getDevSecret().get(macAddr)));
						LogsWriter.writeInfo_1(LogsWriter.GETONLINEPAYDATA, gson.toJson(sg, SendGson.class));
						
					}
				}
			}else {
//				int personListSize = personList.size();
//				String uniqueCode = null;
				int i =0;
				Map<String, String> devNumMap = DbServerTest.getInstance().getDevNumMap();
				for (String uniqueCode : personList) {
					for (String macAddr : macList) {
						i += 1;
						oprsg = new OnlinePayRequestSendGson();
						oprsg.setUniqueCode(uniqueCode);
						oprsg.setDeviceNum(devInfoMap.get(macAddr));
						oprsg.setDeviceMac(macAddr);
						oprsg.setDeviceNum(devNumMap.get(macAddr));
						oprsg.setCommunicationUUID(oprsg.getCommunicationUUID() + Integer.toHexString(i).toUpperCase());
						
						sg = new SendGson<OnlinePayRequestSendGson>();
						sg.setDeviceUniqueCode(macAddr);
						sg.setData(oprsg);
						
						sg.setSign(MySign.getMySign(gson.toJson(sg, SendGson.class), TestParamInit.getDevSecret().get(macAddr)));
						LogsWriter.writeInfo_1(LogsWriter.GETONLINEPAYDATA, gson.toJson(sg, SendGson.class));
					}
				}
			}
			
		}
	}
	

	public void doGetOffLinePayData() {
//		Double dataSize = 20000D;
//		dataSize = Math.ceil(dataSize / Double.valueOf(devNo)) ;
//		int devMacListSize = macList.size();

		if (!getTextFieldIsNull(systemIDJTextField)) {
			LogsWriter.isExistsToDelect(LogsWriter.GETOFFLINEPAYDATA);
			OfflinePayRequestSendGson oprsg = null;
			Gson gson = new Gson();
			SendGson<OfflinePayRequestSendGson> sg = null;
			String macAddr = null;
			OffLinePayRequestThread offprt = null;
			List<String> uniqueCodeList = DbServerTest.getInstance().getUniqueCodeList();
			List<String> devMacAddrList = DbServerTest.getInstance().getDevMacList_MJ(); 
			int uniqueCodeListSize = uniqueCodeList.size();
			int devMacAddrListSize = devMacAddrList.size();
			for (int i = 0 ; i < uniqueCodeListSize; i++) {
				for (int j = 0 ; j < devMacAddrListSize; j++) {
					oprsg = new OfflinePayRequestSendGson();
					sg = new SendGson<OfflinePayRequestSendGson>();
					macAddr = macList.get(j);
					offprt = new OffLinePayRequestThread("", macAddr, uniqueCodeList.get(i));
					oprsg.setOfflineConsumeData(offprt.getInitString());
					
					sg.setDeviceUniqueCode(macAddr);
					sg.setData(oprsg);
					sg.setSign(MySign.getMySign(gson.toJson(sg, SendGson.class), TestParamInit.getDevSecret().get(macAddr)));
					LogsWriter.writeInfo_1(LogsWriter.GETOFFLINEPAYDATA, gson.toJson(sg, SendGson.class));
				}
			}
		}
	}
	
	private boolean getTextFieldIsNull(JTextField text) {
		boolean isnull = false;
		if (text.getText() == null ||text.getText().replaceAll(" ", "").length() <= 0) {
			isnull = true;
		}
		return isnull;
	} 
	private static void getParamInit() {
		
		serverIP = serverIPJTextField.getText().replaceAll(" ", "");
		systemID = systemIDJTextField.getText().replaceAll(" ", "");
		serverProt = getIntFromJtextField("服务端口号输入纯数字", serverProtJTextField);
		devPort = getIntFromJtextField("设备端口号输入纯数字", devPortJTextField);
		devNo = getIntFromJtextField("设备台数输入纯数字", devNoJTextField);
		url = "http://" + serverIP + ":" + serverProt;
//		uniqueCode = Integer.toString(getIntFromJtextField("UniqueCode输入纯数字", uniqueCodeTextField));
		DevInfoMap();
	}
	
	private static int getIntFromJtextField(String massage, JTextField textField ) {
		isOk = true;
		int portParam = 8090;
		try {
			portParam = Integer.parseInt(textField.getText().replaceAll(" ", ""));
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			isOk = false;
			JOptionPane.showMessageDialog(null, massage, "模拟设备上线", JOptionPane.ERROR_MESSAGE);
		}
		return portParam;
	}
	
	private static void DevInfoMap() {
		String macAddrStr = "";
		String deviceNum = "";
		String tempDeviceNum = "88";
		String tempStr = "";
		//生产MAC地址
		devInfoMap = new LinkedHashMap<String, String>();
		macList = new ArrayList<String>();
		if (ResponseGetDeviceParams.getDeviceName().equals(ResponseGetDeviceParams.DEVICE_NAME_MJ)) {
			tempDeviceNum = "11";
		}
		for (int i = 1 ; i <= devNo ; i ++) {
			tempStr = MyString.getFullString(Integer.toString(i), "0", 4);
			macAddrStr =ResponseGetDeviceParams.getDeviceName() + tempStr;
			
			deviceNum = tempDeviceNum + tempStr;
			
			macList.add(macAddrStr);
			devInfoMap.put(macAddrStr, deviceNum);
		}
		
	}

	public static HashMap<String, String> getDevInfoMap() {
		return devInfoMap;
	}
	
	public static String getServerIP() {
		return serverIP;
	}

	public static int getServerProt() {
		return serverProt;
	}

	public static int getDevPort() {
		return devPort;
	}

	public static String getUrl() {
		return url;
	}
	/**
	 * 用于记录一次权限下载的数量，当UploadAuthorityDealResulThead完成权限处理时，downloardAuthorityNumber的数量为零，重新请求权限下载
	 */
	public static HashMap<String, Integer> getDownloardAuthorityNumber() {
		return downloardAuthorityNumber;
	}

	public static String getSystemID() {
		return new MyString().getLimitLengthString(systemID, 10);
	}
	/**
	 * HashMap<String, List<String>> {"DeviceUniqueCode": {Secret, DesIV}} 密钥(3DES加密后的密文) 3DES加密向量 
	 */
	public static HashMap<String, String> getDevSecret() {
		return devSecret;
	}

	public static String getUniqueCode() {
		return uniqueCode;
	}

	public static int getRecordNum() {
		return recordNum + 1;
	}


	public static boolean isSetButtonEnabled() {
		return setButtonEnabled;
	}


	public boolean isSetBreak() {
		return setBreak;
	}


	public void setSetBreak(boolean setBreak) {
		this.setBreak = setBreak;
	}

	
	
	
}
