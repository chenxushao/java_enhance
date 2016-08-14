package com.chenxushao.java.basis.syntax;

import java.util.Enumeration;
import java.util.Properties;


//所有属性
public class SystemAllProperty {


	public static void main(String[] args) {
		   Properties p = System.getProperties();
	       
	       Enumeration<Object> keys = p.keys();
	       
	       while(keys.hasMoreElements()){
	    	   String key = (String) keys.nextElement();
	    	   System.out.println(key + ": " + p.getProperty(key));
	       }
	}

}
