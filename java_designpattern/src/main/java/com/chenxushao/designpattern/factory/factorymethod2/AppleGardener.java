package com.chenxushao.designpattern.factory.factorymethod2;

/*
 * 作者：cuser
 * 版本：1.0
 * 名称：AppleGardener
 * 功能：具体工厂类(苹果园丁)
 * 
 */
public class AppleGardener implements FruitGardener {
	/*
	 * 工厂方法
	 */
	public Fruit factory() {
		return new Apple();
	}

}
