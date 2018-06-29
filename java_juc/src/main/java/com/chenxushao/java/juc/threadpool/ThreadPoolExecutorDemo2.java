package com.chenxushao.java.juc.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorDemo2 implements Runnable {

	private static AtomicInteger counter = new AtomicInteger();
	private final int taskId;

	public int getTaskId() {
		return taskId;
	}

	public ThreadPoolExecutorDemo2(int taskId) {
		this.taskId = taskId;
	}

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("---" + taskId);
	}

	public static void main(String[] args) {

		// 阻塞队列
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(20);
		// 线程工厂
		ThreadFactory threadFactory = new ThreadFactory() {
			public Thread newThread(Runnable r) {
				int currentCount = counter.getAndIncrement();
				System.out.println("Creating newstate thread: " + currentCount);
				return new Thread(r, "mythread" + currentCount);
			}
		};

		// 拒绝策略
		RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() {
			public void rejectedExecution(Runnable r,
					ThreadPoolExecutor executor) {
				if (r instanceof ThreadPoolExecutorDemo2) {
					ThreadPoolExecutorDemo2 example = (ThreadPoolExecutorDemo2) r;
					System.out.println("Rejecting task with id "
							+ example.getTaskId());
				}
			}
		};
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1,
				TimeUnit.SECONDS, queue, threadFactory, rejectedHandler);
		for (int i = 0; i < 100; i++) {
			executor.execute(new ThreadPoolExecutorDemo2(i));
		}
		executor.shutdown();
	}
}