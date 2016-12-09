package com.chenxushao.java.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo1 {

	public static void main(String[] args) throws Exception {

		Lock lock = new ReentrantLock();

		Condition condition = lock.newCondition();
		condition.await();
		condition.signal();

		lock.lock();
		// lock.tryLock(time, unit)
		// lock.lockInterruptibly();
		lock.unlock();
	}
}
