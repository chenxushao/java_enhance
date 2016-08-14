package com.chenxushao.designpattern.factorymethod;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Factory factory = new ConcreteFactory2();
		 
		 System.out.println(factory.factory());
	}

}
