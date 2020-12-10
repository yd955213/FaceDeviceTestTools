package myGson;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import tools.MyString;
/**
 * 便于后期扩展
 * 序列化时，将Integer、Long、Float、Double转换为字符串
 * 返序列时，将字符串转换为对应的对象类型；
 * float和double只能用来做科学计算或者是工程计算，在商业计算中我们要用 java.math.BigDecimal
 * 当json字符串数据为太长时，如：99999.1234567899923 会出现进度丢失问题，需要使用BigDecimal
 * @author yangdang
 *
 */
public class NumberJsonSerializerString {
	private final int INTEGER_DEFAULT = -1;
	private final short SHORT_DEFAULT = -1;
	private final long LONG_DEFAULT = -1L;
	private final double DOUBLE_DEFAULT = -1D;
	private final float FLOAT_DEFAULT = -1F;
	private final String UNDEFINED_DEFAULT = "未定义默认值";
	public Gson create() {
		Gson gson = null;
		/*
		 * 序列化时将Number类型转化为字符串
		 */
		JsonSerializer<Number> numberJsonSerializer = new JsonSerializer<Number>() {
			
			@Override
			public JsonElement serialize(Number arg0, Type arg1, JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				return new JsonPrimitive(String.valueOf(arg0));
			}
		};
		
		if (null == gson) {
			gson = new GsonBuilder()
//					.serializeNulls()
					.registerTypeHierarchyAdapter(Number.class, numberJsonSerializer) 
					.registerTypeAdapter(Integer.class, new NumberJsonIsNull<Integer>())
					.registerTypeAdapter(int.class, new NumberJsonIsNull<Integer>())
					.registerTypeAdapter(Long.class, new NumberJsonIsNull<Long>())
					.registerTypeAdapter(long.class, new NumberJsonIsNull<Long>())
					.registerTypeAdapter(Short.class, new NumberJsonIsNull<Short>())
					.registerTypeAdapter(short.class, new NumberJsonIsNull<Short>())
					.registerTypeAdapter(Double.class, new NumberJsonIsNull<Double>())
					.registerTypeAdapter(double.class, new NumberJsonIsNull<Double>())
					.create();
		}
		
		return gson;
	}
	public Gson createSerializeNulls() {
		Gson gson = null;
		/*
		 * 序列化时将Number类型转化为字符串
		 */
		JsonSerializer<Number> numberJsonSerializer = new JsonSerializer<Number>() {
			
			@Override
			public JsonElement serialize(Number arg0, Type arg1, JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				return new JsonPrimitive(String.valueOf(arg0));
			}
		};
		
		if (null == gson) {
			gson = new GsonBuilder()
					.serializeNulls()
					.registerTypeHierarchyAdapter(Number.class, numberJsonSerializer) 
					.registerTypeAdapter(Integer.class, new NumberJsonIsNull<Integer>())
					.registerTypeAdapter(int.class, new NumberJsonIsNull<Integer>())
					.registerTypeAdapter(Long.class, new NumberJsonIsNull<Long>())
					.registerTypeAdapter(long.class, new NumberJsonIsNull<Long>())
					.registerTypeAdapter(Short.class, new NumberJsonIsNull<Short>())
					.registerTypeAdapter(short.class, new NumberJsonIsNull<Short>())
					.registerTypeAdapter(Double.class, new NumberJsonIsNull<Double>())
					.registerTypeAdapter(double.class, new NumberJsonIsNull<Double>())
					.create();
		}
		
		return gson;
	}
	/*
	 * 返序列化时，返回Type类型对应类的成员变量的类型值
	 * 当json键值为null,"",时，返回自定义默认值
	 */
	private class NumberJsonIsNull<T> implements JsonDeserializer<T>{
			@SuppressWarnings("unchecked")
			@Override
		public T deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
			// TODO Auto-generated method stub
//				System.out.println(arg1);
			return (T) getData(json, arg1, arg1.getClass());
		}
	}
	@SuppressWarnings("unchecked")
	private <T> T getData(JsonElement json, Type arg1, Class<T> clazz) throws NumberFormatException{
		T result = null;
		MyString myString = new MyString();
		try {
			switch (arg1.getTypeName()) {
			
			case "java.lang.Integer":
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Integer.valueOf(json.getAsInt());
				}else {
					result = clazz.cast(INTEGER_DEFAULT);
				}
				
				break;
			case "int":
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Integer.valueOf(json.getAsInt());
				}else {
					result = clazz.cast(INTEGER_DEFAULT);
				}
				
				break;
			case "java.lang.Long":
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Long.valueOf(json.getAsLong());
				}else {
					result = clazz.cast(LONG_DEFAULT);
				}
				
				break;
			case "long":
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Long.valueOf(json.getAsLong());
				}else {
					result = clazz.cast(LONG_DEFAULT);
				}
				
				break;
			case "java.lang.Short":
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Short.valueOf(json.getAsShort());
				}else {
					result = clazz.cast(SHORT_DEFAULT);
				}
				
				break;
			case "short":
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Short.valueOf(json.getAsShort());
				}else {
					result = clazz.cast(SHORT_DEFAULT);
				}
				
				break;
			case "java.lang.Double":
				System.out.println("String = " + json.getAsString());
				System.out.println(Double.parseDouble(json.getAsString()));
				
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Double.valueOf(Double.parseDouble(json.getAsString()));
//							(T) Double.valueOf(json.getAsDouble());
				}else {
					result = clazz.cast(DOUBLE_DEFAULT);
				}
				
				break;
			case "double":
				System.out.println("double = " + json.getAsDouble());
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Double.valueOf(json.getAsDouble());
				}else {
					result = clazz.cast(DOUBLE_DEFAULT);
				}
				
				break;
			case "java.lang.Float":
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Float.valueOf(json.getAsFloat());
				}else {
					result = clazz.cast(FLOAT_DEFAULT);
				}
				
				break;
			case "float":
				if (myString.isNotNullString(json.getAsString())) {
					result = (T) Float.valueOf(json.getAsFloat());
				}else {
					result = clazz.cast(FLOAT_DEFAULT);
				}
				
				break;
			default:
				result = clazz.cast(UNDEFINED_DEFAULT);
				break;
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new NumberFormatException(e.getClass().getTypeName() + " " + e.getMessage());
		}
		return result;
	}
}


