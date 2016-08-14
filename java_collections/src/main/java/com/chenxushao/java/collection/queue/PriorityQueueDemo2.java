package com.chenxushao.java.collection.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class PriorityQueueDemo2 {

	public static void main(String[] args) {

		// 优先队列自然排序示例
		Queue<Integer> integerPriorityQueue = new PriorityQueue<Integer>(7);
		Random rand = new Random();
		for (int i = 0; i < 7; i++) {
			integerPriorityQueue.add(new Integer(rand.nextInt(100)));
		}
		for (int i = 0; i < 7; i++) {
			Integer in = integerPriorityQueue.poll();
			System.out.println("Processing Integer:" + in);
		}

		// 优先队列使用示例
		Queue <Customer> customerPriorityQueue = new PriorityQueue<Customer>(7, idComparator);
		addDataToQueue(customerPriorityQueue);

		pollDataFromQueue(customerPriorityQueue);

	}

	// 匿名Comparator实现
	public static Comparator<Customer> idComparator = new Comparator<Customer>() {

		@Override
		public int compare(Customer c1, Customer c2) {
			return (int) (c1.getId() - c2.getId());
		}
	};

	// 用于往队列增加数据的通用方法
	private static void addDataToQueue(Queue<Customer> customerPriorityQueue) {
		Random rand = new Random();
		for (int i = 0; i < 7; i++) {
			int id = rand.nextInt(100);
			customerPriorityQueue.add(new Customer(id, "Pankaj " + id));
		}
	}

	// 用于从队列取数据的通用方法
	private static void pollDataFromQueue(Queue<Customer> customerPriorityQueue) {
		while (true) {
			Customer cust = customerPriorityQueue.poll();
			if (cust == null)
				break;
			System.out.println("Processing Customer with ID=" + cust.getId());
		}
	}

}
/**
 * 可能的输出结果

Processing Integer:9
Processing Integer:16
Processing Integer:18
Processing Integer:25
Processing Integer:33
Processing Integer:75
Processing Integer:77
Processing Customer with ID=6
Processing Customer with ID=20
Processing Customer with ID=24
Processing Customer with ID=28
Processing Customer with ID=29
Processing Customer with ID=82
Processing Customer with ID=96
 */