package com.chenxushao.java.juc.collections.queue.blocking.array;

import java.util.concurrent.ArrayBlockingQueue;

//BlockingQueue的peek方法
public class ArrayBlockingQueueDemo6 {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		queue.put("2");
	 
		
		System.out.println(queue.size());
		System.out.println(queue.peek());//返回队首元素，但并不删除
		System.out.println(queue.peek());
		System.out.println(queue.size());//长度不变
		
		
		System.out.println(queue.take());
		System.out.println(queue.take());
		System.out.println(queue.size());
		
		//如果队列为空时，返回null，并不会抛出java.util.NoSuchElementException异常
		System.out.println(queue.peek());
	}
}
