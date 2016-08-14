package com.chenxushao.java.basis.annonation;


public class Main {

	/** 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args      
	 * @return void
	 * @throws 
	 */

	public static void main(String[] args) {
		   User user = new User();
		   
		   Class clazz = user.getClass();
		   
		   if (clazz.isAnnotationPresent(ExposeField.class)) {
			   System.out.println("yes");
           }else{
        	   System.out.println("no");
           }
	}

}
