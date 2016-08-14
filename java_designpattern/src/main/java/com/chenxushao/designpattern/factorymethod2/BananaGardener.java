package com.chenxushao.designpattern.factorymethod2;

public class BananaGardener implements FruitGardener {

	public Fruit factory() {
	       return new Banana();
	}

}
