package com.chenxushao.java.basis.classloader;

public class ClassLoaderDemo3 {

	public static void main(String[] args) {
		String sunBootClassPath = System.getProperty("sun.boot.class.path");
		System.out.println("sunBootClassPath: " + sunBootClassPath);

		String javaExtDirs = System.getProperty("java.ext.dirs");
		System.out.println("javaExtDirs: " + javaExtDirs);

		String javaClassPath = System.getProperty("java.class.path");
		System.out.println("javaClassPath: " + javaClassPath);
	}
}
