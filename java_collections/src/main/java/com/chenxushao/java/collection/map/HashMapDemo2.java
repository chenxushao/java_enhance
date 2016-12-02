package com.chenxushao.java.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//按照Hash表结构排列
public class HashMapDemo2 {

	public static void main(String[] args) {
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("1", "cuser");
		hashMap.put("2", "pfei");
		hashMap.put("3", "wneng");
		hashMap.put("4", "yyan");
		hashMap.put(null, "null值");

		for (String key : hashMap.keySet()) {
			System.out.println(hashMap.get(key));
		}
		System.out.println();
		System.out.println(hashMap.containsKey("2"));
		System.out.println(hashMap.containsValue("wneng"));
		System.out.println("is empty? " + hashMap.isEmpty());
		System.out.println("size: " + hashMap.size());

		for (String value : hashMap.values()) {
			System.out.println(value);
		}

		Set<Entry<String, String>> set = hashMap.entrySet();
		System.out.println("\n\nset: " + set);

		System.out.println("\n");

		hashMap.put("1", "chenxushao");
		for (String key : hashMap.keySet()) {
			System.out.println(hashMap.get(key));
		}

	}

}
