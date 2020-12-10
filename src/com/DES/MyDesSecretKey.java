package com.DES;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class MyDesSecretKey {

    //key必须在16位/24位/32位,下面使用的是24位,不足24位则后面余数补0填充满24位

	private final String ALGORITHM_CBC = "DESede/CBC/PKCS5Padding"; //"DESede/ecb/noPadding";PKCS5Padding
	private final String ALGORITHM_ECB = "DESede/ecb/noPadding"; //"DESede/ecb/noPadding";PKCS5Padding
	private final String keyAlgorithm = "DESede";
	private final String ENCODE_TYPE = "UTF-8";
//	private String ivs = "000000000";
	public  byte[] encrypt(String datasource, String key, String ivs) throws Exception {            

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgorithm);
        DESedeKeySpec desKey = new DESedeKeySpec(key.getBytes(ENCODE_TYPE));
        SecretKey securekey = keyFactory.generateSecret(desKey);
		
        IvParameterSpec zeroIv = new IvParameterSpec(ivs.getBytes(ENCODE_TYPE));
//    	SecretKey securekey = new SecretKeySpec(build3DesKey(key), keyAlgorithm); 
        Cipher cipher = Cipher.getInstance(ALGORITHM_CBC);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, zeroIv);
        return cipher.doFinal(datasource.getBytes(ENCODE_TYPE)); 
    }
	
    public byte[] decrypt(byte[] src, String key ,String ivs) throws Exception {
        
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgorithm);
        DESedeKeySpec desKey = new DESedeKeySpec(key.getBytes(ENCODE_TYPE));
        SecretKey securekey = keyFactory.generateSecret(desKey);
    	
        IvParameterSpec zeroIv = new IvParameterSpec(ivs.getBytes(ENCODE_TYPE));
//    	SecretKey securekey = new SecretKeySpec(build3DesKey(key), keyAlgorithm); 
        Cipher cipher = Cipher.getInstance(ALGORITHM_CBC);
        cipher.init(Cipher.DECRYPT_MODE, securekey, zeroIv);
        return cipher.doFinal(src);
    }
    
    public String getBase64Encrypet(byte[] bytes) {
    	String encoded = Base64.getEncoder().encodeToString(bytes);
    	return encoded;
    }
    
    public byte[] getBase64Decoded (String bytes) {
    	
    	byte[] decoded = null;
		try {
			decoded = Base64.getDecoder().decode(bytes.getBytes(ENCODE_TYPE));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return decoded;
    }
    
    public String getKeyfor8bit(String key) {
		int len;
		try {
			len = key.getBytes(ENCODE_TYPE).length;
			if(len%16!=0){
				byte[] hellotemp=new byte[len+(16-len%16)];
				for(int i=0;i<len;i++){
					hellotemp[i]=key.getBytes(ENCODE_TYPE)[i];
				}
				key=new String(hellotemp,ENCODE_TYPE);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return key;
    }
//  //构建3DES密钥
//    private byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
//        byte[] key = new byte[112];    //声明一个24位的字节数组，默认里面都是0
//        byte[] temp = keyStr.getBytes(ENCODE_TYPE);    //将字符串转成字节数组
//         /*
//          * 执行数组拷贝
//          * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
//          */
//        if(key.length > temp.length){
//            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
//            System.arraycopy(temp, 0, key, 0, temp.length);
//        }else{
//            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
//            System.arraycopy(temp, 0, key, 0, key.length);
//        }
//        return key;
//    }
 
    
    public byte[] decrypt_ecb(byte[] src, String key ) throws Exception {
        
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgorithm);
        DESedeKeySpec desKey = new DESedeKeySpec(key.getBytes(ENCODE_TYPE));
        SecretKey securekey = keyFactory.generateSecret(desKey);
    	
//        IvParameterSpec zeroIv = new IvParameterSpec(ivs.getBytes(ENCODE_TYPE));
//    	SecretKey securekey = new SecretKeySpec(build3DesKey(key), keyAlgorithm); 
        Cipher cipher = Cipher.getInstance(ALGORITHM_ECB);
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        return cipher.doFinal(src);
    }
    
    public String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

}
