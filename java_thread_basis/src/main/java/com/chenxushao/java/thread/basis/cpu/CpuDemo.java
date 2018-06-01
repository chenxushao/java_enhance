package com.chenxushao.java.thread.basis.cpu;

public class CpuDemo {

	public static void main(String[] args) {
		int NCPU = Runtime.getRuntime().availableProcessors();
		System.out.println(NCPU);
	}
}
