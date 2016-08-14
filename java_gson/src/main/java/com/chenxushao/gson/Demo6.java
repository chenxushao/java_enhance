package com.chenxushao.gson;

import com.google.gson.Gson;

public class Demo6 {

	/** 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args      
	 * @return void
	 * @throws 
	 */

	public static void main(String[] args) {
		
	   
		String json = "{　　\"name\":\"cxs\",    \"name\":\"zh\"}";
	   Gson gson = new Gson();
	   System.out.println( gson.fromJson(json, User.class).getName());
	             
	}

}
