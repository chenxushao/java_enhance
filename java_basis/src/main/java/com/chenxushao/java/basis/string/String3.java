package com.chenxushao.java.basis.string;

public class String3 {

	public static void main(String[] args) {
		String a = "ab";

		final String bb = "b";

		String b = "a" + bb;//涉及到的是终态变量

		System.out.println(a == b);//true

	}
}
