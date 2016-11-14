package com.chenxushao.java.juc.countdownlatch;

public class PrintTask implements Runnable {

	private int number;
	
	public  PrintTask(int number) {
		 this.number = number;
	}
	
	@Override
	public void run() {
		 for(;;){
	          System.out.println(Thread.currentThread().getName() + "---" + number);
		 }
	}

}
