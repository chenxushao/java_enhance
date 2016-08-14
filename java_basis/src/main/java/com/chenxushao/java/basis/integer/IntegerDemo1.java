package com.chenxushao.java.basis.integer;

public class IntegerDemo1 {

	public static void main(String[] args) {
		Integer itg1 = new Integer(100);
		Integer itg2 = new Integer(100);
		
		System.out.println(itg1 == itg2);//false  只要是new就会直接在heap中开辟空间
		
		
		//字面值范围在-128-127时，直接从常量池中取
		
		itg1 = -128;//极限值
		itg2 = -128;
		
		System.out.println(itg1 == itg2);//true
          
		
		itg1 =  127;//极限值
		itg2 =  127;
		
		System.out.println(itg1 == itg2);//true
	}

}
