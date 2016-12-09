package com.chenxushao.java.juc.synchronizer.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo4 {

	public static void main(String[] args) throws InterruptedException {

		final int count = 10;
		final CountDownLatch cdt = new CountDownLatch(count);
		for (int i = 0; i < 10; ++i) {
			Thread t = new Thread("test_thread" + i) {
				public void run() {
					// do sth.
					cdt.countDown();
				}
			};
			t.start();
		}
		cdt.await();
	}

}
