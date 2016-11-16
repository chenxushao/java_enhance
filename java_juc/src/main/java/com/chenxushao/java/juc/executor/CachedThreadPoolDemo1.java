package com.chenxushao.java.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author CHENXUSHAO
 *  可变尺寸的线程池
 */
public class CachedThreadPoolDemo1 {

	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		 
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
