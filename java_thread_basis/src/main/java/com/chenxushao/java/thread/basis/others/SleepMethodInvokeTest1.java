package com.chenxushao.java.thread.basis.others;

import java.util.Date;

public class SleepMethodInvokeTest1 {

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

	@Override
	public void run() {
		while (true) {
			System.out.println("run");
			try {
				Thread.sleep(2000*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}