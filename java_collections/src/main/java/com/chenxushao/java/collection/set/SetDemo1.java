package com.chenxushao.java.collection.set;

import java.util.HashSet;
import java.util.Set;

public class SetDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		set.add("e");
		
		
		String str = new String("b");
		System.out.println(set.contains("a"));
		System.out.println(set.contains(new String("a")));
		System.out.println(set.contains(str));
		
		
		
	}

}
