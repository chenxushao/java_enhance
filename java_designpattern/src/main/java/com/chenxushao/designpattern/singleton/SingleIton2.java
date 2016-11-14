package com.chenxushao.designpattern.singleton;
 
public class SingleIton2 {
	
	private static SingleIton2 instance = new SingleIton2();
	
	private SingleIton2(){}
	 
	 public static SingleIton2 getInstance(){
		 return instance;
	 }
}
