package com.chenxushao.java.collection.list;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo1 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		list.add("a");
		list.add("b");
		list.add("c");

		System.out.println(list);

		list.remove("c");

		System.out.println(list);
	}

}
