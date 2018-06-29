package com.chenxushao.java.concurrent.juc.locks.reentrantlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo1 {

	public static void main(String[] args) throws Exception {

		Counter counter = new Counter();

		CountDownLatch cdl = new CountDownLatch(10000*100);

		int NCPU = Runtime.getRuntime().availableProcessors();


		ExecutorService executorService = Executors.newFixedThreadPool(NCPU);

		for(int i=1; i<=10000*100; i++){
			executorService.execute(new Task(counter,cdl));
		}

		cdl.await();
		System.out.println(counter.getCount());

	}



	private static class Task implements Runnable{

		private Counter counter;

		private CountDownLatch cdl;

		public Task(Counter counter, CountDownLatch cdl){
			this.counter = counter;
			this.cdl = cdl;
		}

		@Override
		public void run(){
			counter.increase();
			cdl.countDown();

		}
	}


	private static class Counter{

		private int incr = 0;

		private Lock lock = new ReentrantLock();

		public void increase(){
			lock.lock();
			try {
				incr++;
			}finally {
				lock.unlock();;
			}
		}


		public int getCount(){
			return incr;
		}

	}
}
