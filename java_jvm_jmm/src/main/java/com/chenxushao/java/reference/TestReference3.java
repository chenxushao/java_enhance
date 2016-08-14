package com.chenxushao.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/*
 * 软引用
 */
public class TestReference3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 //创建一个强引用
		 String str = new String("Hello");
		 
		 //创建引用队列
		 ReferenceQueue<String> rq = new ReferenceQueue<String>();
		 
		 //创建一个弱引用，它引用"Hello"对象，并与rq引用队列关联
		 WeakReference<String> weakReference = new WeakReference<String>(str,rq);
		 
		 //取消"Hello"的强引用
		 str = null;
		
		  
		 
		 System.out.println("垃圾回收器开始工作后...");
		 //两次催促垃圾回收器工作(调用System.gc()),提高"Hello"对象被回收的可能性
		 System.gc();
		 System.gc();
		 
		 String str1 = weakReference.get();
		 System.out.println(str1);
		 //假如"Hello"对象没被回收，rq.poll返回null
		 System.out.println(rq.poll());//poll方法返回Reference的引用
	}

}
