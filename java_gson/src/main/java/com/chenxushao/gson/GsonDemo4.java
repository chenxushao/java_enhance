package com.chenxushao.gson;

import com.google.gson.Gson;

public class GsonDemo4 {

	public static void main(String[] args) {
		String json = "{　　\"name\":\"cxs\",    \"name\":\"zh\"}";
		Gson gson = new Gson();
		System.out.println(gson.fromJson(json, User.class).getName());
	}
}
