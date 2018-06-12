package com.chenxushao.java.thread.basis.task;

/**
 * @author CHENXUSHAO 线程仅能启动一次(start)
 */
public class Client {

	public static void main(String[] args) {
		Thread t = new Thread(new RunnableTask(), "task1");// 要为每一个线程设置有意义的名字
		t.start();// 启动用start
		// t.start();//仅能启动一次
	}

}
