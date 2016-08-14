package com.chenxushao.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Demo3 {

	/** 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args      
	 * @return void
	 * @throws 
	 */

	public static void main(String[] args) {
		  /*JsonArray jsonArray = new JsonArray();
	             GsonBuilder gb = new GsonBuilder();
	             gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
	             gb.setPrettyPrinting();
	             
	             Gson gson = gb.create();
	             Person p  = new Person();
	             p.setName("cxs");
	             p.setBirth(new Date());
	             p.setId("0101");
	             p.setPswd("root");
	             p.setSex(true);
	             
	             Person p1  = new Person();
	             p1.setName("st");
	             p1.setBirth(new Date());
	             p1.setId("0101");
	             p1.setPswd("root");
	             p1.setSex(true);
	             
//	             p1.setSon(p);
	             p.setFather(p1);
	             
	             String str = gson.toJson(p);
	             System.out.println(str);*/
		
		
		
		JsonArray jsonArray = new JsonArray();
		 JsonObject jsonObject = new JsonObject();
		 for(int i=0;i<3;i++){
    	 jsonObject.addProperty("enum", 1);
    	 jsonObject.addProperty("name", 1);
    	 jsonArray.add(jsonObject);
		 }
    	
		 System.out.println(jsonArray);
	}

}

