package com.chenxushao.java.jvm.basis;
import java.util.Vector;


public class maxHeapTest {
    public static void main(String[] args){
    	Vector v = new Vector();
    	for(int i=0;i<=10;i++){
    		byte[] b = new byte[1024*1024];
    		v.add(b);
    		System.out.println(i+"M is allocated");
    	}
    	System.out.println("Max memory:"+Runtime.getRuntime().maxMemory());
    }
}
