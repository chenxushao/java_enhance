package com.jfans;

public class ClassLoaderTest7 {

	public static void main(String[] args) throws ClassNotFoundException {

		ClassLoader ccl = ClassLoader.getSystemClassLoader();

		Class<?> clazz = ccl.loadClass("com.jfans.ClassLoaderTest7");

		System.out.println(clazz + " --->" + clazz.getClassLoader());

		Trade t = new Trade();
		System.out.println(t.getClass().getClassLoader());

	}

}
