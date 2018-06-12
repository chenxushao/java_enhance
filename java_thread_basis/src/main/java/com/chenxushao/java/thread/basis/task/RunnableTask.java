package com.chenxushao.java.thread.basis.task;

//实现java.lang.Runnable接口，没有返回值的任务
public class RunnableTask implements Runnable {

	public void run() {
		System.out.println("run");
	}

}
