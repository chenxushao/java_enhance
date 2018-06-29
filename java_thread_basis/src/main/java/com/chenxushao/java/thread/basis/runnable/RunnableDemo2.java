package com.chenxushao.java.thread.basis.runnable;

/**
 * @author CHENXUSHAO
 *
 * 线程仅能启动一次(start)，否则抛出IllegalThreadStateException
 */
public class RunnableDemo2 {

	public static void main(String[] args) {
		Thread t = new Thread(new RunnableTask(), "task1");// 要为每一个线程设置有意义的名字
		t.start();// 启动用start
//		 t.start();//仅能启动一次
	}


	//实现java.lang.Runnable接口，没有返回值的任务
	private static class RunnableTask implements Runnable {

		public void run() {
			System.out.println("run");
		}

	}

}
