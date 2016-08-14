package com.chenxushao.java.basis.exceptions;

public class ExceptionSampl3 {
	
	public static double method(int x){
		System.out.println("method begin");
		int result = 0;
		
		System.out.println("try begin");
		result = 1/x;
		System.out.println("try finish");
		
			System.out.println("catch begin"); 
		/*	System.out.println("Exception Description : " + e.getMessage());*/
			System.out.println("catch finsh");
			
			
		
			System.out.println("finally中的代码一定会执行.");
			
		
		System.out.println("方法后面的代码");
		return result;
	}

	
	public static void main(String[] args){
		double rtnVar = method(0);
		System.out.println("rtnVar: " + rtnVar);
		 //System.out.println(method(1));
		 /*System.out.println(method(0));*/
	}
}
