package com.chenxushao.java.jvm.basis;

public class TestStack {
	private int count = 0;

	// 没有出口的递归函数
	public void recursion(long a, long b, long c) throws InterruptedException {
		long d = 0, e = 0, f = 0;
		count++;// 每次调用深度加1
		recursion(a, b, c);// 递归
	}

	public void testStack() {
		try {
			recursion(1L, 2L, 3L);
		} catch (Throwable e) {
			System.out.println("deep of stack is " + count);// 打印栈溢出的深度
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestStack ts = new TestStack();
		ts.testStack();
	}
}
