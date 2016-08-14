package com.chenxushao.java.basis.string;

public class StringTest {

	public static void main(String[] args) {
		 String str = "Hello,World!";
		 
		 System.out.println(str.indexOf('l'));
		 System.out.println(str.lastIndexOf('l'));
		 System.out.println(str.indexOf('l', 6));
		 System.out.println(str.indexOf("llo"));
		 System.out.println(str.indexOf("lo"));
		 System.out.println(str.indexOf("wo"));
		 System.out.println(str.indexOf("Wo"));
	}

}
