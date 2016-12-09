package com.chenxushao.java.juc.atomic;

import java.util.concurrent.atomic.AtomicLong;

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
