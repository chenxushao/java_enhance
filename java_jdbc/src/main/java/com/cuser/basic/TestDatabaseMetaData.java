package com.cuser.basic;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.cuser.utils.JdbcUtils;

//数据库的元数据:对一般写JDBC程序用处不大，但对一些框架的设计,比如hibernate有作用
public class TestDatabaseMetaData {


	public static void main(String[] args) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		DatabaseMetaData databaseMetaData = conn.getMetaData();//获取 DatabaseMetaData 对象，该对象包含关于 Connection 对象连接到的数据库的元数据
		
		
		System.out.println(databaseMetaData.getDatabaseProductName());
		System.out.println(databaseMetaData.getDatabaseProductVersion());
		System.out.println(databaseMetaData.getDriverName());
		System.out.println(databaseMetaData.getDriverVersion());
		System.out.println(databaseMetaData.getUserName());
		System.out.println(databaseMetaData.getURL());
		
		
		System.out.println(databaseMetaData.isReadOnly());
        System.out.println(databaseMetaData.supportsTransactions());//是否支持事务
		

	}

}
