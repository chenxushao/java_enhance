package com.chenxushao.java.basis.classloader;

public class Personx {

	private String name;

	public Personx(){
		this.name = "cuser";//default value
		System.err.println("Person is load by: " + this.getClass().getClassLoader());
	}
	
	public Personx(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
