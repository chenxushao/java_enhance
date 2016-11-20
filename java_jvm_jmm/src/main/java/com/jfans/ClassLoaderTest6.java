package com.jfans;



public class ClassLoaderTest6 {

	public static void main(String[] args) throws ClassNotFoundException {
		 
		CustomClassLoader ccl = new CustomClassLoader("",null);
		ccl.scanPath("e:/classes");
		System.out.println("ccl parent: " + ccl.getParent());
		Class<?> clazz = ccl.loadClass("com.jfans.ClassLoaderTest3");
		System.out.println(clazz + " ---> " + clazz.getClassLoader());
	}
}
