package com.jfans;

import sun.net.spi.nameservice.dns.DNSNameService;

public class ClassLoaderTest1 {
	public static void main(String[] args) {
		
		
		//系统类加载器
		ClassLoader ccl = ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器: " + ccl);
		
		//线程上下文类加载器,默认情况下为系统类加载器
		System.out.println("线程上下文类加载器: "+Thread.currentThread().getContextClassLoader());
		System.out.println("ClassLoaderTest1 classloader: " + ClassLoaderTest1.class.getClassLoader());//系统类加载器，加载classpath
		System.out.println("String classloader: " + String.class.getClassLoader());//bootstrap classloader,加载核心类库rt.jar
		System.out.println("DNSNameService classloader: " + DNSNameService.class.getClassLoader());//扩展类加载器
	
	
		
	}

}
