package com.chenxushao.java.basis.syntax;

public class TestFinally {
	public static void main(String[] args) {
		System.out.println("test1:" + testFinal1());
		System.out.println("test2:" + testFinal2());
		System.out.println("test3:" + testFinal3());
		System.out.println("test4:" + testFinal4());
		System.out.println("test5:" + testFinal5());
	}

	static int testFinal1() {
		int i = 1;
		try {
			return i;
		} finally {
			System.out.println("in testFinal1():finally 肯定会被执行的！");
			i = 48;
		}
	}

	static String testFinal2() {
		String str = "try";
		try {
			return str;
		} finally {
			System.out.println("in testFinal2():finally 肯定会被执行的！");
			str = "finally";
		}
	}

	static StringBuilder testFinal3() {
		StringBuilder build = new StringBuilder("try ");
		try {
			return build;
		} finally {
			System.out.println("in testFinal3():finally 肯定会被执行的！");
			build.append("finally");
			build = new StringBuilder("你猜我是谁！");
		}
	}

	static String testFinal4() {
		try {
			return "return in try";
		} finally {
			System.out.println("in testFinal4():finally 肯定会被执行的！");
			return "return in finally";
		}
	}

	static int testFinal5() {
		try {
			throw new Exception("异常");
		} catch (Exception e) {
			System.out.println("Exception block");
			return 200;
		} finally {
			return 300;
		}
	}
}