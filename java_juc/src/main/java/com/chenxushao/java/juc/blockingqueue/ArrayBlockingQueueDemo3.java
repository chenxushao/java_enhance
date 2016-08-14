package com.chenxushao.java.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

//BlockingQueue的add方法
public class ArrayBlockingQueueDemo3 {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		queue.put("2");
		//add方法，如果队列满时，add方法会抛出异常。 java.lang.IllegalStateException: Queue full
		queue.add("3");
		System.out.println(queue.size());
		System.out.println(queue.take());
		System.out.println(queue.take());
		
		//队列已空，会阻塞
		System.out.println(queue.take());
		System.out.println("end");
	}
}
