package com.chenxushao.java.concurrent.basis.interrupts.interrupted;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * 使用Thread.interrupted（）返回当前线程的中断状态，同时清除中断标识，注意是当前线程.
 */
public class InterruptedMethodDemo2 {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Task(), "t");
		t.start();

		t.interrupt();

		//当前线程是main线程，从未中断过，当然输出false
		System.out.println("是否interrupt 1：" + t.interrupted());
		System.out.println("是否interrupt 2：" + t.interrupted());

	}


	private static class Task implements Runnable {

		@Override
		public void run() {
			for (int i = 1; i <= 2; i++) {
				System.out.println(i + ": in running");
				ThreadUtil.sleep(5, TimeUnit.SECONDS);

			}
		}
	}
}
