package com.chenxushao.common.json;


import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chenxushao.common.datetime.DateTimeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;


/******************************************************************************
 * 
 * <p>Description: Json处理工具类,基于Google Gson</p> 
 * Project: common
 * Package: cn.com.gome.hotel.utils
 *    File: GsonUtils.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年4月23日 上午11:30:34
 * 
 *****************************************************************************/
public final class GsonUtils {

	private GsonUtils() {
	}

	private static final Gson GSON = new GsonBuilder()/*.setPrettyPrinting()*/.create();

	private static final GsonBuilder GSONBUILDER = new GsonBuilder()/*.setPrettyPrinting()*/;

	private static final JsonParser jsonParser = new JsonParser();

	public static Gson getGson() {
		return GSON;
	}

	public static GsonBuilder getGsonbuilder() {
		return GSONBUILDER;
	}

	public static JsonParser getJsonparser() {
		return jsonParser;
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
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()/*.setPrettyPrinting()*/
					.create();
			return gson.toJson(obj);
		} else {
			return GSON.toJson(obj);
		}
	}

	/**
	 * @Title: json2Object
	 * @Description: json串转化为某对象
	 * @param jsonjson字符串，json必须为与Clazz实体格式匹配
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
		Gson gson = new GsonBuilder()/*.setPrettyPrinting()*/.registerTypeHierarchyAdapter(Date.class,
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

	public static JsonElement obj2JsonElement(Object obj) {
		return GSON.toJsonTree(obj);
	}

	/**
	 * @Title: json2ObjectDateSerializer
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param <T>
	 * @param json
	 * @param dateformat
	 * @param  clazz
	 * @return T
	 * @throws
	 */
	public static <T> T json2ObjectDateSerializer(String json,
			final String dateformat, Class<T> clazz) {
		Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class,
				new JsonDeserializer<Date>() {

					@Override
					public Date deserialize(JsonElement jsonelement, Type type,
							JsonDeserializationContext jsondeserializationcontext)
							throws JsonParseException {
						if ("\"\"".equals(jsonelement.toString())) {
							return null;
						}
						String dateStr = jsonelement.getAsString();
						return DateTimeUtils.parse(dateStr, dateformat);
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
		return GSON.fromJson(json, new TypeToken<Map<?, ?>>() {}.getType());
	}

	/**
	 * @Title: json2Array
	 * @Description: 将json字符串转换为数组对象
	 * @param json
	 * @return Object[]
	 * @throws JsonParseException
	 */
	public static Object[] json2Array(String json) throws JsonParseException {
		return GSON.fromJson(json, new TypeToken<Object[]>() {}.getType());
	}

	/** 
	* @Title: json2JsonElement 
	* @Description:json字符串转化为JsonElement对象
	* @param json
	* @return JsonElement
	* @throws 
	*/
	public static JsonElement json2JsonElement(String json) {
		return jsonParser.parse(json);
	}
}