package com.chenxushao.java.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cuser on 16/7/24.
 */
public class AtomicIntegerDemo1 {

	// private static AtomicInteger atomicInteger = new AtomicInteger(2);
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) {
		System.out.println(atomicInteger.get());//
		System.out.println(atomicInteger.getAndAdd(5));// 增加5,返回旧值
		System.out.println(atomicInteger.get());
		System.out.println(atomicInteger.getAndAdd(-5));
		System.out.println(atomicInteger.get());

		System.out.println(atomicInteger.getAndSet(2));
		System.out.println(atomicInteger.get());

		System.out.println(atomicInteger.incrementAndGet());
	}
}
