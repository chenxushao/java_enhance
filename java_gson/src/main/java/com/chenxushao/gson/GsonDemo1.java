package com.chenxushao.gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonDemo1 {

	public static void main(String[] args) {
		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		String s = null;
		jsonObject.addProperty("enum", s);
		jsonObject.addProperty("name", "male");
		jsonArray.add(jsonObject);
		System.out.println(jsonArray);
		GsonBuilder gb = new GsonBuilder();
	}
}
