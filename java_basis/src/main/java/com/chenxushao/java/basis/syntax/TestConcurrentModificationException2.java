package com.chenxushao.java.basis.syntax;

public class TestConcurrentModificationException2 {
	public static void main(String[] args) {
		AccessList alist = new AccessList();
		Thread t1 = new Thread(alist, "t1");
		Thread t2 = new Thread(alist, "t2");
		t1.start();
		t2.start();

		System.out.println("This is Demo........");
	}
}
