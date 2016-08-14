package com.cuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cuser.dao.DAOException;
import com.cuser.dao.UserDao;
import com.cuser.model.User;
import com.cuser.utils.JdbcUtils;

public class UserDaoJdbcImpl implements UserDao {

	public void addUser(User user)  {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
		
		conn = JdbcUtils.getConnection();
		pstmt = conn.prepareStatement("insert into register_table(name,password,birthday)values(?,?,?)");
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getPassword());
		pstmt.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
		pstmt.executeUpdate();
		
		}catch(SQLException e){
			throw new DAOException(e.getMessage(),e);//处理不了的异常要抛出
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	public void delete(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
		
		conn = JdbcUtils.getConnection();
		pstmt = conn.prepareStatement("delete from register_table where id=?");
		pstmt.setInt(1, user.getId());
		pstmt.executeUpdate();
		
		}catch(SQLException e){
			throw new DAOException(e.getMessage(),e);//处理不了的异常要抛出
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	public User findUserById(int userId) {
           User user = null;
           Connection conn = null;
   		   PreparedStatement pstmt = null;
   		   ResultSet rs = null;
   		   try{
	   		
	   		conn = JdbcUtils.getConnection();
	   		pstmt = conn.prepareStatement("select id,name,password,birthday from register_table where id=?");
	   		pstmt.setInt(1,userId);
	   		rs = pstmt.executeQuery();
	   		
	   		if (rs.next()){
	   			user = new User();
	   			user.setId(rs.getInt("id"));
	   			user.setName(rs.getString("name"));
	   			user.setPassword(rs.getString("password"));
	   			user.setBirthday(rs.getDate("birthday"));
	   		}
	   		}catch(SQLException e){
	   			throw new DAOException(e.getMessage(),e);//处理不了的异常要抛出
	   		}finally{
	   			JdbcUtils.close(rs, pstmt, conn);
	   	   }
          
	   		return user;
	}

	public User findUserByName(String name) {
		   User user = null;
           Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   try{
	   		
	   		conn = JdbcUtils.getConnection();
	   		pstmt = conn.prepareStatement("select id,name,password,birthday from register_table where name=?");
	   		pstmt.setString(1, name);
	   		rs = pstmt.executeQuery();
	   		
	   		if (rs.next()){
	   			user = new User();
	   			user.setId(rs.getInt("id"));
	   			user.setName(rs.getString("name"));
	   			user.setPassword(rs.getString("password"));
	   			user.setBirthday(rs.getDate("birthday"));
	   		}
	   		}catch(SQLException e){
	   			throw new DAOException(e.getMessage(),e);//处理不了的异常要抛出
	   		}finally{
	   			JdbcUtils.close(rs, pstmt, conn);
	   	   }
       
	   		return user;
	}

	public List<User> getAllUser() {
		List<User> userList = new ArrayList<User>(); 
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement("select id,name,password,birthday from register_table");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User userTemp = new User();
				userTemp.setId(rs.getInt("id"));
				userTemp.setName(rs.getString("name"));
				userTemp.setPassword(rs.getString("password"));
				userTemp.setBirthday(rs.getDate("birthday"));
				userList.add(userTemp);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);// 处理不了的异常要抛出
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}

		return userList;
	}

	

	public void update(User user) {
		
           Connection conn = null;
		   PreparedStatement pstmt = null;
		   
		   try{
	   		
	   		conn = JdbcUtils.getConnection();
	   		pstmt = conn.prepareStatement("update register_table set name=?,password=?,birthday=? where id=?");
	   		pstmt.setString(1, user.getName());
	   		pstmt.setString(2, user.getPassword());
	   		pstmt.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
	   	 
	   		pstmt.setInt(4, user.getId());
	   		int i = pstmt.executeUpdate();
	   		System.out.println("updae i: " + i);
		   }catch(SQLException e){
			   throw new DAOException(e.getMessage(),e);
		   }finally{
			   JdbcUtils.close(null, pstmt, conn);
		   }
	}

}
