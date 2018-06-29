package com.chenxushao.java.concurrent.basis.shutdown;

//让线程自己中止的办法
public class ThreadShutdown {

	public static void main(String[] args) {
		TestThread r = new TestThread();
		Thread t = new Thread(r);
		t.start();
		for (int i = 0; i <= 1000; i++) {
			if (i % 100 == 0 & i > 0) {
				System.out.println("in main Thread: " + i);
			}
		}
		System.out.println(Thread.currentThread().getName());
		System.out.println("Thread is over.");
		r.shutDown();
	}

	private static class TestThread implements Runnable {
		//要使用volatile
		private volatile boolean flag = true;

		public void run() {
			int i = 0;
			while (flag) {
				System.out.println("  " + i++);
				System.out.println(Thread.currentThread().getName());
			}
		}

		// 让线程中止的依法
		public void shutDown() {
			flag = false;
		}
	}

}

