package com.chenxushao.java.juc.collections.queue.blocking.array;

import java.util.concurrent.ArrayBlockingQueue;
//BlockingQueue的take方法
public class ArrayBlockingQueueDemo2 {

	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		queue.put("2");
		System.out.println(queue.size());
		System.out.println(queue.take());
		System.out.println(queue.take());
		System.out.println(queue.size());
		//队列已空，会一直阻塞，直到队列有数据
		System.out.println(queue.take());
		System.out.println("end");
		
	}

}
