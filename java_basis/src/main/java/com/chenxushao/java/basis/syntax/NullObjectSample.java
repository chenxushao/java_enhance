package com.chenxushao.java.basis.syntax;

public class NullObjectSample {

	public static void main(String[] args) {
		Object o = null;

		System.out.println(o);// 输出null
		System.out.println(o.toString());// 抛出NullPointerException
	}

}
