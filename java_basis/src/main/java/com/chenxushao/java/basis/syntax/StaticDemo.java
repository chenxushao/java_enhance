package com.chenxushao.java.basis.syntax;

public class StaticDemo {

	static int a = 10;
	// 静态代码仅执行一次，在类加载的时候就执行
	static {
		a = 8;
	}

	public static void main(String[] args) {
		System.out.println("a: " + a++);
		System.out.println("a: " + a);
	}

}
