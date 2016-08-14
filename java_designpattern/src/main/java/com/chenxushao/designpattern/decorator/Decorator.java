package com.chenxushao.designpattern.decorator;


public class Decorator implements Component {
	
	Component component;
	
	public Decorator(Component component){
		this.component = component;
	}

	@Override
	public void operate() {
		 component.operate();
	}
	
	
	

}
