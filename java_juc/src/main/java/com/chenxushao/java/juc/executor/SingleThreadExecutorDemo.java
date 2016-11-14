package com.chenxushao.java.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author CHENXUSHAO
 * 单任务线程池
 */
public class SingleThreadExecutorDemo {

	
	public static void main(String[] args) {
		//单任务线程池
		ExecutorService executor = Executors.newSingleThreadExecutor();
		 
		for(int i=1; i<=10;i++){
			executor.execute(new Task());
		}
		executor.shutdown();
	}
	
	private static class Task implements Runnable{

		@Override
		public void run() {
			 System.out.println(Thread.currentThread().getName());
			 try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
