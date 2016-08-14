package com.chenxushao.designpattern.builder;

public class Director {
	
	Builder builder;
	
	public Director(Builder builder){
		this.builder = builder;
	}
	
	public void constructor(){
		builder.buildPart1();
		builder.buildPart2();
		builder.getResult();
	
	}

}
