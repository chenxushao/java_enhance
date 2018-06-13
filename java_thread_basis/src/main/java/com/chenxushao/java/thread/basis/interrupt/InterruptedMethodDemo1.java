package com.chenxushao.java.thread.basis.interrupt;

/**
 * 使用Thread.interrupted（）返回当前线程的中断状态，同时清除中断标识
 */
public class InterruptedMethodDemo1 {
	public static void main(String[] args) {
		System.out.println("Point X: Thread.interrupted()="
				+ Thread.interrupted());
		Thread.currentThread().interrupt();
		//
		System.out.println("Point Y: Thread.interrupted()="
				+ Thread.interrupted());
		System.out.println("Point Z: Thread.interrupted()="
				+ Thread.interrupted());
	}
}
