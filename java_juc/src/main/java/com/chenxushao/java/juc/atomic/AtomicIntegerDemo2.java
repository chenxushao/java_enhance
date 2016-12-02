package com.chenxushao.java.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cuser on 16/7/24.
 */
public class AtomicIntegerDemo2 {

	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger();
		// AtomicInteger atomicInteger = new AtomicInteger(123);

		int theValue = atomicInteger.get();

		atomicInteger.set(234);

		int expectedValue = 123;
		int newValue = 234;
		atomicInteger.compareAndSet(expectedValue, newValue);

		System.out.println(atomicInteger.getAndAdd(10));
		System.out.println(atomicInteger.addAndGet(10));
	}
}
