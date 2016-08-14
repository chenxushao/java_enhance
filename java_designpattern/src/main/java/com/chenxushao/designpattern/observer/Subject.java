package com.chenxushao.designpattern.observer;

public interface Subject {

	//调用这个方法登记一个新的观察者对象
	public void add(Observer observer);
	
	
	//调用这个方法删除一个已经登记过的的观察者对象
	public void remove(Observer observer);
	
	//调用这个方法通知所有登记过的观察者对象
	public void notifyObserves();
}
