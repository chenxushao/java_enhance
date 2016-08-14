package com.chenxushao.java.basis.integer;

public class InternDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = new String("abc");
		System.out.println(s1.hashCode());
		System.out.println("abc".hashCode());
		System.out.println(s1.intern().hashCode());
		System.out.println(s1 == "abc");//false
		System.out.println(s1 == s1.intern());//false
		System.out.println("abc" == s1.intern());//true
		
	}

}
