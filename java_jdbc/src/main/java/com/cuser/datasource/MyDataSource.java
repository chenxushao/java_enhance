package com.cuser.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

//数据库连接池
public class MyDataSource
{
	 private static String user = null;
	 private static String password = null;
	 private static String url = null;
	 LinkedList<Connection> connectionsPool = new LinkedList<Connection>();//连接池
        
        public MyDataSource(){
        	try{
        	for (int i=0; i<10; i++){
        		connectionsPool.addLast(this.createConnection());
        	}}catch(SQLException e){
        		throw new ExceptionInInitializerError(e);//抛出初始化错误
        	}
        }
        
        public Connection getConnection(){
        	return connectionsPool.removeFirst();//移除并返回列表的第一个元素，先入先出
        }
        
        public void free(Connection conn){
        	connectionsPool.addLast(conn);//加在连接池的尾部
        }
       
        private Connection createConnection() throws SQLException{
        	return DriverManager.getConnection(url,user,password);
        }
        
}
