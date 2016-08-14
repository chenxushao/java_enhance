package com.chenxushao.java.thread;

public class ThreadTest {

	public static void main(String[] args) {
	    System.out.println(Thread.MAX_PRIORITY);
		System.out.println(Thread.MIN_PRIORITY);
		System.out.println(Thread.NORM_PRIORITY);
		
		Thread currentThread = Thread.currentThread();
		System.out.println(currentThread);
		System.out.println("id: "+currentThread.getId());
		System.out.println("name: "+currentThread.getName());
		System.out.println("Priority: "+currentThread.getPriority());
		System.out.println("Class: "+currentThread.getClass());
		System.out.println("State: "+currentThread.getState());
		System.out.println("ClassLoader: "+currentThread.getContextClassLoader());
		System.out.println("ThreadGroup: "+currentThread.getThreadGroup());
	}

}
