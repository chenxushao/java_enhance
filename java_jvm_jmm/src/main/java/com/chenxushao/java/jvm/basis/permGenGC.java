package com.chenxushao.java.jvm.basis;


public class permGenGC {
     public static void main(String[] args){
    	 for(int i=0;i<Integer.MAX_VALUE;i++){
    		 String t = String.valueOf(i).intern();//加入常量池
    	 }
     }
}
