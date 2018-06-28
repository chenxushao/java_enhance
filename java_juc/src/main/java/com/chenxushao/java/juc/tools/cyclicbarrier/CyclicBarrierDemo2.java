package com.chenxushao.java.juc.tools.cyclicbarrier;

import com.chenxushao.java.juc.util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo2 {
	public static void main(String[] args) {
		// 创建CyclicBarrier对象,并设置执行完一组5个线程的并发任务后，再执行MainTask任务
		CyclicBarrier cb = new CyclicBarrier(5, new MainTask());
		new SubTask("A", cb).start();
		new SubTask("B", cb).start();
		new SubTask("C", cb).start();
		new SubTask("D", cb).start();
		new SubTask("E", cb).start();
	}


	/**
	 * 最后执行的任务
	 */
	private static class MainTask implements Runnable {
		public void run() {
			System.out.println("......终于要执行最后的任务了......");
		}
	}

	/**
	 * 一组并发任务
	 */
	private static class SubTask extends Thread {
		private String name;

		private CyclicBarrier cb;

		SubTask(String name, CyclicBarrier cb) {
			this.name = name;
			this.cb = cb;
		}

		public void run() {
			System.out.println("[并发任务" + name + "]  开始执行");
			ThreadUtil.sleep(new Random().nextInt(20), TimeUnit.SECONDS);
			System.out.println("[并发任务" + name + "]  执行完毕，通知障碍器");
			try {
				// 每执行完一项任务就通知障碍器
				cb.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}

