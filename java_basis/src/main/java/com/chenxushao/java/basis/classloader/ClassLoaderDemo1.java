package com.chenxushao.java.basis.classloader;


public class ClassLoaderDemo1 {

	public static void main(String[] args) {
		System.out.println(String.class.getClassLoader());
		System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
		System.out.println(Personx.class.getClassLoader());
	}
}
