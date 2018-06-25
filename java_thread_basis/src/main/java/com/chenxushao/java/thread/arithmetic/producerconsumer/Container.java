package com.chenxushao.java.thread.arithmetic.producerconsumer;

import java.util.ArrayList;
import java.util.List;

public class Container {

	List<Integer> products = new ArrayList<Integer>();

	public synchronized int get() {
		// 因为是一直等待，所以要用while
		while (size() == 0) {
			System.out.println(Thread.currentThread().getName() + "在等待");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("消费者可以取产品了");
		int retVar = products.remove(size() - 1);
		System.out.println(Thread.currentThread().getName()
				+ " get ++++++++++++++++++++++++++++++++++++" + retVar);
		// System.out.println("消费者取到产品了");
		this.notifyAll();
		return retVar;

	}

	public synchronized void put(int product) {
		while (size() > 0) {
			System.out.println(Thread.currentThread().getName() + "在等待");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("生产者可以生产了");
		products.add(Integer.valueOf(product));
		System.out.println(Thread.currentThread().getName()
				+ " put ++++++++++++++++++++++++++++++++++++" + product);
		// System.out.println("生产者生成完成了");
		this.notifyAll();

	}

	public synchronized int size() {
		return products.size();
	}
}
