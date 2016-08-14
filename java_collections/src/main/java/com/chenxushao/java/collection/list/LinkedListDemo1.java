package com.chenxushao.java.collection.list;

import java.util.LinkedList;

public class LinkedListDemo1 {

	public static void main(String[] args) {

		LinkedList<String> list = new LinkedList<String>();

		list.add("a");
		list.add("b");
		list.add("c");

		System.out.println(list);

		list.remove("c");

		System.out.println(list);
		
		
		System.out.println(list.peek());
		System.out.println(list);
		
		list.offer("x");
		System.out.println(list);
		System.out.println(list.peek());
		System.out.println(list.poll());;
		System.out.println(list);
		
	}

}
