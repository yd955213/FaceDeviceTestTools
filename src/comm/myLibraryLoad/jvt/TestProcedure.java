package comm.myLibraryLoad.jvt;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.testModel.recognitionRateTest.RecognitonRateTest;
import httpFrame.http.Http;
import httpFrame.http.dasApi.DeviceHeartBeat;
import httpFrame.http.dasApi.UploadRecords;
import myGson.das.SendGson;
import mySocketClient.myHttpClient.OkHttpClientUtil;
import mySocketClient.myHttpClient.api.DownloadAuthorityData;
import mySocketClient.myHttpClient.api.GetDeviceParams;
import mySocketClient.myHttpClient.api.SetDeviceParams;
import sqlite3.DataBaseExecute;
import sqlite3.DownLoadAuthorityType;
import tools.LogsWriter;
import tools.downLoadAuthority.DownLoadAuthorityStatu;
import view.MainIntfaceView;

public class TestProcedure {

	private static DeviceInfo deviceInfo = new DeviceInfo();
	/**
	 * 等待时间 默认 3000毫秒
	 */
	public static final int WAIT_TIME = 3000;
	/**
	 * 重发次数超过6次，则认为该测试步骤失败
	 */
	private final int TIME = 6;
	private final Type TYPE_TOKEN = new TypeToken<SendGson<JvtGetDeviceParamGson>>() {}.getType();
	private SendGson<JvtGetDeviceParamGson> sendGson = null;
	private JvtGetDeviceParamGson deviceParamGson = null;
	private static File[] fileArray = null;
	private RecognitonRateTest recognitonRateTest = null;
	private JvtDeviceDll jvtDeviceDll = JvtDeviceDll.getInstance();
	private List<String> personList = null;
	private static List<String> waiteAliveRecognitionList = null;
	// 获取本机IP 作为服务器IP地址
	private String serverIP = Http.getServerIP();
	private int serverPort = Http.getPort();
	public boolean nextProcedureIsOk = false;
	private Gson gson = new Gson();
	private Gson gsonNull = null;
	/**
	 * 当设备登录成功后，根据设备ID生成对应的日志数据
	 */
	private HashMap<String, StringBuffer> resultLogMap = new HashMap<String, StringBuffer>();

