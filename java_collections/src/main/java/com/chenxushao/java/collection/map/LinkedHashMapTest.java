package com.chenxushao.java.collection.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


//按照插入的先后顺序排列
public class LinkedHashMapTest {

	public static void main(String[] args) {
		   Map<String,String> linkedHashMap = new LinkedHashMap<String,String>();

		   linkedHashMap.put("9", "pfei");
		   linkedHashMap.put("16", "yyan");
		   linkedHashMap.put("12", "wneng");
		   linkedHashMap.put("1", "cuser");
		   /*linkedHashMap.put(null, "null值");*/
		   linkedHashMap.put(null, null);


		   for ( String key : linkedHashMap.keySet()){
			   System.out.println(linkedHashMap.get(key));
		   }

		   System.out.println();
		   System.out.println(linkedHashMap.containsKey("2"));
		   System.out.println(linkedHashMap.containsValue("wneng"));
		   System.out.println("is empty? " + linkedHashMap.isEmpty());
		   System.out.println("size: " + linkedHashMap.size());


		   for ( String value : linkedHashMap.values()){
			   System.out.println(value);
		   }

		   Set set  = linkedHashMap.entrySet();
		   System.out.println("\n\nset: " + set);

		   System.out.println("\n");

		   linkedHashMap.put("1", "chenxushao");
		   for ( String key : linkedHashMap.keySet()){
			   System.out.println(linkedHashMap.get(key));
		   }

	}

}
