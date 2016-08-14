package com.cuser.rs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cuser.dao.DAOException;
import com.cuser.model.User;
import com.cuser.utils.JdbcUtils;


//可流动结果集的测试
public class ScrollerResultSetTest {

	public static void main(String[] args) {
		scorll();
		 
		 
		 
	}
	
	
	
	
	public static void scorll() {
		List<User> userList = new ArrayList<User>(); 
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement("select id,name,password,birthday from register_table",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User userTemp = new User();
				userTemp.setId(rs.getInt("id"));
				userTemp.setName(rs.getString("name"));
				userTemp.setPassword(rs.getString("password"));
				userTemp.setBirthday(rs.getDate("birthday"));
				userList.add(userTemp);
			}
			
			if ( rs.previous() ){
				User userTemp = new User();
				userTemp.setId(rs.getInt("id"));
				userTemp.setName(rs.getString("name"));
				userTemp.setPassword(rs.getString("password"));
				userTemp.setBirthday(rs.getDate("birthday"));
				System.out.println(userTemp);
			}
			
			if ( rs.absolute(2)){
	
					User userTemp = new User();
					userTemp.setId(rs.getInt("id"));
					userTemp.setName(rs.getString("name"));
					userTemp.setPassword(rs.getString("password"));
					userTemp.setBirthday(rs.getDate("birthday"));
					System.out.println(userTemp);
				
				
			}
			
			
			rs.afterLast();//到最后一行的后一行去
			rs.beforeFirst();//到第一行的前面
			
			if ( rs.next()){
				
				User userTemp = new User();
				userTemp.setId(rs.getInt("id"));
				userTemp.setName(rs.getString("name"));
				userTemp.setPassword(rs.getString("password"));
				userTemp.setBirthday(rs.getDate("birthday"));
				System.out.println(userTemp);
		}
			System.out.println("当前行：" + rs.getRow());
			
			rs.last();
			
			System.out.println("当前行：" + rs.getRow());
			System.out.println();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);// 处理不了的异常要抛出
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}

	}
	

}
