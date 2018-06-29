package com.chenxushao.java.concurrent.juc.atomic.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cuser on 16/7/24.
 */
public class SafeCounter2 {

	private AtomicInteger count = new AtomicInteger(0);

	// 使用AtomicInteger后,不需要加锁，也可以达到线程安全
	public void increment() {
		count.incrementAndGet();
	}

	public int getCount() {
		return count.get();
	}
}
