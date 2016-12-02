package com.chenxushao.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapDemo1 {

	public static void main(String[] args) {
		BiMap<String, Integer> userId = HashBiMap.create();
		userId.put("a", 1);
		userId.put("e", 2);
		userId.put("b", 3);
		userId.put("d", 4);
		BiMap<Integer, String> idUser = userId.inverse();
		System.out.println(userId);
		System.out.println(idUser);
		System.out.println(userId.get("a"));
		System.out.println(idUser.get(1));
	}

}