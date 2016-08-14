package com.chenxushao.java.basis;

//返回零数组
public class Return0ArraySample {

	   public static int[] method(){
		   final int[] array = new int[0];
		   final int[] arr = new int[100];
		   int x = 100;
		   if(x<=100)
			   return array;
		   else
		   return arr;
	   }
	   
	   public static void main(String[] args){
		      int [] array = method();
		      if (array.length == 0){
		    	  System.out.println("length==0");
		      }
	   }
}
