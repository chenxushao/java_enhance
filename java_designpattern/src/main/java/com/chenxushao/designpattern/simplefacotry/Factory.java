package com.chenxushao.designpattern.simplefacotry;

public class Factory {

	   public Product factory(){
		   return new ConcreteProduct();
	   }
}
