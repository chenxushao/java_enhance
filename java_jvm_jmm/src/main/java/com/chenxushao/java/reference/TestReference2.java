package com.chenxushao.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/*
 * 弱引用
 */
public class TestReference2 {

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

		// 取消"Hello"的强引用
		str = null;

		// 假如"Hello"对象没被回收，str1引用"Hello"对象
		String str1 = weakReference.get();
		System.out.println(str1);
		// 假如"Hello"对象没被回收，rq.poll返回null
		System.out.println(rq.poll());

	}

}
