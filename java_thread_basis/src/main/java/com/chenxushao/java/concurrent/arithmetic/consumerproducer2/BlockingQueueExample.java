package com.chenxushao.java.concurrent.arithmetic.consumerproducer2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author CHENXUSHAO 使用阻塞队列实现生产者消费者
 */
public class BlockingQueueExample {

	public static void main(String[] args) throws Exception {

		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(4000);
	}
}