package com.chenxushao.java.basis.classloader;

public class ClassLoaderTest3 {
	public static void main(String[] args) {
		String sunBootClassPath = System.getProperty("sun.boot.class.path");
		System.out.println("sunBootClassPath: " + sunBootClassPath);
	    String javaExtDirs = System.getProperty("java.ext.dirs");
	    System.out.println("extDir: " + javaExtDirs);
	    String javaClassPath = System.getProperty("java.class.path");
	    System.out.println("javaClassPath: " + javaClassPath);
	    System.out.println(java.lang.ClassLoader.class.getClassLoader());
		 
	}

}
