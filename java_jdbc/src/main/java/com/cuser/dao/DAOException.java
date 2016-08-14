package com.cuser.dao;

//DAO异常类
public class DAOException extends RuntimeException{

	
	public DAOException() {
		
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public DAOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
         
}
