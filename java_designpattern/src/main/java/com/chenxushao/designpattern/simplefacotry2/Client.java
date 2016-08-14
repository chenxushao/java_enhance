package com.chenxushao.designpattern.simplefacotry2;

public class Client {

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
