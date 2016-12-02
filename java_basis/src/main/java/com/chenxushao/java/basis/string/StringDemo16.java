package com.chenxushao.java.basis.string;

public class StringDemo16 {

	public static void main(String[] args) {
		String s1 = null;
		String s2 = "";
		String s3 = new String();
		String s4 = new String("");

		System.out.println(s2 == s3);// false
		System.out.println(s2 == s4);// false
		System.out.println(s3 == s4);// false

		System.out.println(s2.equals(s3));// true
		System.out.println(s2.equals(s4));// true
		System.out.println(s3.equals(s4));// true
	}
}
