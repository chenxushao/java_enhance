package com.chenxushao.java.jvm;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {

	public static void main(String[] args) {
		 System.out.println("hello,world!");
		 
		 List<Object> objs = new ArrayList<Object>();
		 for(int i=0; i<1024;i++){
			 objs.add(new Object());
		 }
		 
		 
		 while(true){
			 System.out.println("hi");
			 try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}

}
