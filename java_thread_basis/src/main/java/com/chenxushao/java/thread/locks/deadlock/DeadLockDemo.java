package com.chenxushao.java.thread.locks.deadlock;

public class DeadLockDemo {
	

	private static String a ="A";
	private static String b ="B";
	
	public static void main(String[] args) {
		deadLock();
	}



	private static void deadLock(){
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				 synchronized (a) {
					 try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(b){
						System.out.println("1");
					}
				}
			}
		});
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				 synchronized (b) {
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					synchronized (a) {
						System.out.println("2");
					}
				}
			}
		});
		
		t2.start();
	}
}
