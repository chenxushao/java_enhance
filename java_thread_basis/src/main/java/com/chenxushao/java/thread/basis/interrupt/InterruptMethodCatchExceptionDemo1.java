package com.chenxushao.java.thread.basis.interrupt;


/**
 * 抛出InterruptedException后，会清除中断标志，isInterrupted()会返回false
 */
public class InterruptMethodCatchExceptionDemo1 {
	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		System.out.println("Point A: t.isInterrupted()=" + t.isInterrupted());
		// 中断自身
		t.interrupt();
		System.out.println("Point B: t.isInterrupted()=" + t.isInterrupted());
		System.out.println("Point C: t.isInterrupted()=" + t.isInterrupted());

		try {
			Thread.sleep(2000);
			System.out.println("was NOT interrupted");
		} catch (InterruptedException x) {
			System.out.println("was interrupted");
		}
		// 抛出异常后，会清除中断标志，这里会返回false
		System.out.println("Point D: t.isInterrupted()=" + t.isInterrupted());
	}
}
