package com.chenxushao.java.concurrent.juc.blockingqueue.deque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingDequeDemo1 {

	public static void main(String[] args) throws Exception {
		BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

		deque.addFirst("1");
		deque.addLast("2");

		String two = deque.takeLast();
		String one = deque.takeFirst();
	}
}
