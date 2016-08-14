package com.cuser.dao;

import com.cuser.model.User;

public class UserService {

	private UserDao userDao;
	
	public void regist(User user){
		try{
		userDao.addUser(user);
		}catch(DAOException e ){
			e.printStackTrace();
			//todo
			//sendMail()//发电子邮件
		}
	}
}