	private static boolean setBreak = false;
	private StringBuffer result = null;
	/**
	 * 测试开始前的准备工作
	 */
	public void readingToTest() {
		
		result = new StringBuffer();
		result.append("测试时间" + "\t" + "设备ID" + "\t" + "设置参数" + "\t" + "设备上线" + "\t" + "权限下载" + "\t" + "图片识别" + "\t" + "活体识别" + "\t" + "记录上传" + "\t" + "恢复出厂");
		LogsWriter.write(LogsWriter.FACTORY_TEST, result.toString());
		
		//图片识别 照片所在文件夹
		fileArray = new File(RecognitonRateTest.CONTRASTPHOTO_B).listFiles();
		personList = new ArrayList<String>();
		int fileArrayLength = fileArray.length;
		for(int i = 0 ; i < fileArrayLength; i++) {
			personList = DataBaseExecute.getInstance().listResult("pearson_info", "person_id", "", "person_name");
		}
	}
	/*
	 * 自定义,可在设置设备参数之前获取设备密码；
	 * 或者放在设置设备参数之后，通过读取对比判断参数是否设置正确；
	 */
	/**
	 * 搜索到设备后，设置设备远程ip和端口后
	 * 更加测试步骤设置是否启用活体
	 * @param TestProcedure 非活体： = 2 , 活体： 1;
	 */
	public void setDeviceParam(int isAlive) {
		updateJlabel("当前测试设备：" + deviceInfo.serialNumber + "、 设置设备参中···");
//		for (DeviceInfo deviceInfo : deviceInfoList) {
			if (null == deviceInfo.serverIp) {
				
				deviceInfo.serverIp = serverIP;
				deviceParamGson = new JvtGetDeviceParamGson();
				deviceParamGson.BasicParams.DeviceUniqueCode = deviceInfo.serialNumber;
				deviceParamGson.BasicParams.DeviceIP = deviceInfo.devIp;
				deviceParamGson.BasicParams.DevicePort = SearchDevice.DEVICE_PORT;
				deviceParamGson.BasicParams.DeviceName = "工厂测试";
				deviceParamGson.BasicParams.ServerIP = serverIP;
				deviceParamGson.BasicParams.ServerPort = serverPort;
	//			deviceParamGson.BasicParams.setSystemID("1234567890");
				//巨龙设备心跳时长有问题HeartBeatInterval = 5000是，心跳1s一次
				deviceParamGson.BasicParams.HeartBeatInterval = 20000;
				
				deviceParamGson.RecognitionParams.IsAlive = isAlive;
				//不需要重置设备密码
				deviceParamGson.AppParams.AppPassword = null;
				
				sendGson= new SendGson<JvtGetDeviceParamGson>();
				sendGson.setDeviceUniqueCode(deviceInfo.serialNumber);
				sendGson.setData(deviceParamGson);
				
				OkHttpClientUtil.setDevApiIP(deviceInfo.devIp);
				OkHttpClientUtil.setDevApiPort(SearchDevice.DEVICE_PORT);
				//测试版本不写数据库
				GetDeviceParams.setNotUpdateDb(true);
				
				new SetDeviceParams(gson.toJson(sendGson, TYPE_TOKEN), deviceInfo.serialNumber);
				while(true) {
					if(recieveInThreaScord(SetDeviceParams.requestOk)) {
//						updateJlabel("设置设备参数成功，通信正常");
						nextProcedureIsOk = true;
					}else {
						nextProcedureIsOk = false;
					}
					break;
				}
				
			}
//		}
		
	}
	//2、设置设备参数 :远程IP、端口、非活体
	/**
	 * 使用DLL方法登录设备无法获取到设备密码，可通过此方法返回设备密码
	 * @param ip
	 * @param prot
	 * @param deviceUniqueCode
	 * @return
	 */
	public String getDeviceParam() {
		String passWord = null;
		gsonNull = new GsonBuilder()
				.serializeNulls()
				.create();
		SendGson<String> sendGson = new SendGson<String>();
		sendGson.setDeviceUniqueCode(deviceInfo.serialNumber);

		OkHttpClientUtil.setDevApiIP(deviceInfo.devIp);
		OkHttpClientUtil.setDevApiPort(SearchDevice.DEVICE_PORT);
		GetDeviceParams.setNotUpdateDb(true);
		
		new GetDeviceParams(gsonNull.toJson(sendGson, SendGson.class), deviceInfo.serialNumber);
		updateJlabel("开始读取设备信息。。。");
		int timeTemp = 0;
		while(true) {
			if(null != GetDeviceParams.getPassWordJvtTest()) {
				passWord = GetDeviceParams.getPassWordJvtTest();
				GetDeviceParams.setPassWordJvtTest(null);
				nextProcedureIsOk = true;
				break;
			}
			if (timeTemp > 3000){
				nextProcedureIsOk = false;
				updateJlabel("读取设备信息无返回。。。");
				mywait(1000);
				break;
			}
			mywait(200);
			timeTemp += 200;
		}
		return passWord;
	}
	//3、等等设备心跳上线
	public void waiteForDeviceHeartBeat() {
		// TODO Auto-generated method stub
		updateJlabel("等待设备上线中。。。");
//		for (DeviceInfo deviceInfo : deviceInfoList) {
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
					// TODO Auto-generated method stub
					int timeTemp = 0;
					while(true) {
						//recieveInThreaScord判断字符串非空并返回true后，会将字符串置为空，故此处禁止使用DeviceHeartBeat.getDeviceUniqueCode()来作为设备Id地址
						if (recieveInThreaScord(DeviceHeartBeat.getDeviceUniqueCode())) {
							updateJlabel("设备上线成功：设备ID = " + deviceInfo.serialNumber);
							resultLogMap.get(deviceInfo.serialNumber).append("成功" + "\t");
							nextProcedureIsOk = true;
							break;
						}else {
							//防止参数设置未成功或设备保存错误，再次设置一次
							updateJlabel("设备：" +  deviceInfo.serialNumber + "超过" + WAIT_TIME/1000 + "秒未上线，再次设置参数（次数" +  (++timeTemp) + "），等待上线。。。");
							setDeviceParam(1);
						}
						
						if (timeTemp > TIME) {
							updateJlabel("设备：" +  deviceInfo.serialNumber + "超过" + TIME * WAIT_TIME/1000 + "秒未上线，测试失败！！！");
							nextProcedureIsOk = false;
							break;
						}
					}
//				}
//			}).start();
//		}
	}
	//4、调用设备视频流，检测摄像头
	//5、调用LED接口，检测led灯
	//6、人脸权限下载
	public void downLoadAuthority(DownLoadAuthorityType type) {
		updateJlabel("权限下载中");
		//单台设备下载测试用
		DataBaseExecute.getInstance().updateDB("update face_dev_author_set set down_loaded = 0 where dev_id = " + MainIntfaceView.getDevInfo().get(deviceInfo.serialNumber).getDevID() + ";");
		
		try {
			new DownLoadAuthorityStatu().downLoadAuthority(deviceInfo.serialNumber, MainIntfaceView.getDevInfo().get(deviceInfo.serialNumber).getDevID(), true, type);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "注意：权限下载失败！请先提取特征值");
		}
		//权限全部下载成功时 ok = true;
		
