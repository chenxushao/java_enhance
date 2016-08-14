package com.chenxushao.java.basis.string;

public class String10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String a = "a";
		String b = "b";
		String c = "c";
		
		String s = a + b + c;
		
		
		StringBuffer sb = new StringBuffer();
	    sb.append(a).append(b).append(c);
	    
	    String s2 = sb.toString();
	    
	    System.out.println(s.equals(s2));

	}

}
