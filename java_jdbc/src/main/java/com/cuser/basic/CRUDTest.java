package com.cuser.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cuser.model.User;
import com.cuser.utils.JdbcUtils;

public class CRUDTest {

	public static void main(String[] args) {
		
		User user = new User("cxs","123456",new Date());
		addUser(user);
		System.out.println(getAllUser());
	}

	
	static void addUser(User user)  {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement("insert into test_table(name,password,birthday)values(?,?,?)");
			
			String name = user.getName();
			String password = user.getPassword();
			Date date = user.getBirthday();
			
			pstmt.setString(1, name);
			pstmt.setString(2,password);
			pstmt.setTimestamp(3, new Timestamp(date.getTime()));
			
		
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
		
		
	}
	
	
	static List<User> getAllUser(){
		List<User> userList = new ArrayList<User>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
		    stmt = conn.createStatement();
	        rs = stmt.executeQuery("select * from test_table");
	        
	        while ( rs.next()){
	        	User tempUser = new User();
	        	tempUser.setId(rs.getInt("id"));
	        	tempUser.setName(rs.getString("name"));
	        	tempUser.setPassword(rs.getString("password"));
	        	tempUser.setBirthday(rs.getTimestamp("birthday"));
	        	userList.add(tempUser);
	        }
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.close(rs, stmt, conn);
		}
		
		return userList;
		
	}
	
	
}
