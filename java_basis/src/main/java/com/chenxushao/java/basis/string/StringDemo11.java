package com.chenxushao.java.basis.string;

public class StringDemo11 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		String a = "a";
		String b = new String("b");
		String c = "a";
		
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
	}

}
