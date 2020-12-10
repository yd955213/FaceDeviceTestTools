package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import myGson.das.DevInfo;
import view.MainIntfaceView;



public class MyString {
	
	private String result = "";
	
	/**
	 * 遍历list 删除list元素
	 * @param list 
	 * @param element 需要删除的元素
	 * @return
	 */
	public List<?> removeListElement(List<?> list,Object element){
		
		Iterator<?> iterator = list.iterator();
		Object temp = null;
		while(iterator.hasNext()) {
			temp = iterator.next();
			if (null == element ) {
				if (null == temp) {
					iterator.remove();
				}
			}else {
				switch (element.getClass().getSimpleName()) {
				case "String":
					if (element.equals(temp)) {
						iterator.remove();
					}
					break;
				case "Integer":
					if (element == temp) {
						iterator.remove();
					}
					break;
				default:
					break;
				}
			}
			
			
		}
		
		return list;
		
	}
	/**
	 * 遍历获取MAP的值 返回key
	 * @param map
	 * @return List<String>
	 */
	public static List<String> getMapKeyToList(Map<String, DevInfo> map) {
		List<String> keyList = new ArrayList<String>();
		Iterator<Entry<String, DevInfo>> iterator = map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			keyList.add(iterator.next().getKey());
		}
		
		return keyList;
		
	}
	/**
	 * 遍历获取Map<String, DevInfo>的值 返回 value
	 * @param map
	 * @return List<DevInfo>
	 */
	public List<DevInfo> getMapValueToList(Map<String, DevInfo> map) {
		List<DevInfo> devInfoList = new ArrayList<DevInfo>();
		Iterator<Entry<String, DevInfo>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			devInfoList.add(iterator.next().getValue());
		}
		
		return devInfoList;
		
	}
	/**
	 * 判断是否为数字字符串
	 * @param str
	 * @return
	 */
	public boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[\\d]+$");  
		return pattern.matcher(str).matches();  
	}
	
	public String getHexString(String st) throws Exception {
		if(isInteger(st)) {
			st = Integer.toHexString(Integer.parseInt(st));
		}else {
			st = null;
		}
		return st;
	}
	/**
	 * 判断是否为16进制字符串
	 * @param str
	 * @return
	 */
	public boolean isHexString(String str) {
		Pattern pattern = Pattern.compile("^[A-Fa-f0-9]+$");  
		return pattern.matcher(str).matches(); 
	} 
	
	/**
	 * 判断是否为空vector
	 * @param vt
	 * @return
	 */
	public boolean isNotNullVector (List<Vector<Object>> vt) {
		boolean isNull = true;
		if(vt == null || vt.size() < 1) {
			isNull = false;
		}
		return isNull;
	}
	/**
	 * 判断是否为空vector
	 * @param vt
	 * @return
	 */
	public boolean isNotNullString (String st) {
		boolean isNull = true;
		if(null == st|| st.length() < 1) {
			isNull = false;
		}
		return isNull;
	}
	
	public boolean isNotNullList(List<Vector<String>> list) {
		boolean isNull = false;
		if(list == null || list.size() <1 ) {
			isNull = true;
		}
		return isNull;
	}
	/**
	 * 将IP地址转换为16镜子字符串类型
	 * 255.255.255.0 -> FFFFFF00
	 * @param ipStr
	 * @return
	 */
	public String getIPToHexString (String ipStr) {
		try {
			result = "";
			String[] str= ipStr.split("\\.");
			for(int i = 0 ;i < str.length; i++) {
				if(Integer.parseInt(str[i]) < 16) {
					result += "0"+Integer.toHexString(Integer.parseInt(str[i]));
				}else {
					result += Integer.toHexString(Integer.parseInt(str[i]));
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		System.out.println(result);
		return result;
	}
	/**
	 * 获取限定长度字符串，小于前面补零，大于取后面字符串
	 * @param str
	 * @param length
	 * @return
	 */
	public String getLimitLengthString(String str, int length) {
		
		if (null == str) {
			str = "";
		}
		int systemLethgth = str.length();
		if(systemLethgth < length) {
			str = getFullString(str, "0", length);
		}else if(systemLethgth > length){
			str = str.substring(systemLethgth - length, systemLethgth);
		}
		return str;
	}
	
	/** 
	 * 将byte[]转化为字符串
	 * @param arrB
	 * @return
	 * @throws Exception
	 */
	public String getByteArr2HexStr(byte[] arrB) throws Exception {  
	    int iLen = arrB.length; 
	   
	    StringBuffer sb = new StringBuffer(iLen * 2);  
	    for (int i = 0; i < iLen; i++) {
	//    	 
	        int intTmp = arrB[i];  
	        while (intTmp < 0) {  
	            intTmp = intTmp + 256;  
	        }  
//	        System.out.println("arrB["+i+"]"+intTmp);
	        if (intTmp < 16) {  
	            sb.append("0");  
	        }  
	        sb.append(Integer.toString(intTmp, 16));  
	    }  
	    return sb.toString().toUpperCase().trim().replaceAll(" ", "");  
	}  
	/**
	 * 将字符串按照2个长度转换为byte[] 
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public byte[] getHexStr2ByteArr(String strIn) throws Exception {  
	    byte[] arrB = strIn.getBytes("utf-8");  
	    int iLen = arrB.length;  
	    byte[] arrOut = new byte[iLen / 2];  
	    for (int i = 0; i < iLen; i = i + 2) {  
	        String strTmp = new String(arrB, i, 2);  
	        arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);  
	    }  
	    return arrOut;  
	}  
	/**
	 * byte[]转换为ASCII字符串
	 * @param bytes
	 * @return
	 */
	public String getByteToAscii (byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length* 2); 
		for(int i = 0 ; i < bytes.length ;i ++) {
			sb.append((char)bytes[i]);
		}
		return sb.toString().trim().replaceAll(" ", "");
	}
	
	/**
	 * byte[]转换为数字
	 * @param bArr
	 * @return
	 */
	public int getByteArrayToInt2(byte[] bArr) {    
	    if(bArr.length!=4){    
	        return -1;    
	    }    
	    return (int) ((((bArr[0] & 0xff) << 24)      
	               | ((bArr[1] & 0xff) << 16)      
	               | ((bArr[2] & 0xff) << 8)  
	               | ((bArr[3] & 0xff) << 0)));     
	} 
	
	public String getHexStringToIP(String ipstr) {
		result = "";
		String[] str = new String[4];
		for( int i = 0 ;i < str.length ; i++) {
			str[i] = ipstr.substring(2*i, 2*(i+1));
			result+=Integer.parseInt(str[i], 16)+".";
		}
		return result.substring(0,result.length()-1);
	}
	
	
	/**
	 * 字符串安装splitLength的长度拆分为字符串数组
	 * @param data
	 * @param splitLength
	 * @return
	 */
	public String[] splitString (String data , int splitLength) {
		String[] splitStr = new String[data.length()/splitLength];
		for (int i = 0 ;i < splitStr.length;i++) {
			splitStr[i] = data.substring(splitLength*i, splitLength* (i+1));
		}
		return splitStr;
	}
		
	public String getBytesToHexString(byte[] src,int start ,int end){   
	    StringBuilder stringBuilder = new StringBuilder("");   
	    if (src == null || src.length <= 0) {   
	        return null;   
	    }   
	    for (int i = start; i < end; i++) {   
	        int v = src[i] & 0xFF;   
	        String hv = Integer.toHexString(v);   
	        if (hv.length() < 2) {   
	            stringBuilder.append(0);   
	        }   
	        stringBuilder.append(hv);   
	    }   
	    return stringBuilder.toString().toUpperCase();   
	}   
	public String getBytesToASCII(byte[] bytes,int start ,int end){   
	    StringBuilder stringBuilder = new StringBuilder("");   
	    if (bytes == null || bytes.length <= 0) {   
	        return null;   
	    }   
	    for (int i = start; i < end; i++) {
	        stringBuilder.append((char)bytes[i]);   
	    }   
	    return stringBuilder.toString().toUpperCase();   
	} 
	
//	public byte[] getASCIIToByte(String data){   
////		byte[] bytes = data.getBytes();
//	    return data.getBytes();   
//	} 
	
	public byte[] hexStringToBytes(String hexString) {   
	    if (hexString == null || hexString.equals("")) {   
	        return null;   
	    }   
	    hexString = hexString.toUpperCase();   
	    int length = hexString.length() / 2;   
	    char[] hexChars = hexString.toCharArray();   
	    byte[] d = new byte[length];   
	    for (int i = 0; i < length; i++) {   
	        int pos = i * 2;   
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
	    }   
	    return d;   
	}
	//charתbety
	private byte charToByte(char c) {   
	    return (byte) "0123456789ABCDEF".indexOf(c);   
	}  
	/**
	 * 前面补零
	 * @param data
	 * @param fullStr
	 * @param length
	 * @return
	 */
	public static String getFullString(String data , String fullStr,int length) {
		if(data.length() > length) {
			data = data.substring(data.length()-length);
//			JOptionPane.showMessageDialog(null, "�������ֹ�����"+data+",��׼���ȣ�"+length, "�������", JOptionPane.ERROR_MESSAGE);
		}else {
			for(int i = data.length() ; i < length ; i++) {
				data = fullStr +data;
			}
		}
		return data;
	}
	/**
	 * 后面补零
	 * @param data
	 * @param fullStr 补齐字符串
	 * @param length 字符串目标长度
	 * @return
	 */
	public static String getFullString_l(String data , String fullStr,int length) {
		
		for(int i = data.length() ; i < length ; i++) {
			data += fullStr ;
		}
		return data;
	}
	
	/**
	 * 字符串进行高低位互换
	 * @param st
	 * @return
	 */
	public static String getHigthToLow(String st) {
		List<String> list = new ArrayList<>();
		if(st.length()%2 != 0) {
			st = "0"+st;
		}
		for(int i = 0 ; i< st.length()/2;i++) {
			list.add(st.substring(2*i, 2*(i+1)));
		}
		Collections.reverse(list);
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < list.size();i++) {
			sb.append(list.get(i));
		}
		return sb.toString();
	}
	
	/**
	 * 将BCD时间类型的字符串转换为
	 * @param timesStr
	 * @return
	 */
	public String getTimeBCDStringToStr (String timesStr) {
		String [] st = null;
		if(timesStr.indexOf(":") != -1) {
			st = timesStr.split(":");
			timesStr = st[0] +st[1];
		}else if( timesStr.indexOf("-") != -1  ) {
			st = timesStr.split("-");
			timesStr = st[0] +st[1];
		}
		
		return timesStr;
	}
	
	//���ַ���ת��Ϊʱ���ַ�����3231 -> 32:31
	public String getStringToTimesBCDStr (String timesStr) {
		String st = "";
		for( int i = 0 ; i <timesStr.length()/2 ; i++) {
			st += timesStr.substring(2*i, 2*(i+1)) +":";
		}
		return st;
	}
	
	//���ַ���ת��Ϊʱ���ַ�����2014-5-6 -> 140506
	public String getHolidayBCDToStr (String timesStr) {
		String [] st = null;
		if(timesStr.indexOf("-") != -1) {
			st = timesStr.split(":");
		}
		if(st[0].length() > 2) {
			st[0] = st[0].substring(st[0].length() -2, st[0].length());
		}
		timesStr = st[0] + getFullString(st[1], "0", 2) + getFullString(st[2], "0", 2);
		return timesStr;
	}
	
	//���ַ���ת��Ϊʱ���ַ�����150506-> 2014-05-06 
	public String getStringToHolidayBCD (String timesStr) {
		String st = "20";
		for( int i = 0 ; i <timesStr.length()/2 ; i++) {
			st += timesStr.substring(2*i, 2*(i+1)) +"-";
		}
		return st.substring(0, st.length()-1);
	}
	
	public boolean isFigure(String data,int length) {
		String str = "^\\d{"+length+"}$";
		Pattern pattern = Pattern.compile(str);
		Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}
	
	//�ж��Ƿ�Ϊ�����֣�lengthΪ����
	public boolean isFigure(String data) {
		String str = "\\d*";
		Pattern pattern = Pattern.compile(str);
		Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}
	
	public String complementStrLength(String data,int length) {
		if(data == null) {
			data = "";
		}
		if(data.length() < length) {
			for(int i = data.length() ; i < length;i++) {
				data = "0" +data;
			}
		}else {
			data = data.substring(data.length()-length, data.length());
		}
		return data;
	}
	
	public static boolean isJsonStr(String jsonString) {
		boolean isJson = false;
		if (null != jsonString && jsonString.length() > 0) {
//			jsonString = jsonString.trim().replaceAll(" ", "");
			jsonString = jsonString.trim();
			if (jsonString.startsWith("{") && jsonString.endsWith("}")) {
				isJson = true;
			} else if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
				isJson = true;
			}
		}else if("{}".equals(jsonString)|| "\"\"".equals(jsonString)){
			isJson = false;
		}
		if (!isJson) {
			MainIntfaceView.writeLogsTextArea(null, "[返回数据格式错误，非Json字符串]："+jsonString);
		}
		return isJson;
	}
}
