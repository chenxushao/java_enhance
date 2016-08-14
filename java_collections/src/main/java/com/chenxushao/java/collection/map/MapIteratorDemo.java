package com.chenxushao.java.collection.map;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapIteratorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Map<String,String> map = new TreeMap<String, String>();
		 map.put("2", "b");
		 map.put("1", "a");
		 map.put("4", "d");
		 map.put("3", "c");
		 map.put("5", "e");
		 
		 //map的遍历　
		 Iterator<Map.Entry<String, String>> its = map.entrySet().iterator();
		 for(;its.hasNext();){
			 Map.Entry<String, String> entry = its.next();
			 System.out.println(entry.getKey() + "--->" + entry.getValue());
		 }
	}

}
