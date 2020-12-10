package comm.myLibraryLoad.jvt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.swing.JOptionPane;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary.StdCallCallback;
import comm.myLibraryLoad.jvt.myEnum.MyEnum;
import comm.myLibraryLoad.jvt.myStructure.DeviveInfoStructure;
import comm.myLibraryLoad.jvt.myStructure.JvtErrorCodeStructure;
import comm.myLibraryLoad.jvt.myStructure.SdkConfigNetCommonStructure;
import comm.myLibraryLoad.jvt.myStructure.SdkLogList;
import comm.myLibraryLoad.jvt.myStructure.SdkLogSearchConditionStructure;
import tools.MyString;
import view.MainIntfaceView;

public class JvtDeviceDll {
	private static boolean dllInitok = false;
	private NativeLong loginId;
	private final int SEATCH_TIME = 1000;

	private final String USER_NAME = "admin"; 
	private final String PASS_WORD = "admin";
	/**
	 * 巨龙设备sdk, 初始化sdk，软件关闭时调用 ：cleanSDK 方法
	 */
	private JvtDeviceDll () {
		dllInitok = CLibrary.INSTANCE.VideoNet_Init(new CLibrary.JavaCallbackImpl(), 123);
		if(!dllInitok) {
			JOptionPane.showMessageDialog(null, "初始化动态库失败", "343、354厂测", JOptionPane.ERROR_MESSAGE);
		}
//		System.out.println("dllInitok = " + dllInitok);
	}
	private static class JvtDeviceDllHandler{
		private static JvtDeviceDll instance = new JvtDeviceDll();
	}
	public static synchronized JvtDeviceDll getInstance() {
		return JvtDeviceDllHandler.instance;
	}
	
	public interface CLibrary extends Library {
		CLibrary INSTANCE = (CLibrary)Native.load(".\\libs\\win64_dlls\\VideoNetAPI.dll", CLibrary.class);
		/*
		 * Java模拟dll回调函数，
		 * 回调接口
		 */
		public interface JavaCallback extends StdCallCallback  {
			void fDisConnect(NativeLong loginID,String devIP, long devPor, long user);
		}
		/*
		 * 继承接口，如果回调有返回值，则做相应的处理；
		 */
		public static class JavaCallbackImpl implements JavaCallback{
			
