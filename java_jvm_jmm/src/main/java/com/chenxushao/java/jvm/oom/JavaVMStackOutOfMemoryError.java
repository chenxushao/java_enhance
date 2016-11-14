package com.chenxushao.java.jvm.oom;

/*
 * VM参数:-Xss2M
 * 
 * 抛出如下异常：
 *  Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
	at java.lang.Thread.start0(Native Method)
	at java.lang.Thread.start(Thread.java:597)
	at com.jfans.JavaVMStackOutOfMemoryError.stackLeakByThread(JavaVMStackOutOfMemoryError.java:27)
	at com.jfans.JavaVMStackOutOfMemoryError.main(JavaVMStackOutOfMemoryError.java:13)
   
 */
public class JavaVMStackOutOfMemoryError {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 JavaVMStackOutOfMemoryError jvsme = new JavaVMStackOutOfMemoryError();
		 jvsme.stackLeakByThread();
	}

	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable(){
				public void run() {
					 dontStop();
				}
			});
			thread.start();
		}
	}
	
	private void dontStop(){
		 for(int i=0; i<1000; i++){
		 }
	}
}
