package com.jfans;



public class ClassLoaderTest5 {

	public static void main(String[] args) throws ClassNotFoundException {
		MyClassLoader ccl1 = new MyClassLoader();
		Class<?> clazz = ccl1.loadClass("com.jfans.ClassLoaderTest4");
		System.out.println(clazz + "  ---> " + clazz.getClassLoader());
		
		System.out.println(clazz.getName());
		
	}
}
