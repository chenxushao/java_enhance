package com.chenxushao.java.basis.string;

public class String4 {

	public static void main(String[] args) {
		String a = "ab";

		final String bb = getBB();//涉及到方法调用，值无法在编译期确定

		String b = "a" + bb;

		System.out.println(a == b);
	}

	private static String getBB() {
		return "b";
	}
}
