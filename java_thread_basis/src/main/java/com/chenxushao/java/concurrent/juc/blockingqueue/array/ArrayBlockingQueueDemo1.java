package com.chenxushao.java.concurrent.juc.blockingqueue.array;

import java.util.concurrent.ArrayBlockingQueue;

//BlockingQueue的put方法
public class ArrayBlockingQueueDemo1 {
	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		queue.put("2");
		// 队列已满，会一直阻塞
		queue.put("3");
		System.out.println("end");
	}
}
