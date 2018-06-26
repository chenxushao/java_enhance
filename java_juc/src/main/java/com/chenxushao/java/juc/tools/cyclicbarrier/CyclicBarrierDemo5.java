package com.chenxushao.java.juc.tools.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo5 {
	public static void main(String[] args) {
		final CyclicBarrier barrier = new CyclicBarrier(2,new Runnable() {
			@Override
			public void run() {
			 System.out.println("终于轮到我了");
			}
		});
		Runnable action1 = new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.printf("Thread %s " + "executing barrier action.%n",
						name);
				try {
					barrier.await();
					barrier.await();//可以调用多次，但是要到达关卡，必须多个线程都调用。
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		ExecutorService executor =  Executors.newCachedThreadPool();
		executor.execute(action1);
		executor.execute(new CBTask(barrier));
		System.out.println("hello");
	}
	
	private static class CBTask  implements Runnable{
  
		private CyclicBarrier barrier;
		
		public CBTask(CyclicBarrier barrier){
			this.barrier = barrier;
		}
		
		@Override
		public void run() {
		    System.out.println("cb");
		    try {
		    	System.out.println("xx");
				barrier.await();
				System.out.println("y");
				barrier.await();
				System.out.println("z");
				barrier.await();
				System.out.println("over");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}