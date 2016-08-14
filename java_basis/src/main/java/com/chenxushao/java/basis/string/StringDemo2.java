package com.chenxushao.java.basis.string;

public class StringDemo2 {
  
	 static String s ="a";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 String s1 = "a";
		 String s2 = "abc";
		 String s3 = "a" + "bc";
		 String s4 = "a" + "b" + "c";
		 String s5 = s1 + "bc";
		 final  String s6 = "a";
		 String s7 = s6 + "bc";
		 String s8 = s + "bc";
		 
		 System.out.println(s2==s3); 
		 System.out.println(s2==s4); 
         System.out.println(s3==s4);  
         System.out.println(s3==s5); 
         System.out.println("s3==s7? " + (s3==s7)); 
         System.out.println("s3==s8? " + (s3==s8)); 
         
         
         System.out.println(s2.equals(s3));//true
         System.out.println(s2.equals(s4));//true
         System.out.println(s3.equals(s4));//true
		 
         
	}

}
