package com.chenxushao.java.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

//BlockingQueue的element方法
public class ArrayBlockingQueueDemo5 {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		queue.put("2");
	 
		System.out.println(queue.size());
		System.out.println(queue.element());//返回队首元素，但并不删除
		System.out.println(queue.element());
		System.out.println(queue.size());//长度不变
		
		
		System.out.println(queue.take());
		System.out.println(queue.take());
		System.out.println(queue.size());
		
		//如果队列为空时，抛出java.util.NoSuchElementException异常
		System.out.println(queue.element());
	}
}
