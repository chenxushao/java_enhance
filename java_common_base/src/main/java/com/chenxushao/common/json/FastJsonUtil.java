package com.chenxushao.common.json;

import com.alibaba.fastjson.JSON;

public class FastJsonUtil {

	private final static String NULLSTR = "";

	public static String toJson(Object obj) {
		if (obj == null) {
			return NULLSTR;
		}
		return JSON.toJSONString(obj);
	}

	public static <T> T toObj(String jsonValue, Class<T> c) {
		if (jsonValue == null || jsonValue.length() == 0) {
			return null;
		}
		return JSON.parseObject(jsonValue, c);
	}
}