/*
 * 	JsonDeserializer<Integer> integerJsonElementIsNull = new JsonDeserializer<Integer>() {
			@Override
			public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				try {
					System.out.println("typeOfT = " + typeOfT + "  " + typeOfT.getClass().getSimpleName());
					if (json.getAsString().equals("") || json.getAsString() == null) {
						return -1;
					}else {
						return json.getAsInt();
					}
				} catch (NumberFormatException e) {
					return -1;
				}
			}
		};
		JsonDeserializer<Double> doubleJsonElementIsNull = new JsonDeserializer<Double>() {
			@Override
			public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				try {
					if (json.getAsString().equals("") || json.getAsString() == null) {
						return -1D;
					}else {
						return json.getAsDouble();
					}
				} catch (NumberFormatException e) {
					return -1D;
				}
			}
		};
		JsonDeserializer<Long> longJsonElementIsNull = new JsonDeserializer<Long>() {
			@Override
			public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				try {
					if (json.getAsString().equals("") || json.getAsString() == null) {
						return -1L;
					}else {
						return json.getAsLong();
					}
				} catch (NumberFormatException e) {
					return -1L;
				}
			}
		};
		JsonDeserializer<Short> shortJsonElementIsNull = new JsonDeserializer<Short>() {
			@Override
			public Short deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				try {
					if (json.getAsString().equals("") || json.getAsString() == null) {
						return -1;
					}else {
						return json.getAsShort();
					}
				} catch (NumberFormatException e) {
					return -1;
				}
			}
		};
	*/
