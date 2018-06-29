package com.chenxushao.java.concurrent.juc.atomic.counter;

public class SafeCounter3Demo {
	
	public static void main(String[] args) throws InterruptedException {
		
		SafeCounter3 safeCounter3 = new SafeCounter3();
		
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<10_000;i++){
					safeCounter3.increment();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<10_000;i++){
					safeCounter3.increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(safeCounter3.getCount());
	}
}
