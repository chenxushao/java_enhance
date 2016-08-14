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

	//ģ�����п�תǮ�����ӣ�����
	static void test() throws SQLException{
       Connection conn = null;
	   Statement pstmt = null;
	   ResultSet rs = null;
	   double money = 0.0;
	    try{
   		conn = JdbcUtils.getConnection();
   		conn.setAutoCommit(false);
   		
   		pstmt = conn.createStatement();
   		System.out.println("����ת��֮ǰ��");
	    rs = pstmt.executeQuery("select money from acid_table where id=1");
   		
   	    if ( rs.next()){
   	    	money = rs.getDouble("money");
   	    	System.out.println("������Ǯ��" + money);
   	    }
     	rs = pstmt.executeQuery("select money from acid_table where id=2");
     	if ( rs.next()){
   	    	money = rs.getDouble("money");
   	    	System.out.println("���ĵ�Ǯ��" + money);
   	    }
   		
   		//ת��
   	    pstmt.executeUpdate("update acid_table set money=money-10 where id=1");//���������ϵ�Ǯת10Ԫ�����Ŀ���
   	    
   	    
   	   
     	
    	
     	
     	
   	    if ( money > 800){
   	    	throw new RuntimeException("�����˻��ϵ�Ǯ����800Ԫ�������ټ�Ǯ��.");
   	        
   	    }
   	 
   	    pstmt.executeUpdate("update acid_table set money=money+10 where id=2");//�������˻��ϼ�10Ԫ
   	    
   	    
   	    conn.commit();
   	    
   	 System.out.println("����ת��֮��");
	    
  	rs = pstmt.executeQuery("select money from acid_table where id=1");
		
		
	    if ( rs.next()){
	    	money = rs.getDouble("money");
	    	System.out.println("������Ǯ��" + money);
	    }  
   	 rs = pstmt.executeQuery("select money from acid_table where id=2");
  	if ( rs.next()){
	    	money = rs.getDouble("money");
	    	System.out.println("���ĵ�Ǯ��" + money);
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
