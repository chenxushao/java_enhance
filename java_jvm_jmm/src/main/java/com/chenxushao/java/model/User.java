package com.chenxushao.java.model;

public class User {
	
	private String id;
	public User(String id){
		this.id  = id;
	}

	@Override
	protected void finalize() throws Throwable {
		 System.out.println("Finalizing User " +id);
	}

	@Override
	public String toString() {
		 return "User " + this.id;
	}
	
	
	
}
