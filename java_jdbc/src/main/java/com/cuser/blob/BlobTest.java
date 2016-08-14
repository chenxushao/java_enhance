package com.cuser.blob;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cuser.utils.JdbcUtils;


//大的二进制串：如图片，jar包等
public class BlobTest {
	  private final String FILE_PATH_NAME = "src/temp/182515491_0.jpg";
	  
	  private static String getCurrentDate(){
		  DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		  return sdf.format(new Date());
		  
	  } 
	  public void create() throws SQLException, IOException{
		   /* Connection conn = JdbcUtils.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("insert into blob_table(big_bit) values(?)");
		    File file = new File(FILE_PATH_NAME);
		    InputStream is = new BufferedInputStream(new FileInputStream(file));
		    pstmt.setBinaryStream(1, is, (int) file.length());
		    pstmt.executeUpdate();
		    is.close();
		    JdbcUtils.close(null, pstmt, conn);*/
		  
		  
		  Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				// 2.建立连接
				conn = JdbcUtils.getConnection();
				// conn = JdbcUtilsSing.getInstance().getConnection();
				// 3.创建语句
				String sql = "insert into blob_table(big_bit) values (?) ";
				ps = conn.prepareStatement(sql);
				File file = new File("src/temp/182515491_0.jpg");
				
				InputStream in = new BufferedInputStream(new FileInputStream(file));
				
				ps.setBinaryStream(1, in, (int) file.length());
				System.out.println("abc");
				// 4.执行语句
				int i = ps.executeUpdate();

				in.close();

				System.out.println("i=" + i);
			} finally {
				JdbcUtils.close(rs, ps, conn);
			}
		}

	  
	  public static void read() throws SQLException, IOException{
		    Connection conn = JdbcUtils.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("select big_text from clob_table");
		    ResultSet rs = pstmt.executeQuery();
		    
		    while(rs.next()){
		    	Clob clob = rs.getClob("big_text");
		    	Reader reader = clob.getCharacterStream();
		    	File file = new File("src/temp/"+getCurrentDate()+".bak");
		    	Writer writer = new FileWriter(file);
		    	char[] buff = new char[1024];
		    	for (int i=0; (i=reader.read(buff))>0;){
		    		   writer.write(buff, 0, i);
		    	}
		    	reader.close();
		    	writer.close();
		    }
		    JdbcUtils.close(rs, pstmt, conn);
	  }
	  
	  public static void main(String[] args) throws SQLException, IOException{
		    BlobTest bt = new BlobTest();
		    bt.create();
		    //ct.read();
	  }

}
