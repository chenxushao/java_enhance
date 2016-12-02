package com.chenxushao.java.basis.string;

public class StringDemo0 {

	public static void main(String[] args) {
		String a = new String("Hello,Wrold!");// 该条语句创建了2个字符串对象：首次调用

		String b = new String("Hello,Wrold!");// 该条语句创建了1个字符串对象:不是首次调用

		String c = "Hello,Wrold!";
		String d = "Hello,Wrold!";
		String e = "Hello," + "Wrold!";

		System.out.println("a: " + a);
		/*
		 * System.out.println(System.identityHashCode(a));
		 * System.out.println(System.identityHashCode(b));
		 * System.out.println(System.identityHashCode(c));
		 * System.out.println(System.identityHashCode(d));
		 * System.out.println(System.identityHashCode(e));
		 */

		String str = "Hello";
		System.out.println(System.identityHashCode(str));
		str = str + "Java";
		System.out.println(System.identityHashCode(str));
		str = str + "！";
		System.out.println(System.identityHashCode(str));
	}
}
