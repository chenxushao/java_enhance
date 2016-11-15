package com.chenxushao.java.juc.collections.queue.blocking.array;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

//BlockingQueue的poll方法
//从队列弹出元素，有三个方法remove、poll、take
//remove：队列空时，抛出异常；poll：队列空时，立即返回false；take：队列空时，会一直阻塞
public class ArrayBlockingQueueDemo8 {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		queue.put("2");
	 
		
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		
		//队列空时，立即返回null
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll(15,TimeUnit.SECONDS));
	    System.out.println("end");
	    //支持超时退出
//	    queue.poll(timeout, unit)
		
		
	}
}