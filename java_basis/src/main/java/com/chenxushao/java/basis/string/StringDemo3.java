package com.chenxushao.java.basis.string;

public class StringDemo3 {

	public static void main(String[] args) {
		String s1 = "Hello,World!";// 常量池
		String s2 = new String("Hello,World!");
		String s3 = s2.intern();// 返回一个字符串在常量池中的地址

		String s4 = new String("abc");
		System.out.println("value: " + s4.intern());
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s3);
		System.out.println(s3.equals(s2));

	}

}
