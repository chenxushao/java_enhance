package com.chenxushao.java.thread.basis;

import com.chenxushao.java.thread.basis.implthread.Task1;

public class StartThread {

	public static void main(String[] args) {
		 Thread t = new Thread(new Task1(), "task1");//要为每一个线程设置有意义的名字
		 t.start();//启动用start
//		 t.start();//仅能启动一次
//		 t.run();//not run
	}

}
