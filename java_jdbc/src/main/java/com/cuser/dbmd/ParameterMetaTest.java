package com.cuser.dbmd;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cuser.utils.JdbcUtils;

//参数的元数据信息，针对PreparedStatement
public class ParameterMetaTest {

	public static void main(String[] args) throws SQLException {
		  Object[] obj = new Object[]{12};
		  read("select id,name,sex,age from user_table where id=?",obj);
	}

	static void read(String sql,Object[] params) throws SQLException{
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 conn = JdbcUtils.getConnection();
		 pstmt = conn.prepareStatement(sql);
		 
		 ParameterMetaData pmd = pstmt.getParameterMetaData();
		 int count = pmd.getParameterCount();
		
		/* for (int i=1; i<=count; i++){
			 System.out.println(pmd.getParameterClassName(i));
			 System.out.println(pmd.getParameterType(i));
			 System.out.println(pmd.getParameterTypeName(i));
		 }*/
		 
	}
}
