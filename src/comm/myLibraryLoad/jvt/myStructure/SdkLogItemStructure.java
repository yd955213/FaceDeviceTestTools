package comm.myLibraryLoad.jvt.myStructure;
import java.util.Arrays;
import java.util.List;
import com.sun.jna.Structure;

public class SdkLogItemStructure extends Structure{
	/**日志类型*/
	public byte[] sType = new byte[24];
	/**日志用户*/
	public byte[] sUser = new byte[32];
	/**日志数据*/
	public byte[] sData = new byte[68];
	/**日志时间*/
	public SdkTimeSection.ByReference stLogTime = new SdkTimeSection.ByReference();
	/**从上次查询的结束时的日志指针*/
	public int iLogPosition;
	
	public static class ByReference extends SdkLogItemStructure implements Structure.ByReference{};
	public static class ByValue extends SdkLogItemStructure implements Structure.ByValue{}
	
	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(new String[] { 
				"sType", 
				"sUser", 
				"sData", 
				"stLogTime", 
				"iLogPosition"});
	}
	
}
