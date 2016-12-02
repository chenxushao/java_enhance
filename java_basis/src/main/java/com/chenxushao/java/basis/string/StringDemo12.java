package com.chenxushao.java.basis.string;

public class StringDemo12 {

	public static void main(String[] args) {
		String a = "Hello,Wrold!";
		String b = "Hello,Wrold!";
		String c = new String("Hello,Wrold!");

		System.out.println(a == b);// true
		System.out.println(a == c);// false
		System.out.println(b == c);// false
	}
}
