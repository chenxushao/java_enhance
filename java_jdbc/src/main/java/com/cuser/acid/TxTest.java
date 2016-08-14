package com.cuser.acid;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cuser.utils.JdbcUtils;

public class TxTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args)   {
		try {
			test();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//模拟银行卡转钱的例子：事务
	static void test() throws SQLException{
       Connection conn = null;
	   Statement pstmt = null;
	   ResultSet rs = null;
	   double money = 0.0;
	    try{
   		conn = JdbcUtils.getConnection();
   		conn.setAutoCommit(false);
   		
   		pstmt = conn.createStatement();
   		System.out.println("进行转账之前：");
	    rs = pstmt.executeQuery("select money from acid_table where id=1");
   		
   	    if ( rs.next()){
   	    	money = rs.getDouble("money");
   	    	System.out.println("张三的钱：" + money);
   	    }
     	rs = pstmt.executeQuery("select money from acid_table where id=2");
     	if ( rs.next()){
   	    	money = rs.getDouble("money");
   	    	System.out.println("李四的钱：" + money);
   	    }
   		
   		//转账
   	    pstmt.executeUpdate("update acid_table set money=money-10 where id=1");//将张三卡上的钱转10元至李四卡上
   	    
   	    
   	   
     	
    	
     	
     	
   	    if ( money > 800){
   	    	throw new RuntimeException("李四账户上的钱大于800元，不能再加钱了.");
   	        
   	    }
   	 
   	    pstmt.executeUpdate("update acid_table set money=money+10 where id=2");//往李四账户上加10元
   	    
   	    
   	    conn.commit();
   	    
   	 System.out.println("进行转帐之后：");
	    
  	rs = pstmt.executeQuery("select money from acid_table where id=1");
		
		
	    if ( rs.next()){
	    	money = rs.getDouble("money");
	    	System.out.println("张三的钱：" + money);
	    }  
   	 rs = pstmt.executeQuery("select money from acid_table where id=2");
  	if ( rs.next()){
	    	money = rs.getDouble("money");
	    	System.out.println("李四的钱：" + money);
	    }
   	    
	    }catch (SQLException e) {
			if ( conn != null){
				conn.rollback();
			}
			throw e;
		}finally{
	    	System.out.println("finally");
	    	JdbcUtils.close(rs, pstmt, conn);
	    }
   		
	    System.out.println("finish");
	}
	
	
}
