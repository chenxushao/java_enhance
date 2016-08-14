package com.chenxushao.designpattern.simplefacotry;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Factory factory = new Factory();
		 
		 System.out.println(factory.factory());
	}

}
