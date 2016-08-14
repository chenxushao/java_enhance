package com.cuser.dao;

import java.util.List;

import com.cuser.model.User;

public  interface UserDao {
	public void addUser(User user);
	public User findUserById(int userId);
	public User findUserByName(String name);
	public void update(User user);
	public void delete(User user);
	public List<User> getAllUser();

}
