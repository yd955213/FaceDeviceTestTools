package myGson.das;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MyGson{
	
	public Gson getSerializeNullsGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();
		return gson;
	}
	
	/**
	 * 判断字符串是否为JSON格式
	 * @param jsonStr
	 * @return boolean
	 */
	@SuppressWarnings("deprecation")
	public boolean validateJson(String jsonStr) {
		JsonElement jsonElement;
		try {
            jsonElement = new JsonParser().parse(jsonStr);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        if (!jsonElement.isJsonObject()) {
            return false;
        }
		return true;
	}
}
