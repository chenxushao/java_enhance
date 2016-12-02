package com.chenxushao.java.thread.basis.state;

import java.util.Date;

public class InterruptBlockedTest {

	public static void main(String[] args) {
		Thread6 tt = new Thread6();
		Thread t = new Thread(tt, "t");
		t.start();
		/* t.interrupt(); */

		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		t.interrupt();
		/* tt.stop(); */

		for (int i = 0; i <= 30; i++) {
			System.out.println(new Date() + "线程t的状态" + t.getState() + "----"
					+ i);
			/*
			 * try { Thread.sleep(500); } catch (InterruptedException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}
	}

}

class Thread6 implements Runnable {

	private boolean flag = true;

	public void run() {
		while (flag) {
			System.out.println("run");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void stop() {
		this.flag = false;
	}
}