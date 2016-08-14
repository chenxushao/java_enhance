package com.chenxushao.java.basis.reflect;

public class ClassTest {

	public static void main(String[] args) {
		
		String str = "str";
		
		Class strClass1 = String.class;
		Class strClass2 = str.getClass();
		Class strClass3 = null;
		
		try {
			strClass3 = Class.forName("java.lang.String");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(strClass1.getName());
		System.out.println(strClass1 == strClass2);
		System.out.println(strClass1 == strClass3);
		System.out.println(strClass2 == strClass3);
		
		/*Class intClass = Integer.TYPE;
		System.out.println("intClass: " + intClass.getName());
		System.out.println(int.class == Integer.TYPE);*/
		/*try {
			Class personClass = Class.forName("com.jfans.model.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
