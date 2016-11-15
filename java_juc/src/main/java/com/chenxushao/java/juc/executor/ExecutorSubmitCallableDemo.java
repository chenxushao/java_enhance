package com.chenxushao.java.juc.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorSubmitCallableDemo {

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(new Callable<String>(){
		    public String call() throws Exception {
		        System.out.println("Asynchronous Callable");
		        return "Callable Result";
		    }
		});

		System.out.println("future.get() = " + future.get());
		
	}
}
