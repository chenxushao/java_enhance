package com.chenxushao.java.juc.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExecuteRunnableDemo {

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		executorService.execute(new Runnable() {
		    public void run() {
		        System.out.println("Asynchronous task");
		    }
		});

		executorService.shutdown();
	}
}
