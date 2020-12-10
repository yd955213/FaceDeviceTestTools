package comm.myLibraryLoad.jvt.myStructure;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class SdkLogList extends Structure{
	public int iNumLog;
	public SdkLogItemStructure[] logs = new SdkLogItemStructure[128];

	public static class ByReference extends SdkLogList implements Structure.ByReference{};
	public static class ByValue extends SdkLogList implements Structure.ByValue{}
	
	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(new String[] { 
				"iNumLog", 
				"logs"});
	}
}
