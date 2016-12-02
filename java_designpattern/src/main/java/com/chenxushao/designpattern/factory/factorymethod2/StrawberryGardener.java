package com.chenxushao.designpattern.factory.factorymethod2;

/*
 * 作者：cuser
 * 版本：1.0
 * 名称：StrawberryGardener
 * 功能：具体工厂类(草莓园丁)
 * 
 */
public class StrawberryGardener implements FruitGardener {
	/*
	 * 工厂方法
	 */
	public Fruit factory() {
		return new Strawberry();
	}

}
