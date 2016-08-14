package com.cuser.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.cuser.dao.DAOException;
import com.cuser.model.User;
import com.cuser.utils.JdbcUtils;


//结果集的元数据
public class ResultSetMetaDataTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sql = "select id,name,password,birthday from register_table";
		getAllUser(sql);
	}

	
	public static Map<String,User> getAllUser(String sql) {
		Map<String, User> userMap = new HashMap<String,User>(); 
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int count = resultSetMetaData.getColumnCount();
			String[] colNames = new String[count];
			
			
			for ( int i=1; i<=count; i++){
				/*System.out.print(resultSetMetaData.getColumnClassName(i) + "  ");
				System.out.print(resultSetMetaData.getColumnName(i) + "  ");
				System.out.println(resultSetMetaData.getColumnLabel(i) + "  ");*///别名
                colNames[i] = resultSetMetaData.getColumnName(i);
			}
			
			Map<String,User> data  = null;
			while (rs.next()) {
				data = new HashMap<String, User>();
				User userTemp = new User();
				userTemp.setName(rs.getString("name"));
				userTemp.setPassword(rs.getString("password"));
				userTemp.setBirthday(rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);// 处理不了的异常要抛出
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return null;
	}
}
