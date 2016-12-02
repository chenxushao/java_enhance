package com.chenxushao.java.gc;

/*
 * VM参数
 * 
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 * 新生代中Eden和Survivor区的比例为8:1，即Eden占8MB，s0和s1各占用1MB
 * 总的堆内存为20M，其中新生代10M,旧生代容量为20M-10M=10M，
 */
public class TestＭinorGC {

	private final static int SIZE_1MB = 1024 * 1024;// 1MB

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] b2, b3, b4;

	}

}
