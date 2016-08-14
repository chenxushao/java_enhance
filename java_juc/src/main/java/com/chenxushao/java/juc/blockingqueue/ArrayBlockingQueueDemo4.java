package com.chenxushao.java.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

//BlockingQueue的remove方法
public class ArrayBlockingQueueDemo4 {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		queue.put("2");
	 
		System.out.println(queue.size());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		 
		System.out.println(queue.size());
		//当队列为空时，从队列获取元素会抛出 java.util.NoSuchElementException异常，队列大小不固定优先选择LinkedBlockingQueue；
		System.out.println(queue.remove());
	}
}
