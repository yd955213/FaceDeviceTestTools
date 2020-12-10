package myGson.das;

public class RemoteOpenDoorResponseGson {
	/**门禁通行代码（门禁返回的开门结果代码）*/
	private String AccessCode;
	/**开门结果（AccessCode对应的中文含义）*/
	private String Result;
	public String getAccessCode() {
		return AccessCode;
	}
	public void setAccessCode(String accessCode) {
		AccessCode = accessCode;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	
	
}
