package com.chenxushao.java.juc.visibility;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

public class TestVolatile {

	public static void main(String[] args) {
		int threadCount = 10;
		long start = System.currentTimeMillis();
		final Increment inc = new IncrementByVolatile();
		final CyclicBarrier barrier = new CyclicBarrier(threadCount);
		final CountDownLatch latch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			Thread t = new Thread(new FutureTask<Void>(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					barrier.await();
					for (int j = 0; j < 100000; j++) {
						inc.increment();
					}
					latch.countDown();
					return null;
				}
			}));
			t.start();
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long cost = System.currentTimeMillis() - start;
		System.out.println("inc=" + inc.get() + ", class="
				+ inc.getClass().getSimpleName() + ", cost=" + cost + " ms");
	}

}

interface Increment {
	public void increment();

	public int get();
}

class IncrementByVolatile implements Increment {
	private volatile int i;

	public synchronized void increment() {
		int tmp = i;// volatile读：保证原子，并保证读到最新的
		tmp = tmp + 1;
		i = tmp;// volatile写：保证原子，并保证刷入主内存
	}

	public int get() {
		return this.i;
	}
}

class IncrementBySynchronized implements Increment {
	private int i;

	public synchronized void increment() {
		int tmp = get();
		tmp = tmp + 1;
		set(tmp);
	}

	public synchronized int get() {
		return i;
	}

	public synchronized void set(int i) {
		this.i = i;
	}
}
