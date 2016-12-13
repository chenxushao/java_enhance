package com.chenxushao.java.juc.atomic.counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ID序列生成器
 */
public class IdGenerator {
	private final AtomicLong sequenceNumber = new AtomicLong(0);

	public long next() {
		return sequenceNumber.getAndIncrement();
	}
}