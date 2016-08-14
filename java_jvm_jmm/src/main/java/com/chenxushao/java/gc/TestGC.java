package com.chenxushao.java.gc;

import com.chenxushao.java.model.User;

/*
 * JVM垃圾回收测试
 * 打印垃圾回收器执行情况的VM参数
 * -verbose:gc -XX:+PrintGCDetails
 */
public class TestGC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 User user = new User("1");
		 
		 user = null;
		 
		 System.gc();
	}

}
