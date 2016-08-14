package com.chenxushao.designpattern.simplefacotry2;
/*
 * 作者：cuser
 * 版本：1.0
 * 名称：Apple
 * 功能：具体产品类Apple(苹果)
*/
public class Apple implements Fruit {
    private int treeAge;//树龄
	public void grow() {
		 System.out.println("Apple is growing...");

	}

	public void harvest() {
		System.out.println("Apple has been harvested.");

	}

	public void plant() {
		System.out.println("Apple has been planted.");

	}

	public int getTreeAge() {
		return treeAge;
	}

	public void setTreeAge(int treeAge) {
		this.treeAge = treeAge;
	}
	
	

}
