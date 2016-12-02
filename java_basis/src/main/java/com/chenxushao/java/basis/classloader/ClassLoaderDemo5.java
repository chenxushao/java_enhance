package com.chenxushao.java.basis.classloader;

public class ClassLoaderDemo5 {
	public static void main(String[] args) {
		System.out.println(String.class.getClassLoader());// 根(引导)类加载器
		System.out.println(Object.class.getClassLoader());// 根(引导)类加载器
		System.out.println(Person.class.getClassLoader());// 系统类加载器,自定义的Person类

		System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class
				.getClassLoader());
		ClassLoader loader = ClassLoader.getSystemClassLoader();// 返回系统类加载器(在虚拟机启动时就会产生)
		System.out.println(loader);
	}

}
