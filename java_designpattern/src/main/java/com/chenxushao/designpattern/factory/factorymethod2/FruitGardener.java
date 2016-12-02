package com.chenxushao.designpattern.factory.factorymethod2;

/*
 * 作者：cuser
 * 版本：1.0
 * 名称：FruitGardener
 * 功能：工厂方法模式的核心类(水果园丁)
 * 
 */
public interface FruitGardener {
	/*
	 * 工厂方法
	 */
	public Fruit factory();
}
