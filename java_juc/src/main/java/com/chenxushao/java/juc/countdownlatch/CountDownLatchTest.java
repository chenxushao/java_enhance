package com.chenxushao.java.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author CHENXUSHAO
 * 用在多个线程时，你只需要把这个CountDownLatch的引用传递到线程里
 */
public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		
		final CountDownLatch latch = new CountDownLatch(2);
		
		Thread parser1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser1 finish");
				latch.countDown();
			}
		});

		Thread parser2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser2 finish");
				latch.countDown();
			}
		});
		parser1.start();
		parser2.start();
		latch.await();
		System.out.println("all parser finish");
		
	}

}
