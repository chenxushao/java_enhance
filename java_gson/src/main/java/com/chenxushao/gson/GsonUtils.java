package com.chenxushao.gson;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

/**
 * @ClassName: GsonUtils
 * @Description: Json处理工具类
 * @author chenxushao@gmail.com
 * @date 2013-9-22 上午10:21:31
 */
public class GsonUtils {
	private GsonUtils() {
	}

	private static final Gson GSON;
	private static final Gson GSON_SUPPORT_ANNOTATION;// 支持字段注解
	private static final JsonParser  jsonParser= new JsonParser();
	static {
		GSON = new GsonBuilder().setPrettyPrinting().create();
		GSON_SUPPORT_ANNOTATION = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
				.create();
	}

	/**
	 * @Title: object2Json
	 * @Description: 对象转化为json串
	 * @param obj
	 * @return String
	 */
	public static String object2Json(Object obj) {
		return object2Json(obj, false);
	}

	public static String object2Json(Object obj, Boolean supportAnnotation) {
		if (Boolean.TRUE.equals(supportAnnotation)) {
			return GSON_SUPPORT_ANNOTATION.toJson(obj);
		} else {
			return GSON.toJson(obj);
		}
	}

	/**
	 * @Title: json2Object
	 * @Description: json串转化为某对象
	 * @param jsonjson字符串
	 *            ，json必须为与Clazz实体格式匹配
	 * @param clazz
	 * @return T
	 * @throws JsonParseException
	 */
	public static <T> T json2Object(String json, Class<T> clazz)
			throws JsonParseException {
		return GSON.fromJson(json, clazz);
	}

	
	/**
	 * @Title: json2Object
	 * @Description: JsonObject转化为对象
	 * @param <T>
	 * @param jo
	 * @param clazz
	 * @return T
	 * @throws JsonParseException
	 */
	public static <T> T json2Object(JsonObject jo, Class<T> clazz)
			throws JsonParseException {
		return GSON.fromJson(jo, clazz);
	}
 
	/**
	 * @Title: json2List
	 * @Description:JsonArray转化为List
	 * @param ja
	 * @param type 类型    如：  Type type = new TypeToken<List<HelloWorld>>() { }.getType()
	 * @return List<T>
	 * @throws JsonParseException
	 */
	public static <T> List<T> json2List(JsonArray ja, Type type)
			throws JsonParseException {
		return GSON.fromJson(ja, type);
	}

	/** 
	* @Title: json2List 
	* @Description: json转化为List
	* @param json json必须为一个数组
	* @param type 类型    如：  Type type = new TypeToken<List<HelloWorld>>() { }.getType()
	* @return List<T>
	* @throws 
	*/
	public static <T> List<T> json2List(String json, Type type) {
		return GSON.fromJson(json, type);
	}

	/**
	 * @Title: object2JsonDateSerializer
	 * @Description: 将对象转换成json格式(并自定义日期格式)
	 * @param ts
	 * @param dateformat
	 * @return String
	 * @throws
	 */
	public static String object2JsonDateSerializer(Object obj,
			final String dateformat) {
		String jsonStr = null;
		Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class,
				new JsonSerializer<Date>() {
					public JsonElement serialize(Date src, Type typeOfSrc,
							JsonSerializationContext context) {
						SimpleDateFormat format = new SimpleDateFormat(
								dateformat);
						return new JsonPrimitive(format.format(src));
					}
				}).setDateFormat(dateformat).create();
		if (gson != null) {
			jsonStr = gson.toJson(obj);
		}
		return jsonStr;
	}

	/**
	 * @Title: json2ObjectDateSerializer
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param <T>
	 * @param @param json
	 * @param @param dateformat
	 * @param @param clazz
	 * @param @return
	 * @return T
	 * @throws
	 */
	public static <T> T json2ObjectDateSerializer(String json,
			final String dateformat, Class<T> clazz) {
		Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class,
				new JsonSerializer<Date>() {
					public JsonElement serialize(Date src, Type typeOfSrc,
							JsonSerializationContext context) {
						SimpleDateFormat format = new SimpleDateFormat(
								dateformat);
						return new JsonPrimitive(format.format(src));
					}
				}).setDateFormat(dateformat).create();
		return gson.fromJson(json, clazz);
	}

	/**
	 * @Title: json2Map
	 * @Description: 将json字符串转换为Map
	 * @param json
	 * @return Map<?,?>
	 * @throws JsonParseException
	 */
	public static Map<?, ?> json2Map(String json) throws JsonParseException {
		return GSON.fromJson(json, new TypeToken<Map<?, ?>>() {
		}.getType());
	}

	/**
	 * @Title: json2Array
	 * @Description: 将json字符串转换为数组对象
	 * @param json
	 * @return Object[]
	 * @throws JsonParseException
	 */
	public static Object[] json2Array(String json) throws JsonParseException {
		return GSON.fromJson(json, new TypeToken<Object[]>() {
		}.getType());
	}
	
	
	/** 
	* @Title: json2JsonElement 
	* @Description:json字符串转化为JsonElement对象
	* @param json
	* @return JsonElement
	* @throws 
	*/
	public JsonElement json2JsonElement(String json){
		return jsonParser.parse(json);
	}
	
	 
	/**
	 * GsonBuilde使用示例 Gson gson = new GsonBuilder()
	 * .registerTypeAdapter(Id.class, new IdTypeAdapter())
	 * .enableComplexMapKeySerialization() .serializeNulls()
	 * .setDateFormat(DateFormat.LONG)
	 * .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
	 * .setPrettyPrinting() .setVersion(1.0) .create();
	 */
}