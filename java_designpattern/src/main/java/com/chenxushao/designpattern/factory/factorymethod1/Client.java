package com.chenxushao.designpattern.factory.factorymethod1;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Factory factory = new ConcreteFactory2();
		 
		 System.out.println(factory.factory());
	}

}
