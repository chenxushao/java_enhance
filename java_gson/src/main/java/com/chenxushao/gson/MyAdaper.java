package com.chenxushao.gson;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MyAdaper<T> implements JsonSerializer<T> {

	public MyAdaper(List<String> excludeField, List<Class> excludeClass) {
		this.excludeField = excludeField;
	}

	private List<String> excludeField = Collections.EMPTY_LIST;
	private List<Class> excludeClass = Collections.EMPTY_LIST;

	@Override
	public JsonElement serialize(T src, Type typeOfSrc,
			JsonSerializationContext context) {
		Gson gson = new GsonBuilder().setExclusionStrategies(
				new ExclusionStrategy() {
					@Override
					public boolean shouldSkipField(FieldAttributes f) {
						if (excludeField.contains(f.getName())) {
							return true;
						}
						return false;
					}

					@Override
					public boolean shouldSkipClass(Class<?> clazz) {
						if (excludeClass.contains(clazz)) {
							return true;
						}
						return false;
					}
				}).create();

		return gson.toJsonTree(src);
	}
}