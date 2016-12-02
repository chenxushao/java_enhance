package com.chenxushao.designpattern.factory.simplefacotry;

public class Client2 {

	/**
	 * @param args
	 * @throws BadFruitException
	 */
	public static void main(String[] args) throws BadFruitException {
		Fruit apple = FruitGardener.factory("apple");
		apple.grow();
		apple.harvest();
		apple.plant();

	}

}
