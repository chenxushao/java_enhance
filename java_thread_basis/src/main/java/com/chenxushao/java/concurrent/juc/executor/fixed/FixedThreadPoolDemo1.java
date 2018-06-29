package com.chenxushao.java.concurrent.juc.executor.fixed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author CHENXUSHAO 固定大小的线程池
 */
public class FixedThreadPoolDemo1 {

	public static void main(String[] args) {

		// 线程池大小为3
		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int i = 1; i <= 10; i++) {
			executor.execute(new Task());
		}

		executor.shutdown();
	}

	private static class Task implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
