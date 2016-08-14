package com.chenxushao.java.basis.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

 

public class FileDemo1 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		 String fileSeprator = System.getProperty("file.separator");
		  
		 
		 File srcFile = new File("d:" + fileSeprator + "复件 Everything.db");
		 SimpleDateFormat sdf  = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		 
		 System.out.println("-----基本属性-----");
		 System.out.println("是否可读? " + srcFile.canRead());
		 System.out.println("是否可写? " + srcFile.canWrite());
		 System.out.println("是否隐藏? " + srcFile.isHidden());
		 
		 
		 System.out.println("是否为绝对路径? " + srcFile.isAbsolute());
		 System.out.println("是否为目录? " + srcFile.isDirectory());
		 System.out.println("是否为文件? " + srcFile.isFile());
		 System.out.println("是否存在? " + srcFile.exists());
		 System.out.println("最后修改时间: " + sdf.format(new Date(srcFile.lastModified())));
		 System.out.println("字节数: " + srcFile.length());
		 //创建临时文件
		 //System.out.println(srcFile.createNewFile());
		 System.out.println();
		 File srcFile2 = new File("d:" + fileSeprator + "xyzdir" + fileSeprator + "LeoOrbit_2010_0722_1714.7z");
		 System.out.println("-----基本属性-----");
		 System.out.println("是否可读? " + srcFile2.canRead());
		 System.out.println("是否可写? " + srcFile2.canWrite());
		 System.out.println("是否隐藏? " + srcFile2.isHidden());
		 
		 
		 System.out.println("是否为绝对路径? " + srcFile2.isAbsolute());
		 System.out.println("是否为目录? " + srcFile2.isDirectory());
		 System.out.println("是否为文件? " + srcFile2.isFile());
		 System.out.println("是否存在? " + srcFile2.exists());
		 System.out.println("最后修改时间: " + sdf.format(new Date(srcFile2.lastModified())));
		 System.out.println("字节数: " + srcFile2.length());	
		 
		 //删除操作
		 //针对文件:文件不存在时也是返回false，即有文本文件在查看该文件，仍然可以删除
		//针对目录:表目录必须为空才能删除,即既无不能含有子目录，也不能含有文件,用资源管理器正打开该目录，也会删除
		 
		 
		 System.out.println("设置为可读: " + srcFile2.setReadOnly());
		 System.err.println("删除: " + srcFile2.delete());
		 
		 File dir = new File("D:" +fileSeprator + "xyzdir2");
		 System.out.println(dir.length());
		 System.out.println(dir.exists());
		 System.out.println(dir.lastModified());
		 System.out.println("删除目录：" + dir.delete());
		 
		 
		 //重命名
		 //此方法行为的许多方面都是与平台有关的：重命名操作无法将一个文件从一个文件系统移动到另一个文件系统，该操作不是不可分的，
		 //如果已经存在具有目标抽象路径名的文件，那么该操作可能无法获得成功。应该始终检查返回值，以确保重命名操作成功。
		 //当且仅当重命名成功时，返回 true；否则返回 false 
		 
		 File src = new File("d:" + fileSeprator + "xyzdir" + fileSeprator + "复件 xyzdir");
		 System.out.println(src.length());
		 
		 File destFile = new File(src.getAbsolutePath() + "back");
		 
	 
		 System.out.println("重命名: " + src.renameTo(destFile));
		 
		 
		 
	}

}