			@Override
			public void fDisConnect(NativeLong loginID, String devIP, long devPor, long user) {
				// TODO Auto-generated method stub
				System.out.println("回调成功");
			}
		};
		/**
		 * 返回函数执行失败代码，当调用下面的接口失败时，可以用该函数获取失败的代码，具体错误代码参见错误类型代号说明
		 * @return
		 * VIDEONET_NOERROR					= 0,					//没有错误
			VIDEONET_SUCCESS					= 1,					//返回成功
			VIDEONET_SDK_NOTVALID			= -10000,				//非法请求
			VIDEONET_NO_INIT					= -10001,				//SDK未经初始化
			VIDEONET_ILLEGAL_PARAM			= -10002,			//用户参数不合法
			VIDEONET_INVALID_HANDLE			= -10003,			//句柄无效
			VIDEONET_SDK_UNINIT_ERROR		= -10004,			//SDK清理出错
			VIDEONET_SDK_TIMEOUT				= -10005,			//等待超时
			VIDEONET_SDK_MEMORY_ERROR		= -10006,			//内存错误，创建内存失败
			VIDEONET_SDK_NET_ERROR			= -10007,			//网络错误
			VIDEONET_SDK_OPEN_FILE_ERROR		= -10008,			//打开文件失败
			VIDEONET_SDK_UNKNOWNERROR		= -10009,			//未知错误
			VIDEONET_DEV_VER_NOMATCH			= -11000,			//收到数据不正确，可能版本不匹配
			VIDEONET_SDK_NOTSUPPORT			= -11001,			//版本不支持
			VIDEONET_OPEN_CHANNEL_ERROR		= -11200,			//打开通道失败
			VIDEONET_CLOSE_CHANNEL_ERROR		= -11201,			//关闭通道失败
			VIDEONET_SUB_CONNECT_ERROR		= -11202,			//建立媒体子连接失败
			VIDEONET_SUB_CONNECT_SEND_ERROR	= -11203,			//媒体子连接通讯失败
			VIDEONET_NATCONNET_REACHED_MAX  = -11204,           //Nat视频链接达到最大，不允许新的Nat视频链接
			/// 用户管理部分错误码
			VIDEONET_NOPOWER					= -11300,			//无权限
			VIDEONET_PASSWORD_NOT_VALID		= -11301,			// 账号密码不对
			VIDEONET_LOGIN_USER_NOEXIST		= -11302,			//用户不存在
			VIDEONET_USER_LOCKED				= -11303,			// 该用户被锁定
			VIDEONET_USER_IN_BLACKLIST		= -11304,			// 该用户不允许访问(在黑名单中)
			VIDEONET_USER_HAS_USED			= -11305,			// 该用户以登陆
			VIDEONET_USER_NOT_LOGIN			= -11306,			// 该用户没有登陆
			VIDEONET_CONNECT_DEVICE_ERROR    = -11307,			//可能设备不存在
			VIDEONET_ACCOUNT_INPUT_NOT_VALID = -11308,			//用户管理输入不合法
			VIDEONET_ACCOUNT_OVERLAP			= -11309,			//索引重复
			VIDEONET_ACCOUNT_OBJECT_NONE		= -11310,			//不存在对象, 用于查询时
			VIDEONET_ACCOUNT_OBJECT_NOT_VALID= -11311,			//不存在对象
			VIDEONET_ACCOUNT_OBJECT_IN_USE	= -11312,			//对象正在使用
			VIDEONET_ACCOUNT_SUBSET_OVERLAP	= -11313,			//子集超范围 (如组的权限超过权限表，用户权限超出组的权限范围等等)
			VIDEONET_ACCOUNT_PWD_NOT_VALID	= -11314,			//密码不正确
			VIDEONET_ACCOUNT_PWD_NOT_MATCH	= -11315,			//密码不匹配
			VIDEONET_ACCOUNT_RESERVED		= -11316,			//保留帐号
			/// 配置管理相关错误码
			VIDEONET_OPT_RESTART				= -11400,			// 保存配置后需要重启应用程序
			VIDEONET_OPT_REBOOT				= -11401,			// 需要重启系统
			VIDEONET_OPT_FILE_ERROR			= -11402,			// 写文件出错
			VIDEONET_OPT_CAPS_ERROR			= -11403,			// 配置特性不支持
			VIDEONET_OPT_VALIDATE_ERROR		= -11404,			// 配置校验失败
			VIDEONET_OPT_CONFIG_NOT_EXIST	= -11405,			// 请求或者设置的配置不存在
			VIDEONET_CTRL_PAUSE_ERROR		= -11500,			//暂停失败
			VIDEONET_SDK_NOTFOUND			= -11501,			//查找失败，没有找到对应文件
			VIDEONET_CFG_NOT_ENABLE         = -11502,           //配置未启用
			VIDEONET_DECORD_FAIL            = -11503,           //解码失败
			//DNS协议解析返回错误码
			VIDEONET_SOCKET_ERROR             = -11600,         //创建套节字失败
			VIDEONET_SOCKET_CONNECT           = -11601,         //连接套节字失败
			VIDEONET_SOCKET_DOMAIN            = -11602,         //域名解析失败
			VIDEONET_SOCKET_SEND              = -11603,         //发送数据失败
			VIDEONET_ARSP_NO_DEVICE           = -11604,         //没有获取到设备信息，设备应该不在线
			VIDEONET_ARSP_BUSING              = -11605,         //ARSP服务繁忙
			VIDEONET_ARSP_BUSING_SELECT       = -11606,         //ARSP服务繁忙,select失败
			VIDEONET_ARSP_BUSING_RECVICE	  = -11607,         //ARSP服务繁忙,recvice失败
			VIDEONET_CONNECTSERVER_ERROR      = -11608,        //连接服务器失败
			//
			VIDEONET_CONNECT_FULL      = -11612,              //服务器连接数已满
			//版权相关
			VIDEONET_PIRATESOFTWARE           =-11700,       //设备盗版
		 */
		NativeLong VideoNet_GetLastError();
		/**初始化SDK, 在所有的SDK函数之前调用*/
		boolean VideoNet_Init(JavaCallback callbackImpl,long user);
		/**初始化SDK的回调函数*/
		void fDisConnectCallBack(JavaCallback javaCallback);
		/**清空SDK, 释放占用的资源，在所有的SDK函数之后调用*/
		void VideoNet_Cleanup();
		
