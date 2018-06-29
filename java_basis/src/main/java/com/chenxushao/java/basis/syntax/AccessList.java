package com.chenxushao.java.basis.syntax;

import java.util.ArrayList;
import java.util.List;

public class AccessList implements Runnable {
	public List<String> list = new ArrayList<String>();

	@Override
	public void run() {
		while (true) {
			/* synchronized (this) { */
			list.add(String.valueOf(Math.random()));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "---->"
					+ "length: " + list.size());
		}
		/* System.out.println("length: " + cow.size()); */
		/* } */
	}
}
