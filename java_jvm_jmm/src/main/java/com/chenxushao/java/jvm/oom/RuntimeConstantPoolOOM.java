package com.chenxushao.java.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/*
 * 如果要向运行时常量池(位于方法区Method Area)中添加内容，最简单的做法是使用String.intern()这个Native方法了。
 * 由于常量池分配 在方法区(持久代)，我们可以通过-XX:PermSize和-XX:MaxPermSize限制方法区的大小，从而间接限制其中常量池的容量
 * 
 * VM参数:-XX:PermSize10M -XX:MaxPermSize10M
 * 
 * 抛出如下异常：
 Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
 at java.lang.String.intern(Native Method)
 at com.jfans.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:26)

 从运行结果可以看到，运行时常量池溢出，在OutOfMemoryError后面跟随的提示信息是"PermSize space"，说明运行时常量属于方法区(HotSpot虚拟
 机中的永久代)的一部分。

 另外，还有方法区溢出，本机直接内存溢出等。
 */
public class RuntimeConstantPoolOOM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
