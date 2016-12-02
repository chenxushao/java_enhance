package com.chenxushao.designpattern.observer;

public class RealObserver implements Observer {

	private String name;

	public RealObserver(String name) {
		this.name = name;
	}

	public void update() {

		System.out.println(this.name + " 改变了!");

	}

}
