package myGson.das.consume;

public class GetSecretRecieveGson {
	/**
	 * 密钥(3DES加密后的密文)
	 */
	private String Secret;
	/**
	 * 3DES加密向量
	 */
	private String DesIV;
	public String getSecret() {
		return Secret;
	}
	public void setSecret(String secret) {
		Secret = secret;
	}
	public String getDesIV() {
		return DesIV;
	}
	public void setDesIV(String desIV) {
		DesIV = desIV;
	}
	
	
}
