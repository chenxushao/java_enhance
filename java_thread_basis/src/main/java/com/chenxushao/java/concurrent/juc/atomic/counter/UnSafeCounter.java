package com.chenxushao.java.concurrent.juc.atomic.counter;

/**
 * Created by cuser on 16/7/24. 非线程安全计数器
 */
public class UnSafeCounter {

	private volatile int count = 0;

	public void increment() {
		count++;
	}

	public int getCount() {
		return count;
	}
}
