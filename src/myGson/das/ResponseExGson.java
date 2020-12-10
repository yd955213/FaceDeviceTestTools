package myGson.das;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 扩展接口，
 * @author yangdang
 *
 */
public class ResponseExGson<T> extends ResponseGson<T>{
	@Expose(deserialize = false, serialize = true)
	private String Err;
	@Expose(deserialize = false, serialize = true)
	private String Url;
	@SerializedName(value = "DeviceUniqueCode", alternate = {"deviceUniqueCode", "deviceuniquecode", "device_unique_code"})
	private String DeviceUniqueCode;
	public String getErr() {
		return Err;
	}
	public void setErr(String err) {
		Err = err;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getDeviceUniqueCode() {
		return DeviceUniqueCode;
	}
	public void setDeviceUniqueCode(String deviceUniqueCode) {
		DeviceUniqueCode = deviceUniqueCode;
	}
	
}
