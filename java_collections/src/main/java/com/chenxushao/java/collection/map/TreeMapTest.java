package com.chenxushao.java.collection.map;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

//按照键的自然顺序排序：键不可为null
public class TreeMapTest {

	public static void main(String[] args) {
		   Map<String,String> treeMap = new TreeMap<String,String>();
		  
		   treeMap.put("9", "pfei");
		   treeMap.put("3", "yyan");
		   treeMap.put("4", "wneng");
		   treeMap.put("5", "cuser");
		   treeMap.put("2", "null值");
		   treeMap.put("12", null);
	       treeMap.put("81","81");
		   
		   for ( String key : treeMap.keySet()){
			   System.out.println(treeMap.get(key));
		   }
		   System.out.println();
		   System.out.println(treeMap.containsKey("2"));
		   System.out.println(treeMap.containsValue("wneng"));
		   System.out.println("is empty? " + treeMap.isEmpty());
		   System.out.println("size: " + treeMap.size());
		   
		   
		   for ( String value : treeMap.values()){
			   System.out.println(value);
		   }
		   
		   Set set  = treeMap.entrySet();
		   System.out.println("\n\nset: " + set);
		   
		   System.out.println("\n");
		   
		   treeMap.put("1", "chenxushao");
		   for ( String key : treeMap.keySet()){
			   System.out.println(treeMap.get(key));
		   }
		   
	}

}
