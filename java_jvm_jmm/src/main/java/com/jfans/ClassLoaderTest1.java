package com.jfans;

import sun.net.spi.nameservice.dns.DNSNameService;

public class ClassLoaderTest1 {
	public static void main(String[] args) {

		// ϵͳ�������
		ClassLoader ccl = ClassLoader.getSystemClassLoader();
		System.out.println("ϵͳ�������: " + ccl);

		// �߳��������������,Ĭ�������Ϊϵͳ�������
		System.out.println("�߳��������������: "
				+ Thread.currentThread().getContextClassLoader());
		System.out.println("ClassLoaderTest1 classloader: "
				+ ClassLoaderTest1.class.getClassLoader());// ϵͳ�������������classpath
		System.out.println("String classloader: "
				+ String.class.getClassLoader());// bootstrap
													// classloader,���غ������rt.jar
		System.out.println("DNSNameService classloader: "
				+ DNSNameService.class.getClassLoader());// ��չ�������

	}

}
