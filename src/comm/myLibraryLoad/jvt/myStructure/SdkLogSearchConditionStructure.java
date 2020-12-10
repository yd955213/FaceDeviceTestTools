package comm.myLibraryLoad.jvt.myStructure;

import java.util.Arrays;
import java.util.List;
import com.sun.jna.Structure;

public class SdkLogSearchConditionStructure extends Structure {
	public int nType;
	public int iLogPosition;
	public SdkTimeSection.ByReference stBeginTime = new SdkTimeSection.ByReference();
	public SdkTimeSection.ByReference stEndTime = new SdkTimeSection.ByReference();
	
	public static class ByReference extends SdkLogSearchConditionStructure implements Structure.ByReference {}
	public static class ByValue extends SdkLogSearchConditionStructure implements Structure.ByValue {}
	
	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(new String[] { 
				"nType", 
				"iLogPosition", 
				"stBeginTime", 
				"stEndTime"});
	}
}
