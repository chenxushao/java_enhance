package com.chenxushao.designpattern.factory.simplefacotry;

/*
 * 作者：cuser
 * 版本：1.0
 * 名称：FruitGardener
 * 功能：简单工厂模式的核心类(水果园丁)
 * 
 */
public class FruitGardener {
	 public static Fruit factory(String type) throws BadFruitException{
		 
		 if (type.equalsIgnoreCase("apple"))
			 return new Apple();
		 else if (type.equalsIgnoreCase("grape"))
			 return new Grape();
		 else if (type.equalsIgnoreCase("strawberry"))
			 return new Strawberry();
		 else if (type.equalsIgnoreCase("banana"))
			 return new Banana();
		 else{
			 throw new BadFruitException("Bad Fruit Request.");
		 }
	 }
}
