package com.chenxushao.java.basis.string;

public class String8 {

	public static void main(String[] args) {
		String a = "a";
		String b = "a";
		String ab = "ab";
		System.out.println("ab" == a + b);// false //涉及到了变量的+
		System.out.println("ab" == ab);// true
		System.out.println("a" + "b" == ab);// true
	}
}
