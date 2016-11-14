package com.chenxushao.java.juc.synchronizer.countdownlatch;

public class PrintDemo {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new PrintTask(1),"Print1");
		Thread t2 = new Thread(new PrintTask(2),"Print2");
		Thread t3 = new Thread(new PrintTask(3),"Print3");
		
		t1.start();
		t2.start();
		t3.start();
	}

}
