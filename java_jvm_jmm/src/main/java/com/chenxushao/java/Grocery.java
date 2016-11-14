package com.chenxushao.java;

public class Grocery {
	private static final int SIZE = 10000;
	private double[] d = new double[SIZE];
	private String id;
	
	public Grocery(String id){
		this.id = id;
	}
	
	@Override
	public String toString(){
		return id;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Finalizing: " + id);
	} 
}
