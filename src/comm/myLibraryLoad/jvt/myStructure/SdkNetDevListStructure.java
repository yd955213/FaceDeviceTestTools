package comm.myLibraryLoad.jvt.myStructure;

import java.util.Arrays;
import java.util.List;
import com.sun.jna.Structure;

public class SdkNetDevListStructure extends Structure{
	public int netDevNum;
	public SdkConfigNetCommonStructure.ByReference sdkConfigNetCommon = new SdkConfigNetCommonStructure.ByReference();
	
	public static class ByReference extends SdkNetDevListStructure implements Structure.ByReference {}
    public static class ByValue extends SdkNetDevListStructure implements Structure.ByValue {}
    protected List<String> getFieldOrder() {
    	return Arrays.asList(new String[] { 
    			"netDevNum", 
    			"sdkConfigNetCommon"});
    }
}
