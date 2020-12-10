package comm.myLibraryLoad.jvt.myEnum;

public class MyEnum {
	public static interface SocketStyle{
		/***/
		public static final int TCPSOCKET = 0;
		/***/
		public static final int UDPSOCKET = 1
		/***/;
		public static final int SOCKETNR = 2;
	};
	
	public static interface LoginType{
		/**本地GUI登陆:0*/
		public static final int LOGIN_TYPE_GUI = 0;
		/**控制台登陆:1*/
		public static final int LOGIN_TYPE_CONSOLE = 1;
		/**WEB登陆:2*/
		public static final int LOGIN_TYPE_WEB = 2;
		/**SNS登陆：3*/
		public static final int LOGIN_TYPE_SNS = 3;
		/**移动终端登陆：4*/
		public static final int LOGIN_TYPE_MOBIL = 4;
		/**网络键盘登陆：5*/
		public static final int LOGIN_TYPE_NETKEYBOARD = 5;
		/**中心服务器登陆：6*/
		public static final int LOGIN_TYPE_SERVER = 6;
		/**IP自动搜索工具登陆：7*/
		public static final int LOGIN_TYPE_AUTOSEARCH = 7;
		/**升级工具登陆：8*/
		public static final int LOGIN_TYPE_UPGRADE = 8;
		/**全球眼登陆：9*/
		public static final int LOGIN_TYPE_MEGAEYE = 9;
		/**登陆类型：10*/
		public static final int LOGIN_TYPE_NR = 10;
	};
	
	public static interface SDK_TransferProtocol{
		/**0*/
		public static final int SDK_TRANSFER_PROTOCOL_NETIP = 0;
		/**1*/
		public static final int SDK_TRANSFER_PROTOCOL_ONVIF = 1;
		/**2*/
		public static final int SDK_TRANSFER_TRANSFER_PROTOCOL_MAC = 2;
		/**5*/
		public static final int SDK_TRANSFER_PROTOCOL_NR_V2 = 5;
		/**128*/
		public static final int SDK_TRANSFER_PROTOCOL_ONVIF_DEFAULT = 128;
		/**6*/
		public static final int SDK_TRANSFER_PROTOCOL_ONVIF_NR_V2 = 6;
	};
	
	public static interface SDK_DeviceType{
		/**普通DVR设备:0*/
		public static final int SDK_DEVIVE_DEVR = 0;
		/**NVS设备:1*/
		public static final int SDK_DEVIVE_NVS = 1;
		/**IPC设备:2*/
		public static final int SDK_DEVIVE_IPC = 2;
		/**混合dvr:3*/
		public static final int SDK_DEVIVE_HVR = 3;
		/**智能dvr:4*/
		public static final int SDK_DEVIVE_IVR = 4;
		/**车载dvr:5*/
		public static final int SDK_DEVIVE_MVR = 5;
		/**6*/
		public static final int SDK_DEVIVE_NR = 6;
	};
}
