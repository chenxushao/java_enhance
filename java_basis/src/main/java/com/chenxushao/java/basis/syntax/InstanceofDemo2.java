package com.chenxushao.java.basis.syntax;

public class InstanceofDemo2 {
	public static void main(String[] args) {
		String s = null;
		System.out.println(null instanceof String);
		//System.out.println(null instanceof Object);
		System.out.println(s instanceof String);//false
		//System.out.println(s instanceof Person);//编译出错，不位于继承树的同一个分支上。
		 
		Boolean b = false;  
		String str = "foo";  
		b = ( str instanceof String );   // true
		System.out.println(b);
		b = ( str instanceof Object );   // also true
		System.out.println(b);
		//b = ( str instanceof Date );     // 编译出错，不位于继承树的同一个分支上。

	}
}
