package com.cuser.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cuser.model.User;

//通过DAOFactory获取相应的DAO实现类
public class UserDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//增加
		User user = new User();
		user.setName("林峰");
		user.setPassword("MingXin");
		user.setBirthday(new Date());
		
		
		/*UserDao userDao = newstate UserDaoJdbcImpl();*/
		UserDao userDao = DAOFactory.getInstance().getUserDao();
		/*userDao.addUser(user);*/
		
		
		
		List<User> users = userDao.getAllUser();
		
		Iterator<User> userIt = users.iterator();
		while(userIt.hasNext()){
			User tempUser = userIt.next();
			System.out.println(tempUser);
		}
		
		
		//查找
		User userSearchByName = userDao.findUserByName("林峰");
		
		
		
		if (userSearchByName==null){
			System.out.println("\n未查找到符合条件的User");
		   
		}else{
		System.out.println("\n查找到的User: " + userSearchByName);
		}
		//删除
		/*userDao.delete(userSearchByName);*/
		System.out.println("\n删除后的结果:");
		
        users = userDao.getAllUser();
		
		userIt = users.iterator();
		while(userIt.hasNext()){
			User tempUser = userIt.next();
			System.out.println(tempUser);
		}
	}

}
