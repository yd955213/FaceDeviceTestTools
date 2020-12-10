package comm.myLibraryLoad.jvt.myStructure;

import java.util.Arrays;
import java.util.List;
import com.sun.jna.Structure;

public class SdkTimeSection extends Structure{
	public int year;///< 年。  
	public int month;///< 月，January = 1, February = 2, and so on.   
	public int day;///< 日。  
	public int wday;///< 星期，Sunday = 0, Monday = 1, and so on   
	public int hour;///< 时。  
	public int minute;///< 分。  
	public int second;///< 秒。  
	public int isdst;///< 夏令时标识。  
	
	public static class ByReference extends SdkTimeSection implements Structure.ByReference {}
	public static class ByValue extends SdkTimeSection implements Structure.ByValue {}
	
	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(new String[] { 
				"year", 
				"month", 
				"day", 
				"wday", 
				"hour", 
				"minute", 
				"second", 
				"isdst"});
	}
	/**
	 * yyyy-MM-dd hh:mm:ss
	 */
	
	public String toData(){
		StringBuffer time = new StringBuffer();
		time.append(Integer.toString(year) + "-");
		time.append(Integer.toString(month) + "-"); 
		time.append(Integer.toString(day) + " ");
		time.append(Integer.toString(hour) + ":");
		time.append(Integer.toString(minute) + ":");
		time.append(Integer.toString(second));
		return time.toString();
	}
}
