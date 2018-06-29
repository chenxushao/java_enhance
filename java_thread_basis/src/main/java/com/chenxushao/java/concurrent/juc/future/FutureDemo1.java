package com.chenxushao.java.concurrent.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo1 {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello";
			};
		});
		System.out.println("等待结果");
		try {
			System.out.println("拿到结果：" + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}