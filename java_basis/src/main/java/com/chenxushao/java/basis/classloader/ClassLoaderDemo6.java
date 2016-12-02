package com.chenxushao.java.basis.classloader;

public class ClassLoaderDemo6 {
	public static void main(String[] args) {

		ClassLoader loader = ClassLoader.getSystemClassLoader();// 返回系统类加载器
		System.out.println(loader);

	}

}
