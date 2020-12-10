package comm.myLibraryLoad.jvt.myStructure;

public class JvtErrorCodeStructure {
	private final int VIDEONET_NOERROR = 0;			//没有错误
	private final int VIDEONET_SUCCESS = 1;				//返回成功
	private final int VIDEONET_SDK_NOTVALID = -10000;			//非法请求
	private final int VIDEONET_NO_INIT = -10001;			//SDK未经初始化
	private final int VIDEONET_ILLEGAL_PARAM = -10002;			//用户参数不合法
	private final int VIDEONET_INVALID_HANDLE = -10003;	//句柄无效
	private final int VIDEONET_SDK_UNINIT_ERROR = -10004;	//SDK清理出错
	private final int VIDEONET_SDK_TIMEOUT = -10005;	//等待超时
	private final int VIDEONET_SDK_MEMORY_ERROR = -10006;	//内存错误，创建内存失败
	private final int VIDEONET_SDK_NET_ERROR = -10007;	//网络错误
	private final int VIDEONET_SDK_OPEN_FILE_ERROR = -10008;	//打开文件失败
	private final int VIDEONET_SDK_UNKNOWNERROR = -10009;	//未知错误
	private final int VIDEONET_DEV_VER_NOMATCH = -11000;	//收到数据不正确，可能版本不匹配
	private final int VIDEONET_SDK_NOTSUPPORT = -11001;	//版本不支持
	private final int VIDEONET_OPEN_CHANNEL_ERROR = -11200;	//打开通道失败
	private final int VIDEONET_CLOSE_CHANNEL_ERROR = -11201;	//关闭通道失败
	private final int VIDEONET_SUB_CONNECT_ERROR = -11202;	//建立媒体子连接失败
	private final int VIDEONET_SUB_CONNECT_SEND_ERROR = -11203;	//媒体子连接通讯失败
	private final int VIDEONET_NATCONNET_REACHED_MAX = -11204;//Nat视频链接达到最大，不允许新的Nat视频链接
			/// 用户管理部分错误码
	private final int VIDEONET_NOPOWER = -11300;	//无权限
	private final int VIDEONET_PASSWORD_NOT_VALID = -11301;	// 账号密码不对
	private final int VIDEONET_LOGIN_USER_NOEXIST = -11302;	//用户不存在
	private final int VIDEONET_USER_LOCKED = -11303;	// 该用户被锁定
	private final int VIDEONET_USER_IN_BLACKLIST = -11304;	// 该用户不允许访问(在黑名单中)
	private final int VIDEONET_USER_HAS_USED = -11305;	// 该用户以登陆
	private final int VIDEONET_USER_NOT_LOGIN = -11306;	// 该用户没有登陆
	private final int VIDEONET_CONNECT_DEVICE_ERROR  = -11307;	//可能设备不存在
	private final int VIDEONET_ACCOUNT_INPUT_NOT_VALID = -11308;	//用户管理输入不合法
	private final int VIDEONET_ACCOUNT_OVERLAP = -11309;	//索引重复
	private final int VIDEONET_ACCOUNT_OBJECT_NONE = -11310;	//不存在对象, 用于查询时
	private final int VIDEONET_ACCOUNT_OBJECT_NOT_VALID= -11311;	//不存在对象
	private final int VIDEONET_ACCOUNT_OBJECT_IN_USE = -11312;	//对象正在使用
	private final int VIDEONET_ACCOUNT_SUBSET_OVERLAP = -11313;	//子集超范围 (如组的权限超过权限表，用户权限超出组的权限范围等等)
	private final int VIDEONET_ACCOUNT_PWD_NOT_VALID = -11314;	//密码不正确
	private final int VIDEONET_ACCOUNT_PWD_NOT_MATCH = -11315;	//密码不匹配
	private final int VIDEONET_ACCOUNT_RESERVED = -11316;	//保留帐号
			/// 配置管理相关错误码
	private final int VIDEONET_OPT_RESTART = -11400;	// 保存配置后需要重启应用程序
	private final int VIDEONET_OPT_REBOOT = -11401;	// 需要重启系统
	private final int VIDEONET_OPT_FILE_ERROR = -11402;	// 写文件出错
	private final int VIDEONET_OPT_CAPS_ERROR = -11403;	// 配置特性不支持
	private final int VIDEONET_OPT_VALIDATE_ERROR = -11404;	// 配置校验失败
	private final int VIDEONET_OPT_CONFIG_NOT_EXIST = -11405;	// 请求或者设置的配置不存在
	private final int VIDEONET_CTRL_PAUSE_ERROR = -11500;	//暂停失败
	private final int VIDEONET_SDK_NOTFOUND = -11501;	//查找失败，没有找到对应文件
	private final int VIDEONET_CFG_NOT_ENABLE = -11502;//配置未启用
	private final int VIDEONET_DECORD_FAIL = -11503;//解码失败
			//DNS协议解析返回错误码
	private final int VIDEONET_SOCKET_ERROR = -11600;	//创建套节字失败
	private final int VIDEONET_SOCKET_CONNECT = -11601;//连接套节字失败
	private final int VIDEONET_SOCKET_DOMAIN = -11602;//域名解析失败
	private final int VIDEONET_SOCKET_SEND = -11603; //发送数据失败
	private final int VIDEONET_ARSP_NO_DEVICE = -11604;//没有获取到设备信息，设备应该不在线
	private final int VIDEONET_ARSP_BUSING = -11605;//ARSP服务繁忙
	private final int VIDEONET_ARSP_BUSING_SELECT = -11606;//ARSP服务繁忙,select失败
	private final int VIDEONET_ARSP_BUSING_RECVICE = -11607;//ARSP服务繁忙,recvice失败
	private final int VIDEONET_CONNECTSERVER_ERROR = -11608;	//连接服务器失败
	private final int VIDEONET_CONNECT_FULL = -11612;		//服务器连接数已满
			//版权相关
	private final int VIDEONET_PIRATESOFTWARE =-11700;		//设备盗版
	
