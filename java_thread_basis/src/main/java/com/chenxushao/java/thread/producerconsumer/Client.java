package com.chenxushao.java.thread.producerconsumer;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Container container = new Container();
		 
		 Thread customer1 = new Thread(new Customer(container),"Customer1");
		 Thread customer2 = new Thread(new Customer(container),"Customer2");
		 Thread customer3 = new Thread(new Customer(container),"Customer3");
		   
		 Thread producter1 = new Thread(new Producer(container),"Producter1");
		 Thread producter2 = new Thread(new Producer(container),"Producter2");
		 Thread producter3 = new Thread(new Producer(container),"Producter3");
		 
		 customer1.start();
		 customer2.start();
		 customer3.start();
		 
	 
		 producter1.start();
		 producter2.start();
		 producter3.start();
		 
	}

}
