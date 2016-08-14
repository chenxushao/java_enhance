package com.chenxushao.designpattern.singleton2;
 
public class SingleIton {
	private static SingleIton instance = new SingleIton();
	

	 private SingleIton(){}
	 
	 
	 public static SingleIton getInstance(){
		 return instance;
	 }
	 
	 
}
