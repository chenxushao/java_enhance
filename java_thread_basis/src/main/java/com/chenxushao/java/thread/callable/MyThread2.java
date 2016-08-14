package com.chenxushao.java.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyThread2 implements Callable<String>{

	public String call() throws Exception {
		 
		Thread.sleep(5000);
		return "hello";
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
	
		MyThread2 mt2 = new MyThread2();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		long begin = System.currentTimeMillis();
		Future<String> future = executor.submit(mt2);
//		System.out.println(future.get());
		System.out.println(future.get(6,TimeUnit.SECONDS));
		System.out.println((System.currentTimeMillis()-begin)/1000);
//		System.out.println(future.get(3,TimeUnit.SECONDS));
	}

}
