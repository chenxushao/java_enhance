package com.chenxushao.designpattern.factory.factorymethod2;

/*
 * 作者：cuser
 * 版本：1.0
 * 名称：Strawberry
 * 功能：具体产品类Strawberry(草莓)
 */
public class Strawberry implements Fruit {

	public void grow() {
		System.out.println("Strawberry is growing...");

	}

	public void harvest() {
		System.out.println("Strawberry has been harvested.");

	}

	public void plant() {
		System.out.println("Strawberry has been planted.");

	}

}
