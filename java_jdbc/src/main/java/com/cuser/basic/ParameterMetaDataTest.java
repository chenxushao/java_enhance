package com.cuser.basic;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cuser.model.User;
import com.cuser.utils.JdbcUtils;


//参数的元数据信息：对于编写灵活的JDBC有作用，但不同的数据库并不一定能够识别出来，可能是未实现,目前这个版本的MySQL未实现,都是返回的VARCHAR类型
//PreparedStatement才有
//目前这个版本的如果不在数据库url中添加"?generateSimpleParameterMetadata=true"的话，会报异常
//这样的代码，可读性并不好，但灵活性非常高
public class ParameterMetaDataTest {


	public static void main(String[] args) {
		try{
	     read("select * from register_table where id>?",new Object[]{1});
		}catch(Exception e){
			e.printStackTrace();
		}
	}  

	
	public static void read(String sql,Object[] params) {
        User user = null;
        Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   try{
	   		
	   		conn = JdbcUtils.getConnection();
	   		pstmt = conn.prepareStatement(sql);
	   		ParameterMetaData pmd = pstmt.getParameterMetaData();//获取参数元数据信息
	   		int count = pmd.getParameterCount();
	   		System.out.println("count: " + count);
	   		try{
	   		for ( int i=1; i<=count; i++){
	   			/*System.out.print(pmd.getParameterClassName(i) + "  ");
	   			System.out.print(pmd.getParameterType(i) + "  ");
	   			System.out.println(pmd.getParameterTypeName(i) + "  ");*/
	   			
	   			pstmt.setObject(i,params[i-1]);
	   			
	   		}
	   		}catch(Exception e){
	   			e.printStackTrace();
	   		}
	   		rs = pstmt.executeQuery();
	   		
	   		while (rs.next()){
	   			user = new User();
	   			user.setId(rs.getInt("id"));
	   			user.setName(rs.getString("name"));
	   			user.setPassword(rs.getString("password"));
	   			user.setBirthday(rs.getDate("birthday"));
	   			System.out.println(user);
	   		}
	   		}catch(SQLException e){
	   			System.out.println(e.getMessage());
	   		}finally{
	   			JdbcUtils.close(rs, pstmt, conn);
	   	   }
       
	   		
	}
}
