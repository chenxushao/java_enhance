package com.chenxushao.java.basis.string;

public class StringAndStringBufferDemo {

	private static String method1() {
		String result = "";
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000 * 15; i++) {
			result = result + String.valueOf(i);
		}
		System.out.println("method1: " + (System.currentTimeMillis() - start));
		return result;
	}

	private static String method2() {
		StringBuffer result = new StringBuffer();
		String s = "Hello,World!";
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000 * 100; i++) {
			result.append(s);
		}
		System.out.println("method2: " + (System.currentTimeMillis() - start));
		return result.toString();
	}

	public static void main(String[] args) {
		method1();
		/* method2(); */

		// 比较时间，即可判断二者效率优劣了
	}
}
