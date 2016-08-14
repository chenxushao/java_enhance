package com.cuser.model;

import java.util.Date;

public class User {
      private int id;
      private String name;
      private String password;
      private Date birthday;
      
      
	public User() {
	
	}
	




	public User(String name, String password, Date birthday) {
	           this.name = name;
	           this.password = password;
	           this.birthday = birthday;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}










	public Date getBirthday() {
		return birthday;
	}





	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}





	@Override
	public String toString() {
		return this.id+"  "+this.name+ "  "+this.password + "  " + this.birthday;
	}
      
      
}
