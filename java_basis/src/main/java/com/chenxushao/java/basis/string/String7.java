package com.chenxushao.java.basis.string;

public class String7 {
	public static void main(String[] args) {
		String a = "abc";

		String b = "abc";
        
		System.out.println("====>" + a == b);//false  //因为涉及变量(a)的+,且a不为final型变量，无法在编译期确定其值
		
		System.out.println("" + a == b);//false 

	}
}
