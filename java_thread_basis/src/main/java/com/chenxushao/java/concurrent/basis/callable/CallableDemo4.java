package com.chenxushao.java.concurrent.basis.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 多线程求和
 *
 * @author CHENXUSHAO
 */
public class CallableDemo4 {
	private static final int NTHREDS = 10;

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for (int i = 0; i < 20000; i++) {
			Callable<Long> worker = new SumCallable();
			Future<Long> submit = executor.submit(worker);
			list.add(submit);
		}
		long sum = 0;
		System.out.println("resultSize: " + list.size());
		// now retrieve the result
		for (Future<Long> future : list) {
			try {
				sum += future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("sum: " + sum);
		executor.shutdown();
	}

	private static  class SumCallable implements Callable<Long> {
		@Override
		public Long call() throws Exception {
			long sum = 0;
			for (long i = 0; i <= 100; i++) {
				sum += i;
			}
			return sum;
		}
	}
}