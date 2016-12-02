package com.chenxushao.java.collection.list;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo2 {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");

		String str = new String("b");
		System.out.println(list.contains("a"));
		System.out.println(list.contains(new String("a")));
		System.out.println(list.contains(str));

		System.out.println(list.indexOf("a"));
		System.out.println(list.indexOf(new String("a")));
		System.out.println(list.indexOf(str));

	}

}
