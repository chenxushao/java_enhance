package com.chenxushao.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/*
 * 弱引用
 */
public class TestReference1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建一个强引用
		String str = new String("Hello");

		// 创建引用队列
		ReferenceQueue<String> rq = new ReferenceQueue<String>();

		// 创建一个弱引用，它引用"Hello"对象，并与rq引用队列关联
		WeakReference<String> weakReference = new WeakReference<String>(str, rq);

		/**
		 * 上面的代码中，"hello"对象被str强引用，同时被weakReference弱引用，所以它不会被回收
		 */

	}

}
