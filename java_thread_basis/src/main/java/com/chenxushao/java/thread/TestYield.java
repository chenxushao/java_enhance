package com.chenxushao.java.thread;

public class TestYield {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 MyThread3 t1 = new MyThread3("t1");
		 MyThread3 t2 = new MyThread3("t2");
		 t1.start();
		 t2.start();
		 try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 

	}

}


class MyThread3 extends Thread{
    public MyThread3(String name){
    	super(name);
    }
	public void run() {
		for (int i=0; i<=100; i++){
			System.out.println("I am "+this.getName()+": "+i);
			if(i%10 == 0)
			yield();
		}
	}
}