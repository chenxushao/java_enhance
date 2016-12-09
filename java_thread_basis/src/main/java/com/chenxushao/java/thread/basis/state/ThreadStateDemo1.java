package com.chenxushao.java.thread.basis.state;

//线程的状态：生命周期
/*
 * New:新建状态
 * Runnable:运行状态
 * TERMINATED:终止，结束
 * TERMINATED
 * */
public class ThreadStateDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread1();
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("finish");
		System.out.println("State: " + t1.getState().toString());
	}

}

class Thread1 extends Thread {

	@Override
	public void run() {

		for (int i = 0; i < 15; i++) {
			System.out.println("true");
			System.out.println("run..." + " ---" + this.getState().toString());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void start() {
		System.out.println("start..." + " ---" + this.getState().toString());
		super.start();
	}

}