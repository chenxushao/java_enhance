package com.jfans;



public class ClassLoaderTest4 {

	public static void main(String[] args) throws ClassNotFoundException {
	
//		 Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader().getParent());
		  Thread t = new Thread(){
			  
			  
			 class Trade{} 
			  
			@Override
			public void run() {
				Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader().getParent());
				
				 System.out.println(Thread.currentThread().getContextClassLoader());
			     System.out.println("ClassLoaderTest4: " + ClassLoaderTest4.class.getClassLoader());
			     System.out.println("Trade:" + Trade.class.getClassLoader());
			     try {
					System.out.println(Thread.currentThread().getContextClassLoader().loadClass("sun.net.spi.nameservice.dns.DNSNameService"));
					System.out.println(Thread.currentThread().getContextClassLoader().loadClass("com.jfans.ClassLoaderTest4"));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		  };
		  t.setContextClassLoader(ClassLoader.getSystemClassLoader().getParent());
		  t.start();
	}
}
