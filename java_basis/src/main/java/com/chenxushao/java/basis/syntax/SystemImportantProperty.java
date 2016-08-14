package com.chenxushao.java.basis.syntax;


//几个重要的属性
public class SystemImportantProperty {


	public static void main(String[] args) {
		   //操作系统名称
	       System.out.println("os.name: " + System.getProperty("os.name"));
	       //操作系统架构
	       System.out.println("os.arch: " + System.getProperty("os.arch"));
	       //操作系统版本
	       System.out.println("os.version: " + System.getProperty("os.version"));
	       
	       
	       //用户主目录
	       System.out.println("user.home: " + System.getProperty("user.home"));
	       //用户的帐户名称
	       System.out.println("user.name: " + System.getProperty("user.name"));
	      //程序当前工作目录
	       System.out.println("user.dir:" + System.getProperty("user.dir"));
	       
	       
	       //文件分隔符,windows系统为"\",unix系统为"/"
	       System.out.println("file.separator:" + System.getProperty("file.separator"));
	       
	      //路径分隔符,windows系统为";",unix系统为":"
	       System.out.println("path.separator:" + System.getProperty("path.separator"));
	       
	       
	       //行分隔符,windows系统为"\r\n",unix系统为"/n"
	       System.out.println("line.separator:" + System.getProperty("line.separator"));
	       
	        
	       //
	       //Java安装目录
	       System.out.println("java.home:  " + System.getProperties().getProperty("java.home"));
	       //jdk版本
	       System.out.println("java.version:  " + System.getProperty("java.version"));
	        
	       //Java类路径
	       System.out.println("java.class.path:  " + System.getProperty("java.class.path"));
	       
	         
	       //加载库时搜索的路径
	       System.out.println("java.library.path:  " + System.getProperty("java.library.path"));
	       
	       
	       //一个或式个扩展目录的路径
	       System.out.println("java.ext.dirs:  " + System.getProperty("java.ext.dirs"));
	       

	       //默认的临时文件路径
	       System.out.println("java.io.tmpdir:  " + System.getProperty("java.io.tmpdir"));
	       
	         
	      //文件编码
	       System.out.println("file.encoding:  " + System.getProperty("file.encoding"));
	       
	}

}
