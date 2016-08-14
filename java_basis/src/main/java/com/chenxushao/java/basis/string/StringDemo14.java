package com.chenxushao.java.basis.string;

public class StringDemo14 {
	
	public static void main(String[] args) {
		int len  = 12;
		String s = new String("Hello,Wrold!");
		
		String a  = "Hello,Wrold!的长度:12";
		String b  = "Hello" + ",Wrold" + "!" + "的长度:" + 12 ;
		String c  = "Hello" + ",Wrold" + "!" + "的长度:" + "Hello,Wrold!".length() ;
		String d  = "Hello" + ",Wrold" + "!" + "的长度:" + len ;
		String e  = "Hello" + ",Wrold" + "!" + "的长度:" + s.length();
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println("d: " + d);
		System.out.println(a == b);//true:在编译时期就能确定b的值
		System.out.println(a == c);//false:因为c中包含了方法调用,因此不能在编译时确定
		System.out.println(a == d);//false:因为c中包含了变量，因此不能在编译时确定
		
		System.out.println(a==e);
		System.out.println(d == e);
	}

}
