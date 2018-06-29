package com.chenxushao.java.concurrent.arithmetic.multithreadsum;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author CHENXUSHAO  使用CompletionService实现多线程求和
 */
public class MultiThreadSum2 {
	public static void main(String[] args) throws Exception {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i <= 20000; i++) {
			numbers.add(Integer.valueOf(i));
		}
	
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
		
		List<List<Integer>> numberLists = Lists.partition(numbers, 4000);
		for (List<Integer> numberList : numberLists) {
			completionService.submit(new SumTask(numberList));
		}
		Integer sum = Integer.valueOf(0);
      
		for(int i=0; i<numberLists.size();i++){
			sum = sum + completionService.take().get();
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
