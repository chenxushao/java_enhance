package com.chenxushao.java.basis.classloader;

public class MyClassLoaderTest {

	public static void main(String[] args) {

		MyClassLoader loader1 = new MyClassLoader("loader1");
		loader1.setPath("D:/myapp/serverlib/");

		MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
		loader2.setPath("D:/myapp/clientlib/");

		MyClassLoader loader3 = new MyClassLoader(null, "loader3");
		loader3.setPath("D:/myapp/otherlib/");

		test(loader1);
		test(loader2);
		test(loader3);

	}

	private static void test(ClassLoader classLoader) {
		try {
			Class personClass = classLoader.loadClass("com.jfans.Person");
			Object obj = personClass.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
