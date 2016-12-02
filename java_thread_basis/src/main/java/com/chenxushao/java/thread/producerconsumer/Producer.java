package com.chenxushao.java.thread.producerconsumer;

public class Producer implements Runnable {

	private Container container;

	public Producer(Container container) {
		this.container = container;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 30; i++) {
			// 生产产品
			// System.out.println("开始生产" + i);
			container.put(i);

			// System.out.println("结束生产" + i);
		}
	}

}
