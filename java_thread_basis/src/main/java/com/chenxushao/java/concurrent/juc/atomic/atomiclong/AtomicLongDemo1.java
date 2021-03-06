package com.chenxushao.java.concurrent.juc.atomic.atomiclong;

import java.util.concurrent.atomic.AtomicLong;

//方法基本和AtomicInteger类似
public class AtomicLongDemo1 {

	public static AtomicLong atomicLong = new AtomicLong();

	public static void main(String[] args) {
		for (int i = 1; i <= 20; i++) {
			System.out.println(getNext());
		}
	}

	public static long getNext() {
		return atomicLong.incrementAndGet();
	}
}
