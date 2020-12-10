package sqlite3.table;
/**
 * 表名：face_dev_parameter
 * @author yangdang
 *
 */
public class FaceDeviceParameterTable {
	public String devId;    //设备ID，与com_dev.dev_Id相关(数据库自增长)   
	public String devMacAddress;    //设备Mac地址，与com_dev.dev_mac_address相关
	//设备基本功能
	public String appPassWord;    //应用程序管理密码
	public int devUseType;    //设备使用类型(1:仅身份识别 2:普通门禁 3:高级门禁 4:消费 99:其他)
	public int isSupportWrcode;   //二维码识别(0:设备不支持 1:二维码识别开启 2:二维码识别关闭)
	public int isSupportCard; //是否支持刷卡(1:刷卡开启 2:刷卡关闭)
	public int triggerActionErval;      //同一人识别间隔时间(毫秒)
	public int relayTime;    //继电器脉冲时间(毫秒)
	//语音提示相关
	public String recognizeSuccessTip;    //识别成功提示语：@识别成功，@代表姓名
	public String recognizeErrorTip;     //识别失败提示语
	public String attendanceTime;     //工作时段(HH:MM-HH:MM)
	public String beforeJobTip;        //上班之前提示语：@识别成功，@代表姓名
	public String afterJobTip;     //下班之后提示语：@上班一天辛苦了，@代表姓名
	//高级功能基本参数
	public int isAutoRestart;     //设备是否自动重启(0:设备不支持 1:开启 2:关闭)
	public String dailyTestartTime;     //设备每天重启时间(HH:MM:SS)
	public int isUploadPassImg;      //是否上传通行记录照片(1:开启 2关闭)
	public int mainUiType ;        //首页UI类型(0:设备不支持 1:不指定识别区域 2:指定识别区域)
	public int enableScreenSaver;     //是否开启屏保(0:设备不支持 1:开启 2:关闭)
	public int heartBeatErval;     //心跳间隔时间(毫秒)
	public int wiegandType;    //韦根类型(26;34)
	public int wiegandIn;   //韦根输入(0:设备不支持 1:大端格式 2:小端格式)
	public int wiegandOut;  //韦根输出(0:大端格式 1:小端格式)
	public String opendDoorPassWord;   //开门密码
	//算法参数
	public int featureType  ;   //算法类别(只读，设备厂家自定义 1:云从 2:旷世 3:地平线 99:其他)
	public String featureSdkValue;    //算法SDK值(只读)
	public String featureVersion;       //算法版本号(只读)
	//识别参数
	public Double similityThreshold;  //相似度阈值
	public Double qualityThreshold;   //质量分阈值
	public int isAlive;    //活体检测开关(0:设备不支持 1:开启 2关闭)
	public Double livingThreshold;      //活体阈值
	public int minFacePixel;     //抓拍人脸最小像素
	public int maxAcePixel;      //抓拍人脸最大像素
	//硬件参数
	public int suppleLightMode;       //补光灯模式(0:设备不支持 1:自动 2:常开 3:常关)
	public String suppleLightOpenTime;      //补光灯常开时间段(HH:MM-HH:MM); 选择常开时启用
	public int debugModeSwitch;       //调试模式开关(0:设备不支持 1:开启 2关闭)
	//高级门禁功能
	public String remoteCtrlDoorId  ;   //远程控制门点ID
	//其它与设备无关的统计参数
	public int reginId  ; //设备区域ID
	public int inOutFlag;     //进出标记 0:不分进出 1:进区域 2:出区域
	public int isKqUse;      //识别记录是否做考勤，0:不做考勤 1:上下班考勤 11:上班考勤 12:下班考勤
	//消费相关参数
	public String payUrl;      //支付服务URL(IP:Port)
	public String queryUrl;    //查询服务URL(IP:Port)
	public int deviceNum;    //设备号
	public int successInfoDuration;     //消费成功界面显示时长(单位为秒;限制最大不超过12S，最少不超过3S)
	public String DisplayTitle;    //副屏幕标语
	public String breakfastTime;    //早餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
	public String lunchTime;     //午餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
	public String dinnerTime;    //晚餐时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
	public String supperTime;    //夜宵时段(HH:MM-HH:MM，读取参数时更新，设置参数时以pos_mealtype为准)
	public int offlineSwitch;    //离线模式权限开关(0:不支持 1:支持)
	public int offlineSumLimit  ;     //离线消费金额限制(离线消费模式才能设置)
	public int offlineCountLimit  ;   //离线消费次数限制(离线消费模式才能设置)
	public int offlineTimeLimit  ;     //离线消费时间限制(单位：分钟。离线消费模式才能设置)
	public int ssCardSystemInit  ;    //是否已刷系统子卡(0:未刷 1:已刷)，未刷系统卡才能设置系统卡编号
	public int onlineWalletId1  ;     //无子卡模式在线主钱包号(未刷系统子卡才能设置)
	public int onlineWalletId2  ;     //无子卡模式在线辅助钱包号(未刷系统子卡才能设置)
	public String gmtCreate;    //格式为 "YYYY-MM-DD HH:MM:SS.SSS" 
	public String gmtModified;   //格式为 "YYYY-MM-DD HH:MM:SS.SSS" 
}