		/**搜索局域网内设备信息*/
		boolean VideoNet_SearchDevice(SdkConfigNetCommonStructure[] szBuf, int nBuflenth, IntByReference pRetLenth, int nSearchTime);
//		/**搜索设备局域网设备（设备设备，返回搜索的结果）*/
//		boolean VideoNet_SearchDeviceEX(NativeLong loginID, SdkNetDevListStructure.ByReference sdkNetDevListStructure, int transferProtocol, int waitTime);
//		/**注册用户到设备，当设备端把用户设置为复用（设备默认的用户如admin,不能设置为复用），则使用该帐号可以多次向设备注册*/
//		NativeLong VideoNet_Login(String devIP, int devPort, String userName, String passWord, DeviveInfo.ByReference deviveInfo, IntByReference error, int socketType);
		/**注册用户到设备的扩展接口，支持一个用户指定登陆的客户端类型*/
		NativeLong VideoNet_LoginEx(String devIP, int devPort, String userName, String passWord,DeviveInfoStructure.ByReference devifo, int type, IntByReference error);
		/**注销设备用户*/
		NativeLong VideoNet_Logout(NativeLong loginId);
		
		/**查询日志
		 * int waittime = 2000
		 */
		boolean VideoNet_FindDVRLog(NativeLong loginId, SdkLogSearchConditionStructure.ByReference sdkLogByReference, SdkLogList.ByReference logsByReference, long bufSize, int waitTime);
		/**
		 * 重启和清除日志
		 * @param loginId VideoNet_Login的返回值
		 * @param type 控制类型：0 重启设备、1 清除日志、2、关机、3、恢复记录日志、4停止记录日志、 5 恢复出厂
		 * @param waitTime = 2000
		 * @return 成功返回TRUE，失败返回FALSE
		 */
		boolean VideoNet_ControlDVR(NativeLong loginId, int type, int waitTime);
		
		
	}
	
	/**
	 * 调用前，前保证所有设备已注销注册用了 :getLogonOut(int loginId)
	 */
	public static void cleanSDK() {
		if (dllInitok) {
			CLibrary.INSTANCE.VideoNet_Cleanup();
		}
	}
	/**注销设备用户
	  */
	public void getLogonOut(int loginId) {
		CLibrary.INSTANCE.VideoNet_Logout(new NativeLong(loginId));
	}
	/**
	 * 
	 * @param loginId 设备注册ID
	 * @param controlType 控制类型：0 重启设备、，1 清除日志、2、关机、3、恢复记录日志、4停止记录日志、 5 恢复出厂
	 */
	public boolean getControlDevice(int loginId, int controlType) {
		boolean ok = CLibrary.INSTANCE.VideoNet_ControlDVR(new NativeLong(loginId), controlType, 2000);
		if (ok) {
//			System.out.println(getControlDeviceinfo(controlType) + "成功");
		}
		return ok;
	}
	

