package com.chenxushao.java.juc.collections.queue.blocking.array;

import java.util.concurrent.ArrayBlockingQueue;

//BlockingQueue的offer方法
//向队列压入元素，有三个方法add、offer、put
//add：队列满时，抛出异常；offer：队列满时，立即返回false；put：队列满时，会一直阻塞
public class ArrayBlockingQueueDemo7 {

	public static void main(String[] args) throws InterruptedException {

		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		System.out.println(queue.offer("2"));
		// 队列满时，立即返回false
		System.out.println(queue.offer("x"));
		// 支持超时退出
		// queue.offer(e, timeout, unit)

		System.out.println(queue.size());
	}
}