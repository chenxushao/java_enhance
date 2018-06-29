package com.chenxushao.java.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cuser on 16/7/24.
 */
public class AtomicIntegerDemo1 {
	
	/**
	 * 常见的方法列表
	 * @see AtomicInteger#get()             直接返回值
	 * @see AtomicInteger#getAndAdd(int)    增加指定的数据，返回变化前的数据
	 * @see AtomicInteger#getAndDecrement() 减少1，返回减少前的数据
	 * @see AtomicInteger#getAndIncrement() 增加1，返回增加前的数据
	 * @see AtomicInteger#getAndSet(int)    设置指定的数据，返回设置前的数据
	 * 
	 * @see AtomicInteger#addAndGet(int)    增加指定的数据后返回增加后的数据
	 * @see AtomicInteger#decrementAndGet() 减少1，返回减少后的值
	 * @see AtomicInteger#incrementAndGet() 增加1，返回增加后的值
	 * @see AtomicInteger#lazySet(int)      仅仅当get时才会set
	 * 
	 * @see AtomicInteger#compareAndSet(int, int) 尝试新增后对比，若增加成功则返回true否则返回false
	 */

	// private static AtomicInteger atomicInteger = newstate AtomicInteger(2);
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
