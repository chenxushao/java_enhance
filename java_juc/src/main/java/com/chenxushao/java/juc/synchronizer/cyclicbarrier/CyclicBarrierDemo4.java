package com.chenxushao.java.juc.synchronizer.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo4 {
	public static void main(String[] args) {
		final  int threadCount = 10;
		CyclicBarrier barrier = new CyclicBarrier(threadCount, new Runnable() {
			public void run() {
//				collectTestResult();
			}
		});
		for (int i = 0; i < threadCount; ++i) {
			Thread thread = new Thread("test-thread " + i) {
			public void run() {
			try {
//				doTest();
				barrier.await();
			} catch (InterruptedException e) {
				return;
			} catch (BrokenBarrierException e) {
				return;
				}
			}
		};
		thread.start();
		}
	}
}