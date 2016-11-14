package com.chenxushao.java.jvm.oom;


public class StackOverflowErrorTest {

	public static void main(String[] args) {
		 new Thread(new Runnable(){
			public void run() {
				loop(0);
			}
			 private void loop(int i){
				   if(i != 80000){
					   i++;
					   loop(i);
					   System.out.println("loop" + i);
				   }
			   }
		 }
		 ).start();
	}
}
