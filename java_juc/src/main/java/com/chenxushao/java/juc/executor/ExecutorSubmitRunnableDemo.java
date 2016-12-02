package com.chenxushao.java.juc.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorSubmitRunnableDemo {

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future future = executorService.submit(new Runnable() {
			public void run() {
				System.out.println("Asynchronous task");
			}
		});

		System.out.println(future.get()); // returns null if the task has
											// finished correctly.
	}
}
