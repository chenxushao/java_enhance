package com.chenxushao.java.basis.string;

public class String9 {
	public static void main(String[] args) {
		final String pig = "length: 10";
		final String dog = "length: " + pig.length();
		System.out.println("Animals are equal: " + pig == dog);// false
																// //陷阱在运算符优先级那里
		String a = "a";
		String b = "a";
		System.out.println("a==b ? " + a == b);// false

		String c = "ac";
		String d = "a" + "c";
		String e = a + "c";
		System.out.println(c == d);// true
		System.out.println(c == e);// false //涉及到了变量
	}
}
