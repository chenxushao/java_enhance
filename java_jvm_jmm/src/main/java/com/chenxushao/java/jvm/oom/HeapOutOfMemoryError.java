package com.chenxushao.java.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/*
 * Java堆内存溢出异常测试
 * 当在JVM的堆中创建大量的对象时而堆内存又分配不足时就抛出OutOfMemoryError异常
 * VM参数: -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOutOfMemoryError {
	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		while (true) {
			list.add(new Object());
		}
	}
}
