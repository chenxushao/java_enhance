package com.chenxushao.java.basis.string;

public class StringDemo13 {

	public static void main(String[] args) {
		String a = "Hello,Wrold!";
		String b = "Hello" + ",Wrold" + "!";

		System.out.println(a == b);// true
		System.out.println(a.equals(b));
	}
}
