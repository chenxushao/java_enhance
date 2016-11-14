package com.chenxushao.designpattern.factory.factorymethod2;
/*
 * 作者：cuser
 * 版本：1.0
 * 名称：Fruit
 * 功能：抽象产品(水果)角色
 * 
 */
public interface Fruit {
	/*
	 * 生长
	 */
	public void grow();
	
	/*
	 * 收获
	 */
	public void harvest();
	
	/*
	 * 种植
	 */
	public void plant();

}
