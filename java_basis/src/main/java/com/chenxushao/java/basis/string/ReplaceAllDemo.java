package com.chenxushao.java.basis.string;

public class ReplaceAllDemo {

	public static void main(String[] args) {
		String str = "java.lang.String，java.lang.String，java.lang.String";

		// System.out.println(str.replace("lang", "a"));//普通替换使用replace即可
		System.out.println(str.replace(".", "A"));
		System.out.println(str.replaceAll(".", "A"));// 第一个参数使用的是正则表达式，需要注意
	}
}
