package com.cuser.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//该类一个实例都不会产生:Jdbc工具类
public final class JdbcUtils {
	 private static String user = null;
	 private static String password = null;
	 private static String url = null;

	 //静态代码块:只会被加载一次
	 static{
		   try {
			Class.forName(JdbcConfigure.getInstance().getJdbcInfo().getDriver());
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);//异常要抛出
		}
	 }
	 
	 private  JdbcUtils(){
	 }
	 
	 public static Connection getConnection() throws SQLException{
		 url = JdbcConfigure.getInstance().getJdbcInfo().getUrl();
		 user = JdbcConfigure.getInstance().getJdbcInfo().getUser();
		 password = JdbcConfigure.getInstance().getJdbcInfo().getPassword();
		 return DriverManager.getConnection(url,user,password);
	 }
	 
	 //释放资源:可以空值进去
	 public static void close(ResultSet rs,Statement stmt,Connection conn){
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
	 
	 public static void main(String[] args){
		
		 System.out.println("Hello,Jdbc");
		 
	 }
}
