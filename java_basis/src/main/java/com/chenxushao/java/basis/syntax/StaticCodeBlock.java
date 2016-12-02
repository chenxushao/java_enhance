package com.chenxushao.java.basis.syntax;

public class StaticCodeBlock {

	Person p1 = new Person("p1");
	static int b;
	static {
		System.out.println("StaticCodeBlock--->static code block...");

	}

	public StaticCodeBlock() {
		System.out.println("StaticCodeBlock���췽��");
	}

	static Person p2 = new Person("p2");
}
