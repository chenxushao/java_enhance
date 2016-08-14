package com.cuser.dbmd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.cuser.utils.JdbcUtils;


/*
 * 可以返回数据库本身的一些特性，如数据库版本、数据库名称、数据库厂商信息、
 * 是否支持事务、是否支持某种事务隔离级别、是否支持流动结果集、是否支持批量更新等。
 */

//数据库的元数据信息
public class DBMD {

	public static void main(String[] args) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		DatabaseMetaData dbmd = conn.getMetaData();
		System.out.println("db Name: "+dbmd.getDatabaseProductName());
		System.out.println("db Version: "+dbmd.getDatabaseProductVersion());
		System.out.println();
		System.out.println("driver Name: "+dbmd.getDriverName());
		System.out.println("driver Version: "+dbmd.getDriverVersion());
		System.out.println("major Version: "+dbmd.getDriverMajorVersion());
		System.out.println("minor Version: "+dbmd.getDriverMinorVersion());
		
		System.out.println("tx: "+dbmd.supportsTransactions());
		conn.close();
	}

}
