package com.chenxushao.gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Demo {

	/** 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args      
	 * @return void
	 * @throws 
	 */

	public static void main(String[] args) {
		  JsonArray jsonArray = new JsonArray();
	        	 JsonObject jsonObject = new JsonObject();
	        	 String s = null;
	        	 jsonObject.addProperty("enum",s);
	        	 jsonObject.addProperty("name","male");
	        	 jsonArray.add(jsonObject);
	             System.out.println(jsonArray);
	             
	             GsonBuilder gb = new GsonBuilder();
	             
	}

}
