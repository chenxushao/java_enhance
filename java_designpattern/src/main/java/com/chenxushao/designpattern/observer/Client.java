package com.chenxushao.designpattern.observer;

public class Client {

	public static void main(String[] args) {
	     Observer o1 = new RealObserver("cxs");
	     Observer o2 = new RealObserver("wgang");
	     Observer o3 = new RealObserver("pc");
	     Observer o4 = new RealObserver("wky");
	     Observer o5 = new RealObserver("kzq");
	     
	     Subject subject = new RealSubject();
	     subject.add(o1);
	     subject.add(o2);
	     subject.add(o3);
	     
	     subject.notifyObserves();
	     subject.remove(o1);
	     subject.add(o5);
	     System.out.println();
	     subject.notifyObserves();
	}

}
