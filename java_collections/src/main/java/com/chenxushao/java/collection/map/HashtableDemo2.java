package com.chenxushao.java.collection.map;

import java.util.Hashtable;

//键和值不允许为空：无特定排序，便键需要实现equals和hashCode方法
public class HashtableDemo2 {

	public static void main(String[] args) {
		Hashtable<String, String> hashTable = new Hashtable<String, String>();
		hashTable.put("3", "cuser");
		hashTable.put("1", "angel");
		hashTable.put("2", "pfei");
		/* hashTable.put("project", null); */
		/* hashTable.put(null,"project"); */
		/* hashTable.remove("project"); */
		hashTable.put("project", "test");
		hashTable.put("22", "java");
		hashTable.put("22", "world");
		hashTable.put("42", "book");
		hashTable.put("4", "zhi");

		for (String key : hashTable.keySet()) {
			System.out.println(hashTable.get(key));
		}
	}
}
