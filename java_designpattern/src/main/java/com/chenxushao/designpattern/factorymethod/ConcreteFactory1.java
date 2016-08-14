package com.chenxushao.designpattern.factorymethod;

public class ConcreteFactory1 extends Factory {

	@Override
	public Product factory() {
		// TODO Auto-generated method stub
		return new ConcreteProduct1();
	}

}
