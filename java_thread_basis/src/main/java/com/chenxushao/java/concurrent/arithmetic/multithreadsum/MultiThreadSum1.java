package com.chenxushao.java.concurrent.arithmetic.multithreadsum;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author CHENXUSHAO  使用Future实现多线程求和
 */
public class MultiThreadSum1 {
	public static void main(String[] args) throws Exception {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i <= 20000; i++) {
			numbers.add(Integer.valueOf(i));
		}
		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<Future<Integer>> result = Lists.newArrayList();
		List<List<Integer>> numberLists = Lists.partition(numbers, 4000);
		for (List<Integer> numberList : numberLists) {
			Future<Integer> future = executor.submit(new SumTask(numberList));
			result.add(future);
		}
		Integer sum = Integer.valueOf(0);
		for (Future<Integer> future : result) {
			sum = sum + future.get();
		}
		System.out.println("sum: " + sum);
		executor.shutdown();
	}

	private static class SumTask implements Callable<Integer> {
		private List<Integer> numbers;

		public SumTask(List<Integer> numbers) {
			this.numbers = numbers;
		}

		@Override
		public Integer call() throws Exception {
			Integer sum = Integer.valueOf(0);
			for (Integer number : numbers) {
				sum = sum + number;
			}
			return sum;
		}
	}
}
