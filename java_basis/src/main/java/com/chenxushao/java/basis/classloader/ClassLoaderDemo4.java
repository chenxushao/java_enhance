package com.chenxushao.java.basis.classloader;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class ClassLoaderDemo4 {
	public static void main(String args[]) throws Exception {
		RuntimeMXBean mx = ManagementFactory.getRuntimeMXBean();

		System.out.println(mx.getSystemProperties());
		System.out.println(mx.getBootClassPath());
		System.out.println(mx.getClassPath());
		System.out.println(mx.getUptime() + " ms");
	}
}