package com.cuser.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//用单例模式设计的jdbc工具类
public class JdbcUtilsSingle {
	private static JdbcUtilsSingle instance = null;
    
	private String user = null;
	private String password = null;
	private String url = null;
	
	 static{
		   try {
			Class.forName(JdbcConfigure.getInstance().getJdbcInfo().getDriver());
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	 }
	
	private JdbcUtilsSingle(){
		
	}
	
	public static synchronized JdbcUtilsSingle getInstance(){
		if (instance == null)
			instance = new JdbcUtilsSingle();
		return instance;
	}
	
	
	
	 

	
	 
	 
	 
	 public  Connection getConnection() throws SQLException{
		 url = JdbcConfigure.getInstance().getJdbcInfo().getUrl();
		 user = JdbcConfigure.getInstance().getJdbcInfo().getUser();
		 password = JdbcConfigure.getInstance().getJdbcInfo().getPassword();
		 return DriverManager.getConnection(url,user,password);
	 }
	 
	 public void close(ResultSet rs,Statement stmt,Connection conn){
		   if (rs != null){
			   try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		   }
		   
		   if (stmt != null){
			   try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    stmt = null;
		   }
		   
		   if (conn != null){
			    try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = null;
		   }
	 }
	 

}
