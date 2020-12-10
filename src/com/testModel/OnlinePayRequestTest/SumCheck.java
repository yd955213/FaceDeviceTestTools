package com.testModel.OnlinePayRequestTest;

public class SumCheck {
	/**
	 * ASCII累加和
	 * @param data
	 * @return
	 */
	public static String getSumCheck(String data) {
		char[] chars = data.toCharArray();
		int sum = 0;
		for(int i = 0 ; i < chars.length; i++) {
			sum += (int)chars[i];
		}
		data = Integer.toHexString(sum);
		if(data.length()>2) {
			data = data.substring(data.length()-2);
		}else if(data.length()<2) {
			data = "0" + data;
		}
		return data.toUpperCase();
	}
//	/**
//	 * 16进制累加和
//	 */
//	public static String getSumCheck(String data) {
//		String[] hex = MyString.splitString(data, 2);
//		int sum = 0;
//		for(int i = 0 ; i < hex.length ; i++) {
//			sum +=Long.parseLong(hex[i],16); 
//		}
//		data = MyString.getFullString(Integer.toHexString(sum),"0",4);
//		return data.toUpperCase();
//	}
	/**
	 * BCC 累加和
	 * @param data
	 * @return
	 */
	public static String getBCC(byte[] data) {

		String ret = "";
		byte BCC[]= new byte[1];
		for(int i=0;i<data.length;i++)
		{
		BCC[0]=(byte) (BCC[0] ^ data[i]);
		}
		String hex = Integer.toHexString(BCC[0] & 0xFF);
		if (hex.length() == 1) {
		hex = '0' + hex;
		}
		ret += hex.toUpperCase();
		return ret;
	}
}
