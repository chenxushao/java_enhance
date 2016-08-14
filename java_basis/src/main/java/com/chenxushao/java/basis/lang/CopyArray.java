package com.chenxushao.java.basis.lang;

public class CopyArray {

	  public static void main(String[] a) {
	    int[] array1 = { 1, 2, 3, 4 };
	    int[] array2 = new int[array1.length];
	    System.arraycopy(array1, 0, array2, 0, array1.length);

	    for(int i: array2){
	      
	      System.out.println(i);
	    }
	  }
	}
