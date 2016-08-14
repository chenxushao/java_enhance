package com.cuser.dao;

import java.io.InputStream;
import java.util.Properties;

//DAO工厂类
public class DAOFactory 
{
	  private static UserDao userDao = null;
      private static DAOFactory instance = new DAOFactory();
      
      
      private DAOFactory(){
    	  try {
    		  Properties prop = new Properties();
    		  //指定文件路径的方式之一，默认根目录为当前项目
        	  //InputStream inStream = new FileInputStream(new File("src/daoconfig.properties"));
        	  //方式二，通过类加载器来加载文件，相比上面一种方式而言，灵活性好一些
    		  InputStream inStream = DAOFactory.class.getClassLoader().getResourceAsStream("daoconfig.properties");
    		  prop.load(inStream);
			  String userDaoClass = prop.getProperty("userDaoClass");
			  
			  userDao = (UserDao) Class.forName(userDaoClass).newInstance();
			
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
    	  
      }
      
      public static DAOFactory getInstance(){
    	   return instance;
      }

	public UserDao getUserDao() {
		return userDao;
	}
}
