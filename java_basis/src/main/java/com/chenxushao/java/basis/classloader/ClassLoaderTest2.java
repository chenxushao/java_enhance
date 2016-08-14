package com.chenxushao.java.basis.classloader;

public class ClassLoaderTest2 {
	public static void main(String[] args) {
	  
		ClassLoader loader = ClassLoader.getSystemClassLoader();//返回系统类加载器
		System.out.println(loader);
		
		 
	}

}
