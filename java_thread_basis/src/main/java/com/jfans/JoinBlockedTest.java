package com.jfans;


import java.util.Date;

public class JoinBlockedTest {

	public static void main(String[] args) {
		Thread5 tt = new Thread5();
		Thread t = new Thread(tt,"t");
        t.start();
        try {
			t.join();
			System.out.println(Thread.currentThread().getName() + " State is : " + Thread.currentThread().getState());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for ( int i=0; i<=30; i++){
    		System.out.println(new Date() + "线程t的状态" + t.getState() + "----" + i);
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
     }

}


class Thread5 implements Runnable{

	public void run() {
		while( true ){
			System.out.println("run");
		}
		
		
	}
	
}