package com.chenxushao.java.juc.exeception;

import java.util.Vector;

//在迭代时，进行插入、删除操作会抛出ConcurrentModificationException异常
public class ConcurrentModificationExceptionDemo1 {

	public static void main(String[] args) {
		testVector();
	}

	private static void testVector() {
		Vector<String> tests = new Vector<String>();
		tests.add("a");
		tests.add("b");
		tests.add("temp");
		tests.add("c");
		for (String test : tests) {
			if (test.equals("b")) {
				tests.remove("temp");
			}
			System.out.println(test);
		}
	}

}
