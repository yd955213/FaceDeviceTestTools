package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class SystemTimes {
	private static Date times = null;
	private static SimpleDateFormat  sdf= null;
	private static String result = null;
	
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 */
	public static String getSysTime () {
		times = new Date();
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.format(times);
	}
	/**
	 * yyMMddHHmmss
	 * @return
	 */
	public String getSysTimeYYMMDDHHMMSS () {
		times = new Date();
		sdf = new SimpleDateFormat("yyMMddHHmmss");
		return sdf.format(times);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getSysTime1 () {
		times = new Date();
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(times);
	}
	
	public String getSysTimeYYMMDD() {
		times = new Date();
		sdf = new SimpleDateFormat("yyMMdd");
		return sdf.format(times);
	}
	public static String getSysTimeYYYYMMDD() {
		times = new Date();
		sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(times);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss E
	 * @return
	 */
	public String getSysTimeFull () {
		times = new Date();
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		return sdf.format(times);
	}
	public String getStringWeek (String time) throws ParseException {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		times = sdf.parse(time);
		sdf = new SimpleDateFormat("E");
		Vector<String> vt = new Vector<>();
		vt.add("星期一");
		vt.add("星期二");
		vt.add("星期三");
		vt.add("星期四");
		vt.add("星期五");
		vt.add("星期六");
		vt.add("星期日");
		return "0"+vt.indexOf(sdf.format(times));
	}
	public String getweek () {
		times = new Date();
		sdf = new SimpleDateFormat("E");
		return sdf.format(times);
	}
	
	public String stringToTime(String st) throws Exception{
		String[] str = st.split(" ");
		String[] str1 = str[0].split("\\-");
		String[] str2 = str[1].split("\\:");
		Vector<String> vt = new Vector<>();
		vt.add("星期一");
		vt.add("星期二");
		vt.add("星期三");
		vt.add("星期四");
		vt.add("星期五");
		vt.add("星期六");
		vt.add("星期日");
		st=str1[0]+str1[1]+str1[2]+str2[0]+str2[1]+str2[2]+"0"+vt.indexOf(str[2]);
		return st;
	}
	
	/**
	 * XXXXXXXXXXXX -> 20XX-XX-XX XX:XX:XX
	 * @param st
	 * @return
	 */
	public String getStringTimeYY (String st) {
		
			result ="20"+st.substring(0, 2)+"-"+
					st.substring(2, 4)+"-"+
					st.substring(4, 6)+" "+
					st.substring(6, 8)+":"+
					st.substring(8,10)+":"+
					st.substring(10, 12);
		
		return result;
	}
}
