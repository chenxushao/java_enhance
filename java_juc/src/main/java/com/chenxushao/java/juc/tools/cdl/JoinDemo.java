package com.chenxushao.java.juc.tools.cdl;

/**
 * CountDownLatch能实现和join一样的功能.但比join更强大,更灵活.
 */
public class JoinDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread parser1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser1 finish");
			}
		});

		Thread parser2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser2 finish");
			}
		});

		parser1.start();
		parser2.start();
		parser1.join();
		parser2.join();
		System.out.println("all parser finish");
	}
}