package com.chenxushao.gson;


import java.util.Date;

import com.google.gson.annotations.Expose;

public class Person {

	private String id;
	
	@Expose
	private String name;
	@Expose
	private String pswd;
	@Expose
	private Boolean sex ;
	@Expose
	private int age;
	@Expose
	private Date birth;
	
	Person father;
	
	Person son;
	
	public Person getSon() {
		return son;
	}
	public void setSon(Person son) {
		this.son = son;
	}
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) {
		this.father = father;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
