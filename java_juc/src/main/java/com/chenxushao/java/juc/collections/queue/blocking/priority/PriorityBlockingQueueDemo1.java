package com.chenxushao.java.juc.collections.queue.blocking.priority;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo1 {
	
	
	public static void main(String[] args) throws Exception {
		BlockingQueue<String> queue   = new PriorityBlockingQueue<String>();
	    //String implements java.lang.Comparable
	    queue.put("Value");
	
	    String value = queue.take();
	}
}

