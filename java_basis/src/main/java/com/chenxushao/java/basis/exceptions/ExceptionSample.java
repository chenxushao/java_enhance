package com.chenxushao.java.basis.exceptions;

public class ExceptionSample {

	public static double method(int x) {
		int result = 0;
		if (x == 0) {
			try {
				result = 1 / x;
			} catch (ArithmeticException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("x=0");

		}

		if (x > -1) {
			try {
				result = 1 / 0;
				System.out.println("x=1");
			} catch (ArithmeticException e) {
				System.out.println("in catch while x=1");
			}
		}

		/*
		 * if (x == 2){ result = 2; System.out.println("x=1"); }
		 */
		System.out.println("test and dmeo");
		System.out.println("method rear.");
		return result;
	}

	public static void main(String[] args) {
		// System.out.println(method(1));
		System.out.println(method(0));
	}
}
