package com.chenxushao.java.collection.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		
		
		System.out.println(map.containsKey("1"));
		System.out.println(map.containsKey(new String("1")));
	}

}
