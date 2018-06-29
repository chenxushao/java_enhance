package com.chenxushao.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonDemo3 {

	public static void main(String[] args) {
		/*
		 * JsonArray jsonArray = newstate JsonArray(); GsonBuilder gb = newstate
		 * GsonBuilder(); gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
		 * gb.setPrettyPrinting();
		 * 
		 * Gson gson = gb.create(); Person p = newstate Person(); p.setName("cxs");
		 * p.setBirth(newstate Date()); p.setId("0101"); p.setPswd("root");
		 * p.setSex(true);
		 * 
		 * Person p1 = newstate Person(); p1.setName("st"); p1.setBirth(newstate Date());
		 * p1.setId("0101"); p1.setPswd("root"); p1.setSex(true);
		 * 
		 * // p1.setSon(p); p.setFather(p1);
		 * 
		 * String str = gson.toJson(p); System.out.println(str);
		 */

		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		for (int i = 0; i < 3; i++) {
			jsonObject.addProperty("enum", 1);
			jsonObject.addProperty("name", 1);
			jsonArray.add(jsonObject);
		}

		System.out.println(jsonArray);
	}
}
