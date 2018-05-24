package com.chenxushao.java.thread.basis.state;

import java.util.Date;

public class SleepMethodInvokeTest {

	public static void main(String[] args) {
		Thread4 tt = new Thread4();
		Thread t = new Thread(tt, "t");
		t.start();

		for (int i = 0; i < 30; i++) {
			System.out.println(new Date() + "线程t的状态" + t.getState() + "----"
					+ i);
		}
	}

}

class Thread4 implements Runnable {

	public void run() {
		while (true) {
			System.out.println("run");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}