package com.chenxushao.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import com.chenxushao.java.Grocery;

/*
 * 软引用，弱引用，虚引用的综合测试
 */
public class References {

	// 引用队列
	private static ReferenceQueue<Grocery> rq = new ReferenceQueue<Grocery>();

	// 从队列中取出一个引用
	public static void checkQueue() {
		Reference<? extends Grocery> inq = rq.poll();// 从队列中取出一个引用

		if (inq != null) {
			System.out.println("In queue: " + inq + " : " + inq.get());
		}
	}

	public static void main(String[] args) {
		final int size = 10;

		// 创建10个Grocery对象及10个软引用
		Set<SoftReference<Grocery>> sa = new HashSet<SoftReference<Grocery>>();
		for (int i = 0; i < size; i++) {
			SoftReference<Grocery> ref = new SoftReference<Grocery>(
					new Grocery("Soft " + i), rq);
			System.out.println("Just created : " + ref.get());
			sa.add(ref);// 加入set
		}

		// 启动垃圾回收
		System.gc();
		checkQueue();

		System.out
				.println("---------------------------------------------------------");

		// 创建10个Grocery对象及10个弱引用
		Set<WeakReference<Grocery>> wa = new HashSet<WeakReference<Grocery>>();
		for (int i = 0; i < size; i++) {
			WeakReference<Grocery> ref = new WeakReference<Grocery>(
					new Grocery("Weak " + i), rq);
			System.out.println("Just created : " + ref.get());
			wa.add(ref);// 加入set
		}

		// 启动垃圾回收
		System.gc();
		checkQueue();

		System.out.println("---------------------------------------------------------");

		// 创建10个Grocery对象及10个虚引用
		Set<PhantomReference<Grocery>> pa = new HashSet<PhantomReference<Grocery>>();
		for (int i = 0; i < size; i++) {
			PhantomReference<Grocery> ref = new PhantomReference<Grocery>(
					new Grocery("phantom " + i), rq);
			System.out.println("Just created : " + ref.get());
			pa.add(ref);// 加入set
		}

		// 启动垃圾回收
		System.gc();
		checkQueue();

		/**
		 * 本例依次创建了10个软引用，10个弱引用，10个虚引用，它们各自引用一个Grocery对象。从程序运行
		 * 结果可以看出，虚引用形同虚设，它所引用的对象随时可能被垃圾回收器回收，具有弱引用的对象拥有
		 * 稍微长的生命周期，当垃圾回收器执行回收操作时，有可能被垃圾回收器回收，具有软引用的对象拥有较
		 * 长的生命周期，但在Java虚拟机认为内存不足的情况下，也会被垃圾回收器回收。
		 */
	}
}
