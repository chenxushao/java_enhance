package com.chenxushao.java.thread.basis.state;

//线程的状态：生命周期
/*
 * New:新建状态
 * Runnable:运行状态
 * TERMINATED:终止，结束
 * TIMED_WAITING：等待
 * */
public class ThreadTest3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread2("线程1");
		t1.start();

		System.out.println("t1 State: " + t1.getState());
		/*
		 * try { t1.join(); System.out.println("调用其它线程的join方法后： " +
		 * Thread.currentThread().getName() + " Thread state:  " +
		 * Thread.currentThread().getState().toString()); } catch
		 * (InterruptedException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()
					+ " Thread state:  "
					+ Thread.currentThread().getState().toString());
			try {
				System.out.println("-t1 State: " + t1.getState());
				Thread.sleep(500);
				System.out.println("--t1 State: " + t1.getState());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("---State of t1: " + t1.getState().toString());
	}

}

class Thread2 extends Thread {

	public Thread2(String name) {
		super(name);
		System.out.println("构造方法中： " + Thread.currentThread().getName()
				+ " Thread state:  "
				+ Thread.currentThread().getState().toString());
	}

	@Override
	public void run() {

		System.out.println("run开始处： " + Thread.currentThread().getName()
				+ " Thread state:  "
				+ Thread.currentThread().getState().toString());
		for (int i = 0; i < 10; i++) {
			/* System.out.println("true"); */
			System.out.println(Thread.currentThread().getName() + "---run..."
					+ " ---" + this.getState().toString());
			try {
				Thread.sleep(1000);
				System.out.println("调用sleep方法后："
						+ Thread.currentThread().getName() + "---run..."
						+ " ---" + this.getState().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void start() {
		System.out.println(Thread.currentThread().getName() + " Thread: "
				+ "   start..." + " --- State: " + this.getState().toString());
		super.start();
	}

}