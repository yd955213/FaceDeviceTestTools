package com.DES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONObject;

public class MySign {
	/*
	 * 签名采用MD5加密，加密密钥为开机获取的Secret(直接获得的Secret为3DES加密后的密文，记得解密)。
	加密内容：请求数据为json格式(key-value)，将json按照key的ASCLL码排序，Data为Json类型，内部键值对也需要排序，将排序后的json各项Value相加得到Values,
	计算签名SignA = MD5(Values+Secret)
	如果Sign=SignA，则验签通过
	 */
	/**
	 * 签名加密
	 * @param signStr json 字符串
	 * @param Secret 加密密钥为开机获取的Secret
	 * @return
	 */
	public static String getMySign (String jsonStr, String Secret) {
		jsonStr = new MySign().sortAsciiJson(jsonStr);
		jsonStr = MD5Util.md5(jsonStr + Secret);
		return jsonStr.toUpperCase();
	}
	
	private  ArrayList<String> sortAscii (JSONObject json) {
		ArrayList<String> nameList = getJsomToKeyList(json);
        //key排序，升序
        Collections.sort(nameList);
		return nameList;
	}
	
	private ArrayList<String> getJsomToKeyList (JSONObject json) {
		ArrayList<String> nameList = new ArrayList<>();
		Iterator<String> keys = json.keys();
		ArrayList<String> nameList2 =null;
        while(keys.hasNext()) {
            String key = keys.next().toString();
            if (key.equals("Data")) {
            	nameList2 = getJsomToKeyList (json.getJSONObject(key));
            	for (String key2 : nameList2) {
            		nameList.add(key2);
            	}
            }else {
            	nameList.add(key);
            }
            
        }
        return nameList;
	}
	
	/**
     * 按ASCII码给json对象排序（规定：升序）
     * @param json
     * @return
     */
	private String sortAsciiJson(String json){
        String res = "";
        MySign mySign = new MySign();
        JSONObject jsonObject = new JSONObject(json);
        ArrayList<String> nameList = mySign.sortAscii(jsonObject);
//        ArrayList<String> nameList2 = null;
//        JSONObject jsonObject2 = null;
        for (String key : nameList) {
        	if (jsonObject.has(key)) {
        		res += jsonObject.getString(key);
        	}else {
        		res += jsonObject.getJSONObject("Data").getString(key);
        	}
        }
        return res;
        
    }
}
