package com.chenxushao.gson;

import java.util.Date;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

public class GsonDemo2 {

	public static void main(String[] args) {
		JsonArray jsonArray = new JsonArray();
		GsonBuilder gb = new GsonBuilder();
		gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gb.setPrettyPrinting();
		gb.addSerializationExclusionStrategy(new MyExclusionStrategy(
				Person.class));

		Gson gson = gb.create();
		Person p = new Person();
		p.setName("cxs");
		p.setBirth(new Date());
		p.setId("0101");
		p.setPswd("root");
		p.setSex(true);

		String str = gson.toJson(p);
		System.out.println(str);
	}
}

class MyExclusionStrategy implements ExclusionStrategy {

	private Class classToExclude;

	public MyExclusionStrategy(Class classToExclude) {
		this.classToExclude = classToExclude;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		System.out.println("f: " + f.getName());
		if (f.getName().equals("birth")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
}
