package myGson.das;
import com.google.gson.annotations.SerializedName;
import tools.SystemTimes;

public class ResponseGson<T> {
	@SerializedName(value = "Code",alternate = {"code"})
	protected String CodeEx = "0";
	@SerializedName(value = "Msg",alternate = {"msg"})
	protected String MsgEx = "OK";
	@SerializedName(value = "TimeStamp", alternate = {"timeStamp", "Timestamp", "timestamp"})
	protected String TimeStampEx = SystemTimes.getSysTime1();
	@SerializedName(value = "Data",alternate = {"data"})
	protected T DataEx;
	public String getCode() {
		return CodeEx;
	}
	public void setCode(String codeEx) {
		CodeEx = codeEx;
	}
	public String getMsg() {
		return MsgEx;
	}
	public void setMsg(String msgEx) {
		MsgEx = msgEx;
	}
	public String getTimeStamp() {
		return TimeStampEx;
	}
	public void setTimeStamp(String timeStampEx) {
		TimeStampEx = timeStampEx;
	}
	public T getData() {
		return DataEx;
	}
	public void setData(T dataEx) {
		DataEx = dataEx;
	}

	
}
