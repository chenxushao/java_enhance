package com.chenxushao.java.basis.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class FileUtils {
	
	private static FileSizeDescriptor fileSizeDescriptor = new FileSizeDescriptor();
	
	private FileUtils(){}
	

	public static String getFileSize(File file){
		String tempSize = fileSizeDescriptor.getFileSizeTemp(file);
		return tempSize;
	}
    
	
	
    
	public static String getFileSize(String filePath){
	    File file = new File(filePath);
	    if ( !file.exists()){
	    	throw new RuntimeException("指定路径不存在");
	    }
		String tempSize = fileSizeDescriptor.getFileSizeTemp(file);
		
		return tempSize;
	}
	 
	 
	 
	 public static String getFileLastModified( File file )
		{
			Date date = new Date( file.lastModified() );
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月dd日,HH:mm");
			return sdf.format(date);
		}
	 
	 
	 public static boolean isFileExist( File file, File folder )
		{
			File[] files = folder.listFiles();
			String fileName = file.getName();
			for( File f : files )
			{
				if( fileName.equals( f.getName() ) )
				{
					return true;
				}
			}

			return false;
		}
	 
	 
	 public static boolean isFileExist(String filePath){
		 File file = new File(filePath);
		 return file.exists();
	 }
	 
	 public static void doPaste(File sourceFile,File targetFolder){
		  String sourceFileName = sourceFile.getName();
		  
		  String targetFolderPath = targetFolder.getAbsolutePath();
		  
		  File targetFile = new File(targetFolderPath + File.separator + sourceFileName);
		  BufferedReader br = null;
		  BufferedWriter bw = null;
		  
		  try {
			    br = new BufferedReader(new FileReader(sourceFile));
			    bw = new BufferedWriter(new FileWriter(targetFile));
			    
			    String line = "";
			    
			    while ( (line=br.readLine()) != null){
			    	bw.write(line);
			    }
			    bw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if ( br != null){
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			if ( bw != null){
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	 }
	 
	 
	 public static void doPaste(String sourceFilePath,String targetFolder){
		  File sourceFile = new File(sourceFilePath);
		  String sourceFileName = sourceFile.getName();
		  
		  String targetFolderPath = targetFolder;
		  
		  File targetFile = new File(targetFolderPath + File.separator + sourceFileName);
		  BufferedInputStream bis = null;
		  BufferedOutputStream bos = null;
		  
		  try {
			    bis = new BufferedInputStream(new FileInputStream(sourceFile));
			    bos = new BufferedOutputStream(new FileOutputStream(targetFile));
			    
			    byte[] buff = new byte[8192];
			    int pos = 0;
			    
			    while ((pos=bis.read(buff)) != -1){
			    	bos.write(buff,0,pos);
			    	System.out.println(new String(buff,0,pos));
			    }
			    bos.flush();
			    
			    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if ( bis != null){
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			if ( bos != null){
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	 }
	 
	 
	 public static void main(String[] args){
			
			
			/*System.out.println(getFileSize(new File("E:\\编程参考资料")));
			System.out.println(getFileSize(new File("E:\\浪曦视频在线注册.txt")));
			System.out.println(getFileSize(new File("E:\\编程参考资料")));
			System.out.println(getFileSize(new File("D:\\Downloads")));
			System.out.println(getFileSize("D:\\Download"));
			*/
		/*	System.out.println(getFileLastModified(new File("E:\\浪曦视频在线注册.txt")));
			
			doPaste(new File("E:\\测试20090902.txt"), new File("d:\\"));
			doPaste("E:\\session.obj","D:\\");*/
			
			/*for (File fileTemp  : new File("d:\\测试20090902.txt").listFiles()){
				System.out.println(fileTemp);
			}*/
		 
		  /*for (File fileTemp : getFileList(new File("D:\\"))){
			  System.out.println(fileTemp);
		  }*/
		/* 
		 System.out.println(getFileType(new File("D:\\电脑操作备忘.doc")));
		 System.out.println(getFileType(new File("D:\\file_table")))*/;
		 
		 /*System.out.println(getFileNumberInFolder(new File("D:\\GNSS项目")));
		 System.out.println(getFolderNumberInFolder(new File("D:\\GNSS项目")));*/
		 
		/* move("D:\\session.obj","E:\\temp");*/
		 
		/* 
	    File file = new File("E:\\file_table");
	    System.out.println(file.exists());
	    System.out.println(file.isFile());
	    System.out.println(file.isDirectory());*/
	    
	    File fileDir = new File("D:/config");
	   /* System.out.println(fileDir.exists());
	    System.out.println(fileDir.isFile());
	    System.out.println(fileDir.isDirectory());
	    System.out.println(fileDir.delete());*/
	    
	    System.out.println(delete("D:/file_table"));
		 
	 }
	 
	 public static List<File> getFileList(File folder){
		 List<File> fileList = new ArrayList<File>();
		 
		 if ( folder.isDirectory()){
			 for ( File tempFile : folder.listFiles()){
				 fileList.add(tempFile);
			 }
		 }else{
			 throw new RuntimeException("不是文件夹");
		 }
		 
		 return fileList;
	 }
	 
	 public static String getFileName(File file){
		    return file.getName();
	 }
	 
	 
	 public static String getFileType(File file){
		 String type = "";
		  if (file.isDirectory()){
			  return "目录";
		  }
		  
		  String name = file.getName();
		  
		  int pos = name.lastIndexOf('.');
		  if (pos != -1){
		  type = name.substring(pos);}
		  return type;
	 }
	 
	 
	 public static int getFileNumberInFolder(File folder){
		  int count = 0;
		  if ( folder == null){
			  return 0;
		  }
		  
		  if ( folder.isFile()){
			  return 0;
		  }
			  
		  File[] files = folder.listFiles();
		  if (files == null){
			  return 0;
		  }
		  for (File tempFile : files){
				   if ( tempFile.isFile()){
					   count++;
				   }
			  }
		 
		  
		  return count;
	 }
	 
	 public static int getFolderNumberInFolder(File folder){
		 int count = 0;
		 if ( folder == null){
			 return 0;
		 }
		 
		 if (folder == null)
			 return 0;
		 
		 File[] files = folder.listFiles();
		 
		 if (files == null){
			 return 0;
		 }
		 
		 for (File tempFile : files){
			 if ( tempFile.isDirectory()){
				 count++;
			 }
		 }
		 return count;
	 }
	 
	 public static void move(String sourceFilePath, String targetFolder){
		   File sourceFile = new File(sourceFilePath);
		   String sourceFileName = sourceFile.getName();
		   
		   File targetFile = new File(targetFolder + File.separator + sourceFileName);
		   
		   FileInputStream fis = null;
		   FileOutputStream fos = null;
		   FileChannel in = null;
		   FileChannel out = null;
		try {
			fis = new FileInputStream(sourceFile);
			fos = new FileOutputStream(targetFile);
			in = fis.getChannel();
			out = fos.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				in.close();
				out.close();
				System.out.println(sourceFile.delete());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	   
	 }
	 
	 public static boolean createDirectory(String folder){
		 boolean flag = true;
		 
		 File dir = new File(folder);
		 
		 if ( !(dir.exists()) && !(dir.isDirectory())){
			 flag = dir.mkdirs();
		 }
		 return flag;
		 
	 }
	 
	 
	 public static boolean delete(String filePath){
		 boolean flag = true;
		 
		 File file = new File(filePath);
		 
		 if ( file.isFile()){
			 flag = file.delete();
			 System.out.println("flag in if: " + flag);
		 } 
		 if ( file.isDirectory()){
			 for (File tempFile : file.listFiles()){
				 flag =  delete(tempFile.getAbsolutePath());
				 System.out.println("flag in else: " + flag);
			 }
			 flag = file.delete();
		 }
		 
		 return flag;
	 }
	 
}
