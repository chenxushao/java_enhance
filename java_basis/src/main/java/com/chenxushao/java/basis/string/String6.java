package com.chenxushao.java.basis.string;

public class String6 {

	private static String a = new String("ab");

	public static void main(String[] args) {

		String s1 = "a";

		String s2 = "b";

		String s = s1 + s2;

		System.out.println(s == a);//false

		System.out.println(s.intern() == a);//false

		System.out.println(s.intern() == a.intern());//true

	}

}
