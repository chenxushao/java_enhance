package com.chenxushao.java.basis.string;

public class StringConcatDemo {

	public static void main(String[] args) {
		String str = "abc";
		String s1 = str.concat("");
		String s2 = str.concat("m");
		System.out.println(str == s1);// 当要连接的串为""时，返回本身(this)，所以这里返回true
		System.out.println(str == s2);
		System.out.println("s1: " + s1);
		System.out.println("s2: " + s2);
	}
}
