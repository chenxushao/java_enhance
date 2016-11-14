package com.chenxushao.java.thread.basis;

public class TestPriopity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread4 t1 = new MyThread4("t1");
		MyThread4 t2 = new MyThread4("----t2----");
		t1.setPriority(Thread.NORM_PRIORITY+5);
		t1.start();
		t2.start();
	}
}


class MyThread4 extends Thread{
    public MyThread4(String name){
    	super(name);
    }
	public void run() {
		for (int i=0; i<=10; i++){
			System.out.println("I am "+this.getName()+": "+i);
		}
		}
}