	/**
	 * 搜索设备，获取设备IP，TCP端口信息
	 * @return List<DeviceInfo>
	 */
	public List<DeviceInfo> getSearchDeviceInfoList(){
		List<DeviceInfo> list = getSearchDevice();
		// 出厂设备:用户名：admin、密码默认 admin  dll搜索到的用户名和密码为空，需要手工添加
		for(DeviceInfo deviceInfo: list) {
			deviceInfo.userName = USER_NAME;
			deviceInfo.passWord = PASS_WORD;
		}
		return list;
	}
	/**
	 * 登录设备，获取设备serialNumber(对应DAS的MAC地址)，需要密码DeviceInfo。passWord，密码通过GetDeviceParams接口获取：AppParams。AppPassword
	 * getLoginExDeviceInfoList调用前，先调用getSearchDeviceInfoList接口获取设备IP，端口信息，否则登录失败 返回null
	 * @return List<DeviceInfo>
	 */
	public List<DeviceInfo> getLoginExDeviceInfoList(List<DeviceInfo> deviveInfoList){
		
		deviveInfoList = getLoginEx(deviveInfoList);
		return deviveInfoList;
	}
	/**
	 *返回函数执行失败代码
	 * @return
	 */
	public String getErrorCode() {
		return new JvtErrorCodeStructure().getErrorCodeCh(CLibrary.INSTANCE.VideoNet_GetLastError().intValue());
	}
	/**
	 * 搜索设备
	 */
	private List<DeviceInfo> getSearchDevice() {
		List<DeviceInfo> devInfoList = new ArrayList<DeviceInfo>();
		DeviceInfo deviceInfo = null;
		if (dllInitok) {
			SdkConfigNetCommonStructure[] sdkConfigNetCommonStructures = new SdkConfigNetCommonStructure[100];
			int nBuflenth = sdkConfigNetCommonStructures.length*100;;
			IntByReference pRetLenth = new IntByReference();
			String ip = null;
			String ipTemp = "0.0.0.0";
			MyString myString = new MyString();
			
			if(CLibrary.INSTANCE.VideoNet_SearchDevice(sdkConfigNetCommonStructures, nBuflenth, pRetLenth, SEATCH_TIME)) {
				int lenght = pRetLenth.getValue()/sdkConfigNetCommonStructures.length;
				try {
					for (int i = 0 ; i < lenght; i++) {
						ip = myString.getHexStringToIP(myString.getByteArr2HexStr(sdkConfigNetCommonStructures[i].HostIP));
						//安装厂家的说法lenght 等于返回的设备数，但我这边实测：length的实际数量为设备数的7倍，防止意外情况，这里通过判断ip是否为全0来筛选设备
						if (ip != null && !ip.equals(ipTemp) ) {
							deviceInfo = new DeviceInfo();
							deviceInfo.devIp = ip;
							deviceInfo.hostName = myString.getByteToAscii(sdkConfigNetCommonStructures[i].HostName);
							deviceInfo.submask = myString.getByteToAscii(sdkConfigNetCommonStructures[i].Submask);
							deviceInfo.gateway = myString.getByteToAscii(sdkConfigNetCommonStructures[i].Gateway);
							deviceInfo.mac = myString.getByteToAscii(sdkConfigNetCommonStructures[i].sMac).substring(0, 18);
							deviceInfo.httpPort = sdkConfigNetCommonStructures[i].HttpPort;
							deviceInfo.tcpPort = sdkConfigNetCommonStructures[i].TCPPort;
							deviceInfo.sslPort = sdkConfigNetCommonStructures[i].SSLPort;
							deviceInfo.udpPort = sdkConfigNetCommonStructures[i].UDPPort;
							deviceInfo.userName = myString.getByteToAscii(sdkConfigNetCommonStructures[i].szUserName);
							deviceInfo.passWord = myString.getByteToAscii(sdkConfigNetCommonStructures[i].szPassword);
							devInfoList.add(deviceInfo);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainIntfaceView.getJvtSearchDeviceJlabel().setText("共搜索到： " + devInfoList.size() + "  台设备");
				
			}
		}
		return devInfoList;
	}
	
	/**
	 * 设置注册或者登录 登录失败 返回登录成功的设备
	 * @return
	 * @throws InterruptedException 
	 */
	private synchronized List<DeviceInfo> getLoginEx(List<DeviceInfo> deviceInfoList) {
		int size = deviceInfoList.size();
		MyString myString = new MyString();
		List<DeviceInfo> deviceInfoListTemp = new ArrayList<DeviceInfo>();
		CountDownLatch countDownLatch = new CountDownLatch(size);
		/*
		 * 使用CountDownLatch，多线程登录设备获取MAC地址
		 */
		for (DeviceInfo deviceInfo : deviceInfoList) {
			new Thread(()-> {
				IntByReference error = new IntByReference();
				DeviveInfoStructure.ByReference deviveInfoByReference = new DeviveInfoStructure.ByReference();
				NativeLong loginId = CLibrary.INSTANCE.VideoNet_LoginEx(deviceInfo.devIp, deviceInfo.tcpPort, deviceInfo.userName, deviceInfo.passWord, deviveInfoByReference, MyEnum.LoginType.LOGIN_TYPE_GUI, error);
//					大于0 则登录成功
				if (loginId.intValue() > 0) {
					deviceInfo.serialNumber = myString.getByteToAscii(deviveInfoByReference.sSerialNumber);
					deviceInfo.softWareVersion = myString.getByteToAscii(deviveInfoByReference.softWareVersion);
					deviceInfo.hardWareVersion = myString.getByteToAscii(deviveInfoByReference.hardWareVersion);
					deviceInfo.encryptVersion = myString.getByteToAscii(deviveInfoByReference.encryptVersion);
					deviceInfo.tmBuildTime = deviveInfoByReference.tmBuildTime.toData();
					deviceInfo.uiDeviceRunTime = Integer.toString(deviveInfoByReference.uiDeviceRunTime);
					deviceInfo.deviceTye = deviveInfoByReference.deviceTye;
					deviceInfo.hardWare = myString.getByteToAscii(deviveInfoByReference.hardWare);
					deviceInfo.updataTime = myString.getByteToAscii(deviveInfoByReference.updataTime);
					deviceInfo.loginID = loginId.intValue();
					deviceInfoListTemp.add(deviceInfo);
				}
				countDownLatch.countDown();
			}).start();
		}
//		等待所有线程执行完成
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deviceInfoListTemp;
	}

	@SuppressWarnings("unused")
	private String getControlDeviceinfo(int controlType) {
		// TODO Auto-generated method stub
		String data = null;
		switch (controlType) {
		case 0:
			data = "重启设备";
			break;
			
		case 1:
			data = "清除日志";
			break;
			
		case 2:
			data = "关机";
			break;
			
		case 3:
			data = "恢复记录日志";
			break;
			
		case 4:
			data = "停止记录日志";
			break;
			
		case 5:
			data = "恢复出厂";
			break;
			
		default:
			data = "未定义类型：" +Integer.toString(controlType);
			break;
		}
		return data;
	}
	/**
	 * 测试方法
	 */
	public void getsomeTest() {
		/*
		 * 注册SDK
		 */
		boolean a = CLibrary.INSTANCE.VideoNet_Init(new CLibrary.JavaCallbackImpl(), 123);
		System.out.println("a = " + a);
		
//		Pointer szBuf = new Memory(1024); 
//		int nBuflenth = 1024;
//		IntByReference pRetLenth = new IntByReference(1024); 
//		int nSearchTime = 1500;
//    	System.out.println("a = " + CLibrary.INSTANCE.VideoNet_SearchDevice(szBuf, nBuflenth, pRetLenth, nSearchTime));
//		Long b = CLibrary.INSTANCE.VideoNet_LoginEx("", new NativeLong(0), "admin", "admin", new DeviveInfo.ByReference(), LoginType.LOGIN_TYPE_AUTOSEARCH, new IntByReference());
//		System.out.println("b = " + b);
//		System.out.println(CLibrary.INSTANCE.VideoNet_GetLastError());
//		SdkNetDevList value = new SdkNetDevList();
//		
//		System.out.println("a = " + CLibrary.INSTANCE.VideoNet_SearchDeviceEX(new NativeLong(9999999), value, SDK_TransferProtocol.SDK_TRANSFER_PROTOCOL_NETIP, 15000));
		/*
		 * 搜索设备
		 */
		SdkConfigNetCommonStructure[] szBuf = new SdkConfigNetCommonStructure[100];
		int nBuflenth = szBuf.length*100;
		IntByReference pRetLenth = new IntByReference();
		int nSearchTime = 1000;
		System.out.println("a = " + CLibrary.INSTANCE.VideoNet_SearchDevice(szBuf, nBuflenth, pRetLenth, nSearchTime));
		String iptemp = "0.0.0.0";
		String ip = null;
		int port = 0;
		MyString myString = new MyString();
		int lenght = pRetLenth.getValue()/szBuf.length;
		System.out.println("lenght = " + lenght);
		for (int i = 0 ; i < lenght; i++) {
			try {
				System.out.println("ip["+i+"]=" + myString.getHexStringToIP(myString.getByteArr2HexStr(szBuf[i].HostIP)));
				if (!iptemp.equals(myString.getHexStringToIP(myString.getByteArr2HexStr(szBuf[i].HostIP)))) {
					System.out.println("hostName = " + myString.getByteToAscii(szBuf[i].HostName));
					ip = myString.getHexStringToIP(myString.getByteArr2HexStr(szBuf[i].HostIP));
					System.out.println("ip = " + ip);
					System.out.println("Submask = " + myString.getHexStringToIP(myString.getByteArr2HexStr(szBuf[i].Submask)));
					System.out.println("Gateway = " + myString.getHexStringToIP(myString.getByteArr2HexStr(szBuf[i].Gateway)));
					System.out.println("HttpPort = " + szBuf[i].HttpPort);
					System.out.println("TCPPort = " + szBuf[i].TCPPort);
					port = szBuf[i].TCPPort;
					System.out.println("SSLPort = " + szBuf[i].SSLPort);
					System.out.println("UDPPort = " + szBuf[i].UDPPort);
					System.out.println("MaxConn = " + szBuf[i].MaxConn);
					System.out.println("MonMode = " + szBuf[i].MonMode);
					System.out.println("MaxBps = " + szBuf[i].MaxBps);
					System.out.println("TransferPlan = " + szBuf[i].TransferPlan);
					System.out.println("bUseHSDownLoad = " + szBuf[i].bUseHSDownLoad);
					System.out.println("sMac = " + myString.getByteToAscii(szBuf[i].sMac));
					System.out.println("sSn = " + myString.getByteToAscii(szBuf[i].sSn));
					System.out.println("ChannelNum = " + szBuf[i].ChannelNum);
					System.out.println("DeviceType = " + szBuf[i].DeviceType);
					System.out.println("szUserName = " + myString.getByteToAscii(szBuf[i].szUserName));
					System.out.println("szPassword = " + myString.getByteToAscii(szBuf[i].szPassword));
					System.out.println("szKernelVersion = " + myString.getByteToAscii(szBuf[i].szKernelVersion));
					System.out.println("szWebVersion = " + myString.getByteToAscii(szBuf[i].szWebVersion));
					System.out.println("szIPV6 = " + myString.getHexStringToIP(myString.getByteArr2HexStr(szBuf[i].szIPV6)));
					System.out.println("szAlgorithmVersion = " + myString.getByteToAscii(szBuf[i].szAlgorithmVersion));
					System.out.println("szModelVersion = " + myString.getByteToAscii(szBuf[i].szModelVersion));
					System.out.println("szCustomer = " + myString.getByteToAscii(szBuf[i].szCustomer));
					System.out.println("SubIP = " + myString.getHexStringToIP(myString.getByteArr2HexStr(szBuf[i].SubIP)));
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//    	System.out.println("pRetLenth = " + pRetLenth.getValue()/szBuf.length);
		/*
		 * 设备注册
		 */
//		DeviveInfo.ByValue deviveInfo = new DeviveInfo.ByValue();
		DeviveInfoStructure.ByReference deviveInfo = new DeviveInfoStructure.ByReference();
		IntByReference error = new IntByReference();
		
//		deviveInfo.write();
		System.out.println("IP = " + ip);
		loginId = CLibrary.INSTANCE.VideoNet_LoginEx(ip, port, "admin", "Das123456", deviveInfo, MyEnum.LoginType.LOGIN_TYPE_GUI, error);
		System.out.println("VideoNet_Login = " + loginId);
		
		try {
			System.out.println("deviveInfo.softWareVersion = " + myString.getByteToAscii(deviveInfo.softWareVersion));
			System.out.println("deviveInfo.hardWareVersion = " + myString.getByteToAscii(deviveInfo.hardWareVersion));
			System.out.println("deviveInfo.encryptVersion = " + myString.getByteToAscii(deviveInfo.encryptVersion));
			System.out.println("deviveInfo.tmBuildTime = " + deviveInfo.tmBuildTime.toData());
			System.out.println("deviveInfo.sSerialNumber = " + myString.getByteToAscii(deviveInfo.sSerialNumber));
			System.out.println(myString.getByteToAscii(deviveInfo.sSerialNumber).length());
			System.out.println("deviveInfo.byChanNum = " + deviveInfo.byChanNum);
			System.out.println("deviveInfo.iVideoOutChannel = " + deviveInfo.iVideoOutChannel);
			System.out.println("deviveInfo.byAlarmInPortNum = " + deviveInfo.byAlarmInPortNum);
			System.out.println("deviveInfo.byAlarmOutPortNum = " + deviveInfo.byAlarmOutPortNum);
			System.out.println("deviveInfo.iTalkInChannel = " + deviveInfo.iTalkInChannel);
			System.out.println("deviveInfo.iTalkOutChannel = " + deviveInfo.iTalkOutChannel);
			System.out.println("deviveInfo.iExtraChannel = " + deviveInfo.iExtraChannel);
			System.out.println("deviveInfo.iAudioInChannel = " + deviveInfo.iAudioInChannel);
			System.out.println("deviveInfo.iCombineSwitch = " + deviveInfo.iCombineSwitch);
			System.out.println("deviveInfo.iDigChannel = " + deviveInfo.iDigChannel);
			System.out.println("deviveInfo.uiDeviceRunTime = " + deviveInfo.uiDeviceRunTime);
			System.out.println("deviveInfo.deviceTye = " + deviveInfo.deviceTye);
			System.out.println("deviveInfo.hardWare = " + myString.getByteToAscii(deviveInfo.hardWare));
			System.out.println("deviveInfo.updataTime = " + myString.getByteToAscii(deviveInfo.updataTime));
			System.out.println("deviveInfo.sDeviceModel = " + myString.getByteToAscii(deviveInfo.sDeviceModel));
			System.out.println("deviveInfo.szOnvifModel = " + myString.getByteToAscii(deviveInfo.szOnvifModel));
			System.out.println("deviveInfo.sProtocol = " + myString.getByteToAscii(deviveInfo.sProtocol));
			System.out.println("deviveInfo.updataType = " + deviveInfo.updataType);
			System.out.println("deviveInfo.nLanguage = " + deviveInfo.nLanguage);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
//		SdkNetDevListStructure.ByReference sdkNetDevListStructure = new SdkNetDevListStructure.ByReference();
//		a = CLibrary.INSTANCE.VideoNet_SearchDeviceEX(loginId, sdkNetDevListStructure, MyEnum.SDK_TransferProtocol.SDK_TRANSFER_PROTOCOL_NETIP, 15000);
		System.out.println("VideoNet_SearchDeviceEX = " + a );
		System.out.println(CLibrary.INSTANCE.VideoNet_ControlDVR(loginId, 5, 2000));
		CLibrary.INSTANCE.VideoNet_Cleanup();
		
//		System.out.println("VideoNet_GetLastError = " + getErrorCode());
//		System.out.println("deviveInfo1 = " + deviveInfo1.byChanNum);
//		System.out.println(CLibrary.INSTANCE.VideoNet_Logout(loginId));
		
		//退出
//    	CLibrary.INSTANCE.VideoNet_Cleanup();
    }
}
