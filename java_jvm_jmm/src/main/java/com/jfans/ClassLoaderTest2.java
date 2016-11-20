package com.jfans;


public class ClassLoaderTest2 {
	public static void main(String[] args) throws ClassNotFoundException {
		
		
		//扩展类加载器
		ClassLoader ccl = ClassLoader.getSystemClassLoader().getParent();
		
	    Class<?> clazz1 = ccl.loadClass("java.lang.String");
	    System.out.println(clazz1 + " ---> " + clazz1.getClassLoader());
	    
	    //将ClassLoaderTest2.class拷贝至%JDK_HOME%\jre6\lib\ext目录下(包括包结构),仍然可被扩展类加载器加载
	    //令人感到奇怪的是，必须将含包结构的文件夹必须放到ext目录下的某一个子目录下时才会加载
	    Class<?> clazz2 = ccl.loadClass("com.jfans.ClassLoaderTest2");
	    System.out.println(clazz2 + " ---> " + clazz2.getClassLoader());
	    Class<?> clazz3 = ccl.loadClass("sun.net.spi.nameservice.dns.DNSNameService");
	    System.out.println(clazz3 + " ---> " + clazz3.getClassLoader());
		 
	}

}
