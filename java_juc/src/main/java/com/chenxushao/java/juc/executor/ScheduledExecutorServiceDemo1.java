package com.chenxushao.java.juc.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo1 {

	public static void main(String[] args) throws Exception {
		ScheduledExecutorService scheduledExecutorService =
		        Executors.newScheduledThreadPool(5);

		ScheduledFuture<String> scheduledFuture =
		    scheduledExecutorService.schedule(new Callable<String>() {
		        public String call() throws Exception {
		            System.out.println("Executed!");
		            return "Called!";
		        }
		    },
		    5,
		    TimeUnit.SECONDS);

		System.out.println("result = " + scheduledFuture.get());
		scheduledExecutorService.shutdown();
	}
}
