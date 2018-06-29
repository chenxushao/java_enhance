package com.chenxushao.java.concurrent.juc.atomic.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeCounter3 {

	private volatile int count = 0;
	
	Lock lock = new ReentrantLock();
	// 使用AtomicInteger后,不需要加锁，也可以达到线程安全
	public  void increment() {
		lock.lock();
		try{
		 count++;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	public int getCount() {
		return count;
	}
}
