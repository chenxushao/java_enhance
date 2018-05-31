package com.chenxushao.java.thread.basis;

/**
 * @author CHENXUSHAO
 *
 */
public class ThreadIsAliveDemo {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner1());
		Thread t2 = new Thread(new Runner2());
		t1.start();
		t2.start();

		System.out.println("t1:" + t1.isAlive());// 线程是否活着
		System.out.println("t2:" + t2.isAlive());

		System.out.println(Thread.currentThread());

		for (int i = 0; i < 10; i++) {
			System.out.println("Main Thread: " + i);
		}
	}
}

class Runner1 implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner1: " + i);
		}
	}
}

class Runner2 implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner2: " + i);
		}
	}
}