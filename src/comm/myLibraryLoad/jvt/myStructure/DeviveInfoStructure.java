package comm.myLibraryLoad.jvt.myStructure;

import java.util.Arrays;
import java.util.List;
import com.sun.jna.Structure;

import comm.myLibraryLoad.jvt.myEnum.MyEnum;

public class DeviveInfoStructure extends Structure{
	/**软件版本信息*/
	public byte[] softWareVersion = new byte[64];
	/**硬件版本信息*/
	public byte[] hardWareVersion = new byte[64];
	/**加密版本信息*/
	public byte[] encryptVersion = new byte[64];
	/**软件创建时间*/
	public SdkTimeSection tmBuildTime = new SdkTimeSection();
	/**设备序列号	*/
	public byte[] sSerialNumber = new byte[64];
	/**视频输入通道数*/
	public Integer byChanNum;
	/**视频输出通道数*/
	public Integer iVideoOutChannel;
	/**报警输入通道数*/
	public Integer byAlarmInPortNum;
	/**报警输出通道数*/
	public Integer byAlarmOutPortNum;
	/**对讲输入通道数*/
	public Integer iTalkInChannel;
	/**对讲输出通道数*/
	public Integer iTalkOutChannel;
	/**扩展通道数	*/
	public Integer iExtraChannel;
	/**音频输入通道数*/
	public Integer iAudioInChannel;
	/**组合编码通道分割模式是否支持切换*/
	public Integer iCombineSwitch;
	/**数字通道数*/
	public Integer iDigChannel;
	/**系统运行时间*/
	public Integer uiDeviceRunTime;
	/**设备类型*/
	public Integer deviceTye = MyEnum.SDK_DeviceType.SDK_DEVIVE_DEVR;	///
	/**设备型号*/
	public byte[] hardWare = new byte[64];
	/**更新日期 例如 2013-09-03 14:15:13*/
	public byte[] updataTime = new byte[20];
	/***/
	public Integer updataType;	///<更新内容
	/**设备型号(底层库从加密里获得，sHardWare针对多个设备用同一个程序这种情况区分不了)*/
	public byte[] sDeviceModel = new byte[16];       // 
	/**国家的语言ID,0英语 1中文 2中文繁体 3韩语 4德语 5葡萄牙语 6俄语*/
	public Integer nLanguage;
	/***/
	public byte[] szOnvifModel = new byte[32];
	/***/
	public byte[] sProtocol = new byte[16];
	
	public static class ByReference extends DeviveInfoStructure implements Structure.ByReference {}
    public static class ByValue extends DeviveInfoStructure implements Structure.ByValue {}
    
    protected List<String> getFieldOrder() {
    	return Arrays.asList(new String[] { 
    			"softWareVersion", 
    			"hardWareVersion", 
    			"encryptVersion", 
    			"tmBuildTime", 
    			"sSerialNumber", 
    			"byChanNum", 
    			"iVideoOutChannel", 
    			"byAlarmInPortNum", 
    			"byAlarmOutPortNum", 
    			"iTalkInChannel", 
    			"iTalkOutChannel", 
    			"iExtraChannel", 
    			"iAudioInChannel", 
    			"iCombineSwitch", 
    			"iDigChannel", 
    			"uiDeviceRunTime", 
    			"deviceTye", 
    			"hardWare", 
    			"updataTime", 
    			"updataType",
    			"sDeviceModel",
    			"nLanguage",
    			"szOnvifModel",
    			"sProtocol"});
    }
}
