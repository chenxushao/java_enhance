package com.jfans;

public class ClassLoaderTest2 {
	public static void main(String[] args) throws ClassNotFoundException {

		// ��չ�������
		ClassLoader ccl = ClassLoader.getSystemClassLoader().getParent();

		Class<?> clazz1 = ccl.loadClass("java.lang.String");
		System.out.println(clazz1 + " ---> " + clazz1.getClassLoader());

		// ��ClassLoaderTest2.class������%JDK_HOME%\jre6\lib\extĿ¼��(�������ṹ),��Ȼ�ɱ���չ�����������
		// ���˸е���ֵ��ǣ����뽫�����ṹ���ļ��б���ŵ�extĿ¼�µ�ĳһ����Ŀ¼��ʱ�Ż����
		Class<?> clazz2 = ccl.loadClass("com.jfans.ClassLoaderTest2");
		System.out.println(clazz2 + " ---> " + clazz2.getClassLoader());
		Class<?> clazz3 = ccl
				.loadClass("sun.net.spi.nameservice.dns.DNSNameService");
		System.out.println(clazz3 + " ---> " + clazz3.getClassLoader());

	}

}
