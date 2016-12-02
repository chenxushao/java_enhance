package com.chenxushao.java.basis.syntax;

public class TestException {

	public static void main(String[] args) {

		int value = method();
		System.out.println("value: " + value);
	}

	private static int method() {
		int a = 0;
		try {
			System.out.println("try");
			return a;
		} finally {
			++a;
		}
	}
}
