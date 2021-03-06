package com.chenxushao.java.concurrent.arithmetic.producerconsumer;

//消费者,理应是一个线程，不停地从产品容器中去取产品
public class Customer implements Runnable {

	private Container container;

	public Customer(Container container) {
		this.container = container;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 30; i++) {
			// 取产品
			container.get();
		}
	}

}
