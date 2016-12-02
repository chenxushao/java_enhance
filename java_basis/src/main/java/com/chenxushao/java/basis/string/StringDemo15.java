package com.chenxushao.java.basis.string;

public class StringDemo15 {

	public static void main(String[] args) {
		final int len = 12;// 用final修饰
		final String s = "的长度:";// 用final修饰
		String a = "Hello,Wrold!的长度:12";
		String b = "Hello" + ",Wrold" + "!" + "的长度:" + 12;
		String c = "Hello" + ",Wrold" + "!" + "的长度:" + len;
		String d = "Hello" + ",Wrold" + "!" + s + len;

		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println("d: " + d);
		System.out.println(a == b);// true:在编译时期就能确定b的值
		System.out.println(a == c);// true
		System.out.println(a == d);// true//用final修饰的在编译期可以确定
	}
}