		switch (type) {
		case PHOTOURL_AND_FEATURE:
			if (DownloadAuthorityData.requestOk) {
				updateJlabel("权限下载成功！请将设备正对电脑屏幕，准备开始识别！");
				nextProcedureIsOk = true;
			}else {
				nextProcedureIsOk = false;
//				testDataLog.append("权限未下载失败，测试中断；"+ deviceInfo.serialNumber + "<br>;5秒后重新开始测试");
				mywait(5000);
				updateJlabel("<html>注意：权限未下载失败，测试中断；"+ deviceInfo.serialNumber + ";<br>5秒后重新开始测试！请检测网络或者设备是否正常！</html>");
			}
			
			break;
			
		case PHOTOURL_ISLEGAL_N:
			if (DownloadAuthorityData.requestOk) {
				updateJlabel("权限删除成功！");
				nextProcedureIsOk = true;
			}else {
				nextProcedureIsOk = false;
				updateJlabel("注意：权限删除失败！请检测网络或者设备是否正常！");
			}
		default:
			break;
		}
//		writeLogs(nextProcedureIsOk, deviceInfo.serialNumber);
		mywait(1000);
	}
	//7、图片识别
	public void pictureRecognition() {
		// TODO Auto-generated method stub
		//选项卡跳转到图片切换页面
		MainIntfaceView.getTestTabbedPane().setSelectedComponent(MainIntfaceView.getTestPhotoPanel());
		//测试用，后面屏蔽
//		fileArray = new File(RecognitonRateTest.CONTRASTPHOTO_B).listFiles();
		if (fileArray.length > 0) {
			pictureChange(fileArray);
		}
	}
	private void pictureChange(File[] fileArrayTemp) {
		int fileSize = fileArrayTemp.length;
		recognitonRateTest = new RecognitonRateTest();
		int timeTemp = 0;
		for (int i = 0 ; i < fileSize; i++) {
			UploadRecords.setName(null);
			recognitonRateTest.updatePhoto(MainIntfaceView.getTestphotoLabel(), fileArrayTemp, i);
			while(true) {
				if(recieveInThreaScord(UploadRecords.getName())) {
					if (personList.contains(UploadRecords.getName())) {
						MainIntfaceView.getTestphotoLabel().setIcon(null);
						nextProcedureIsOk = true;
						timeTemp = 0;
						break;
					}
				}else {
					++timeTemp;
				}
//				System.out.println("timeTemp = " + timeTemp);
				if (timeTemp > 2*TIME){
					nextProcedureIsOk = false;
					break;
				}
			}
			//若第一次记录未上穿 直接打断
			if (!nextProcedureIsOk){
				updateJlabel("设备" + deviceInfo.serialNumber + "未上传记录，本次测试失败，5秒后开始下一次测试！！！");
				mywait(5*1000);
				break;
			}
		}
//		writeLogs(nextProcedureIsOk, deviceInfo.serialNumber);
		//完成测试后、选项卡跳转到测试页面
		MainIntfaceView.getTestTabbedPane().setSelectedComponent(MainIntfaceView.getTest343Panel());
		if (nextProcedureIsOk) {
			updateJlabel("设备" + deviceInfo.serialNumber + "连续上传" + fileSize + "条记录成功；");
		}
//		System.out.println("timeTemp nextProcedureIsOk = " + nextProcedureIsOk);
//		mywait(RecognitonRateTest.DELAY);
		mywait(200);
	}
	//8、设置启用活体

	//9、活体识别，等待活体记录上传
	public void waiteAlive(int time) {
		updateJlabel("请将设备摄像头正对自己，连续识别" + time + "次，等待设备上传记录！");
		int timeTemp = 0;
		if (null == waiteAliveRecognitionList) {
			waiteAliveRecognitionList = new ArrayList<String>();
		}else {
			waiteAliveRecognitionList.clear();
		}
		while(time > 0) {
			timeTemp = 0;
			while(true) {
				if(recieveInThreaScord(UploadRecords.getName())) {
					nextProcedureIsOk = true;
					updateJlabel("还需继续识别：" + --time + "次");
					UploadRecords.setName(null);
					break;
				}
				if (++timeTemp > TIME){
					nextProcedureIsOk = false;
					break;
				}
//				System.out.println(System.currentTimeMillis() + "  " + timeTemp);
			}
			//若第一次记录未上传 直接打断
			if (!nextProcedureIsOk){
				updateJlabel("<html>设备"+deviceInfo.serialNumber + "连续" + TIME * WAIT_TIME/1000 +"秒未上传记录，测试失败，请检查网络！10s后开始下一台测试！！！</html>");
				mywait(10*1000);
				break;
			}
		}
		if(nextProcedureIsOk) {
			updateJlabel("活体识别正常，测试完成！清除设备权限中。。。");
			mywait(1000);
		}
//		writeLogs(nextProcedureIsOk, deviceInfo.serialNumber);
		//记录上报功能正常
//		writeLogs(nextProcedureIsOk, deviceInfo.serialNumber);
	}

	//10、清除设备人脸权限（巨龙设备恢复出厂不删除人脸）

	//11、设备恢复出厂状态，退出登陆状态
	public void returnToFactory() {
		//10、恢复出厂设置
		updateJlabel("设备恢复出厂中（等待设备重启）。。。");
		nextProcedureIsOk = jvtDeviceDll.getControlDevice(deviceInfo.loginID, 5);
//		writeLogs(nextProcedureIsOk, deviceInfo.serialNumber);
		//最后完成测试后，写日志
		if (nextProcedureIsOk) {
			LogsWriter.write(LogsWriter.FACTORY_TEST, resultLogMap.get(deviceInfo.serialNumber).toString());
		}
		//11、注销登陆: 
		jvtDeviceDll.getLogonOut(deviceInfo.loginID);
		mywait(1000);	//
		updateJlabel("本次测试完成，该台设备合格：设备ID：" + deviceInfo.serialNumber);
		mywait(1000);
		updateJlabel("请拔掉当前测试设备的网线！10s后开始下一台测试！！！");
		mywait(10*1000);
	}
	
	/**
	 * 这个方法中做了updateJlabel 文字提示功能，
	 * 该方法更多用在提示后停留一段时间，方便观察
	 * @param time
	 */
	public void mywait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateJlabel(String data) {
		MainIntfaceView.getTestLogsJlabell().setText(data);
	}
	
	public boolean getBreakTest() {
		if (setBreak) {
			updateJlabel("手动中断测试!!!");
			if(null != deviceInfo && !resultLogMap.isEmpty()) {
				resultLogMap.get(deviceInfo.serialNumber).append("手动中断测试，本次测试失败");
			}
		}
		return setBreak;
	}

	
	/**
	 * WAIT_TIME 秒内循环判断data是否为空，
	 * @param data
	 * @return data 为空 返回false  data 非空返回true
	 */
	private boolean recieveInThreaScord(Object data) {
		int timeTemp = 0;
//		int temp = 200;
		boolean recieveNotNull = false;
		
		while (timeTemp < WAIT_TIME) {
			if (null != data) {
				switch (data.getClass().getSimpleName()) {
				
				case "Boolean":
					if ((boolean) data) {
						recieveNotNull = true;
					}
					break;
	
				default:
					recieveNotNull = true;
					break;
				}
			}
			if(recieveNotNull) {
				break;
			}
			/*
			 * 当记录连续上传时，这里延迟200毫秒会导致误判没有记录上传
			 */
//			mywait(temp);
			mywait(1);
			timeTemp += 1;
		}
		return recieveNotNull;
	}

	public static void setSetBreak(boolean setBreak) {
		TestProcedure.setBreak = setBreak;
	}
	public StringBuffer getResult() {
		return result;
	}
	public void setResult(StringBuffer result) {
		this.result = result;
	}
}
