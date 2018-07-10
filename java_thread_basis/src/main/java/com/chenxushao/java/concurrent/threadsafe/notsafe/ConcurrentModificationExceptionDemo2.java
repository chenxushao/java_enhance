package com.chenxushao.java.concurrent.threadsafe.notsafe;

import java.util.ArrayList;
import java.util.List;

//在迭代时，进行插入、删除操作会抛出ConcurrentModificationException异常
public class ConcurrentModificationExceptionDemo2 {
	
	public static void main(String[] args) {
		testList();
	}
	
	private static void testList() {
		List<String> tests = new ArrayList<String>();
		tests.add("a");
		tests.add("b");
		tests.add("temp");
		tests.add("c");
		for (String test : tests) {
			if (test.equals("b")) {
//				tests.add("x");
				tests.remove("temp");
			}
			System.out.println(test);
		}
	}
}
