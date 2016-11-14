package com.chenxushao.designpattern.factory.factorymethod2;

/*
 * 作者：cuser
 * 版本：1.0
 * 名称：Grape
 * 功能：具体产品类Grape(葡萄)
*/
public class Grape implements Fruit {
    
	private boolean seedLess;//有无籽
	public void grow() {
		System.out.println("Grape is growing...");

	}

	public void harvest() {
		System.out.println("Grape has been harvested.");

	}

	public void plant() {
		System.out.println("Grape has been planted.");

	}

	public boolean isSeedLess() {
		return seedLess;
	}

	public void setSeedLess(boolean seedLess) {
		this.seedLess = seedLess;
	}
	
	

}
