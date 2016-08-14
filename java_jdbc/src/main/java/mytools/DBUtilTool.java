package mytools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtilTool
{    
	//关闭Connection
	public static void closeConnection(Connection conn)
	{
		  if ( conn != null)
		  {
			   try
			   {
				   conn.close();
			   }catch(SQLException e){
				   e.printStackTrace();
			   }
			   conn = null;
		  }
    }
  
   //关闭Statement
  public static void closeStatement(Statement stmt)
  {
	   if ( stmt != null)
	   {
		   try {
			stmt.cancel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stmt=null;
	   }
  }
  
   //关闭ResultSet
  public static void closeResultSet(ResultSet rs)
  {
	     if ( rs != null)
	     {
	    	  try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs=null;
	     }
}
   //关闭PreparedStatement
    public static void closePreparedStatement(PreparedStatement pstmt)
    {
    	  if ( pstmt != null)
    	  {
    		  try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		  pstmt=null;
    	  }
    }
    
    //Load DriverClass
    /*
     jdbc-odbc:"sun.jdbc.odbc.JdbcOdbcDriver"
     mysql:"com.mysql.jdbc.Driver"
     SQL Server:"com.microsoft.jdbc.sqlserver.SQLServerDriver"
     */
    public static void init(String DriverClass)
    {
    	  try {
			Class.forName(DriverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //建立与数据库的连接(URL)
    /*
        jdbc-odbc url="jdbc:odbc:datasource_name"
        mysql url="jdbc:mysql://localhost:3306/database_name";
        SQL Server url="jdbc:microsoft:sqlserver://localhost:1433;databasename=pubs"
     */
    public static void createConnection(String url,String user,String password)
    {
    	  try {
			Connection conn=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //other
    public static void other(Connection conn) throws SQLException
    {
        //创建可滚动的结果集。
		Statement stmt=conn.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
    }


	//获取一个数据库连接,仅以mysql数据库为例
	public static Connection getConnection(String url,String user,String password)
	{    
		Connection conn=null;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
		 
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		 return conn;
	}

}