	public String getErrorCodeCh(int errcode) {
		String ErrorString = null;
		switch (errcode) {
		case VIDEONET_NOERROR:
			ErrorString = "没有错误";
			break;
		case VIDEONET_SUCCESS:
			ErrorString = "返回成功";
			break;
		case VIDEONET_SDK_NOTVALID:
			ErrorString = "非法请求";
			break;
		case VIDEONET_NO_INIT:
			ErrorString = "SDK未经初始化";
			break;
		case VIDEONET_ILLEGAL_PARAM:
			ErrorString = "用户参数不合法";
			break;
		case VIDEONET_INVALID_HANDLE:
			ErrorString = "句柄无效";
			break;
		case VIDEONET_SDK_UNINIT_ERROR:
			ErrorString = "SDK清理出错";
			break;
		case VIDEONET_SDK_TIMEOUT:
			ErrorString = "等待超时";
			break;
		case VIDEONET_SDK_MEMORY_ERROR:
			ErrorString = "内存错误，创建内存失败";
			break;
		case VIDEONET_SDK_NET_ERROR:
			ErrorString = "网络错误";
			break;
		case VIDEONET_SDK_OPEN_FILE_ERROR:
			ErrorString = "打开文件失败";
			break;
		case VIDEONET_SDK_UNKNOWNERROR:
			ErrorString = "未知错误";
			break;
		case VIDEONET_DEV_VER_NOMATCH:
			ErrorString = "收到数据不正确，可能版本不匹配";
			break;
		case VIDEONET_SDK_NOTSUPPORT:
			ErrorString = "版本不支持";
			break;
		case VIDEONET_OPEN_CHANNEL_ERROR:
			ErrorString = "打开通道失败";
			break;
		case VIDEONET_CLOSE_CHANNEL_ERROR:
			ErrorString = "关闭通道失败";
			break;
		case VIDEONET_SUB_CONNECT_ERROR:
			ErrorString = "建立媒体子连接失败";
			break;
		case VIDEONET_SUB_CONNECT_SEND_ERROR:
			ErrorString = "媒体子连接通讯失败";
			break;
		case VIDEONET_NATCONNET_REACHED_MAX:
			ErrorString = "Nat视频链接达到最大，不允许新的Nat视频链接";
	// 用户管理部分错误码
			break;
		case VIDEONET_NOPOWER:
			ErrorString = "无权限";
			break;
		case VIDEONET_PASSWORD_NOT_VALID:
			ErrorString = "账号密码不对";
			break;
		case VIDEONET_LOGIN_USER_NOEXIST:
			ErrorString = "用户不存在";
			break;
		case VIDEONET_USER_LOCKED:
			ErrorString = "该用户被锁定";
			break;
		case VIDEONET_USER_IN_BLACKLIST:
			ErrorString = "该用户不允许访问(在黑名单中)";
			break;
		case VIDEONET_USER_HAS_USED:
			ErrorString = "该用户以登陆";
			break;
		case VIDEONET_USER_NOT_LOGIN:
			ErrorString = "该用户没有登陆";
			break;
		case VIDEONET_CONNECT_DEVICE_ERROR:
			ErrorString = "可能设备不存在";
			break;
		case VIDEONET_ACCOUNT_INPUT_NOT_VALID:
			ErrorString = "用户管理输入不合法";
			break;
		case VIDEONET_ACCOUNT_OVERLAP:
			ErrorString = "索引重复";
			break;
		case VIDEONET_ACCOUNT_OBJECT_NONE:
			ErrorString = "不存在对象, 用于查询时";
			break;
		case VIDEONET_ACCOUNT_OBJECT_NOT_VALID:
			ErrorString = "不存在对象";
			break;
		case VIDEONET_ACCOUNT_OBJECT_IN_USE:
			ErrorString = "对象正在使用";
			break;
		case VIDEONET_ACCOUNT_SUBSET_OVERLAP:
			ErrorString = "子集超范围 (如组的权限超过权限表，用户权限超出组的权限范围等等)";
			break;
		case VIDEONET_ACCOUNT_PWD_NOT_VALID:
			ErrorString = "密码不正确";
			break;
		case VIDEONET_ACCOUNT_PWD_NOT_MATCH:
			ErrorString = "密码不匹配";
			break;
		case VIDEONET_ACCOUNT_RESERVED:
			ErrorString = "保留帐号";
	// 配置管理相关错误码
			break;
		case VIDEONET_OPT_RESTART:
			ErrorString = "保存配置后需要重启应用程序";
			break;
		case VIDEONET_OPT_REBOOT:
			ErrorString = "需要重启系统";
			break;
		case VIDEONET_OPT_FILE_ERROR:
			ErrorString = "写文件出错";
			break;
		case VIDEONET_OPT_CAPS_ERROR:
			ErrorString = "配置特性不支持";
			break;
		case VIDEONET_OPT_VALIDATE_ERROR:
			ErrorString = "配置校验失败";
			break;
		case VIDEONET_OPT_CONFIG_NOT_EXIST:
			ErrorString = "请求或者设置的配置不存在";
			break;
		case VIDEONET_CTRL_PAUSE_ERROR:
			ErrorString = "暂停失败";
			break;
		case VIDEONET_SDK_NOTFOUND:
			ErrorString = "查找失败，没有找到对应文件";
			break;
		case VIDEONET_CFG_NOT_ENABLE:
			ErrorString = "配置未启用";
			break;
		case VIDEONET_DECORD_FAIL:
			ErrorString = "解码失败";
	//DNS协议解析返回错误码
			break;
		case VIDEONET_SOCKET_ERROR:
			ErrorString = "创建套节字失败";
			break;
		case VIDEONET_SOCKET_CONNECT:
			ErrorString = "连接套节字失败";
			break;
		case VIDEONET_SOCKET_DOMAIN:
			ErrorString = "域名解析失败";
			break;
		case VIDEONET_SOCKET_SEND:
			ErrorString = "发送数据失败";
			break;
		case VIDEONET_ARSP_NO_DEVICE:
			ErrorString = "没有获取到设备信息，设备应该不在线";
			break;
		case VIDEONET_ARSP_BUSING:
			ErrorString = "ARSP服务繁忙";
			break;
		case VIDEONET_ARSP_BUSING_SELECT:
			ErrorString = "ARSP服务繁忙,select失败";
			break;
		case VIDEONET_ARSP_BUSING_RECVICE:
			ErrorString = "ARSP服务繁忙,recvice失败";
			break;
		case VIDEONET_CONNECTSERVER_ERROR:
			ErrorString = "连接服务器失败";
			break;
		case VIDEONET_CONNECT_FULL:
			ErrorString = "服务器连接数已满";
			//版权相关
			break;
		case VIDEONET_PIRATESOFTWARE:
			ErrorString = "设备盗版";
			break;

		default:
			ErrorString = "未定义的错误类型（" + errcode + "）";
			break;
		}
		return ErrorString;
	}
}
