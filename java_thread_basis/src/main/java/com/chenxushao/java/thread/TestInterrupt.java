package com.chenxushao.java.thread;

import java.util.Date;

public class TestInterrupt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread mt = new MyThread();
		mt.start();
		
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		mt.interrupt();
	}

}

class MyThread extends Thread{

	@Override
	public void run() {
		 while(true){
			  System.out.println("--------"+new Date()+"---------");
			  try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		 }
	}
	 
	
}