package com.chenxushao.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class RealSubject implements Subject {

	private List<Observer> observers = new ArrayList<Observer>();

	public void add(Observer observer) {
		observers.add(observer);

	}

	public void notifyObserves() {
		for (Observer observer : observers) {
			observer.update();
		}

	}

	public void remove(Observer observer) {
		observers.remove(observer);
	}

}
