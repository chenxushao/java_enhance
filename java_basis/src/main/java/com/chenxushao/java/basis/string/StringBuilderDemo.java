package com.chenxushao.java.basis.string;

public class StringBuilderDemo {


	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		System.out.println("sb: " + sb.toString());
		System.out.println(System.identityHashCode(sb));
		sb.append("Hello,Java!");
		System.out.println(System.identityHashCode(sb));
		sb.append("!");
		System.out.println(System.identityHashCode(sb));
		sb.append(".");
		System.out.println(System.identityHashCode(sb));
		System.out.println("sb: " + sb.toString());
		sb.delete(sb.length()-2,sb.length());
		System.out.println(System.identityHashCode(sb));
		System.out.println("sb: " + sb.toString());
		
		
	}

}
