package com.chenxushao.java.basis.classloader;

public class ClassLoaderDemo2 {

	public static void main(String[] args) {
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println("systemClassLoader: " + systemClassLoader);

		System.out.println(systemClassLoader.getParent());

		System.out.println(systemClassLoader.getParent().getParent());
	}
}
