package com.chenxushao.java.collection.map;

import java.util.Hashtable;


//HashTable的测试案例
public class HashTableSample {
	public static void main(String[] args) {
		Hashtable<String,String> table = new Hashtable<String,String>();
		table.put("1", "a");
		table.put("2", "b");
		table.put("1", "b");
		
		
		System.out.println(table);
		System.out.println(table.get("1"));
		System.out.println(table.get("2"));
		
		table.remove("1");
		
		System.out.println(table);
		System.out.println(table.get("1"));
		System.out.println(table.get("2"));
	}

}
