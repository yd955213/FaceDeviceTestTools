package myGson.readSense;

public class DeviceBindGson {
	private int code = 0;
	private String message = "success";
	private ResultTid result;
	
	public static class ResultTid {
		private String tid ;

		public String getTid() {
			return tid;
		}

		public void setTid(String tid) {
			this.tid = tid;
		}
		
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultTid getResult() {
		return result;
	}

	public void setResult(ResultTid result) {
		this.result = result;
	}
}
