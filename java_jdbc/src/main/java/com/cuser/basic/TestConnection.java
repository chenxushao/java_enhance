package com.cuser.basic;

import java.sql.Connection;
import java.sql.SQLException;

import com.cuser.utils.JdbcUtils;

//Connection的基本测试
public class TestConnection {

	public static void main(String[] args) {
		 try {
			Connection conn = JdbcUtils.getConnection();
			System.out.println(conn.getCatalog());//检索此 Connection 对象的当前目录名称
			/*conn.setAutoCommit(true);*///设置自动提交
			System.out.println(conn.getAutoCommit());//检索此 Connection 对象的当前自动提交模式
			System.out.println(conn.isReadOnly());//检索此 Connection 对象是否处于只读模式
			System.out.println(conn.isClosed());//检索此 Connection 对象是否已经被关闭
			System.out.println(conn.getTransactionIsolation());//检索此 Connection 对象的当前事务隔离级别
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
