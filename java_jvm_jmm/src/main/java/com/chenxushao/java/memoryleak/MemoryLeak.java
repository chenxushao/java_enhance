package com.chenxushao.java.memoryleak;

import java.util.Vector;

import com.chenxushao.java.model.User;

/*
 * Java内存泄漏测试
 * JVM垃圾回收测试
 * 打印垃圾回收器执行情况的VM参数
 * -verbose:gc -XX:+PrintGCDetails
 */
public class MemoryLeak {
	
	public static void main(String[] args) {
		Vector<User> vector = new Vector<User>();
		for(int i=0; i<10000; i++){
			User user = new User(String.valueOf(i));
			vector.add(user);
			user = null;//此时所有的user都不会被释放，因为vector引用这些对象，导致了内存泄漏。
		}
		 vector.clear();
		/*
		 * 正确的最简单的办法是将vector置为null
		 * eg:
		 * vector.clear();
		 * vector = null;
		 */
	}
}
