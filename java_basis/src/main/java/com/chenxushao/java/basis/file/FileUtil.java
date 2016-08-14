package com.chenxushao.java.basis.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/*
 * 文件操作实用小公具 
 * 功能：该类实现了对文件对象的拷贝删除、命名等工具。
 *       设计为全静态函数
 * 作者：唐健
 * 版本：1.0
 * 时间：2008.11.12
 */
public final class FileUtil {
	 
	
	// 功能：判断文件是否存在
	public static boolean isExist(String strPath) {
		if (strPath == null || strPath.equals("")) {
			return false;
		}
		File tempFile = new File(strPath);
		return tempFile.exists();
	}

	// 功能：判断文件是否存在
	public static boolean isExist(File file) {
		return file.exists();
	}

	// 功能：创建文件夹
	public static boolean createDirectory(String strPath) {
		File dirFile = null;
		try {
			dirFile = new File(strPath);
			if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
				boolean creadok = dirFile.mkdirs();
				if (creadok) {
					System.out.println(" ok:创建文件夹成功！ ");
				} else {
					System.out.println(" err:创建文件夹失败！ ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	// 功能：拷贝文件到目标文件夹
	public static boolean copyFile(String source, String destdir) {
		String filename = "";
		FileInputStream fis  = null;
		FileOutputStream fos = null;
		try {

			String filepath = source;
			File ofile = new File(filepath);
			File syspath = new File(destdir);
			// 不存在则创建
			if (!syspath.exists())
				syspath.mkdirs();

			filename = ofile.getName();
			 fis = new FileInputStream(ofile);
			 fos = new FileOutputStream(destdir + "/"
					+ filename);
			byte[] tempch = new byte[fis.available()];
			fis.read(tempch);
			fos.write(tempch);
			fos.flush();
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(fis !=null){
					fis.close();
				}
				if(fos != null){
				  fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return true;
	}

	// 功能：拷贝文件到目标文件
	public static boolean copyFile2(String sourceFile, String destFile) {

		try {

			String filepath = sourceFile;
			File ofile = new File(filepath);
			FileInputStream fis = new FileInputStream(ofile);
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] tempch = new byte[fis.available()];
			fis.read(tempch);
			fos.write(tempch);
			fos.flush();
			fos.close();
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 功能：添加时间后缀 生成新的文件名
	public static String makeFileSuffix(String file) {
		String fileName = file;
		Date m_date = new Date();
		SimpleDateFormat m_dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName += m_dateFormat.format(m_date);
		return fileName;
	}

	// 功能：删除文件夹及其子文件夹
	// 参数: delpath - 删除路径
	public static boolean deleteFile(String delpath) {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory())
						delfile.delete();
					else if (delfile.isDirectory())
						deleteFile(delpath + "\\" + filelist[i]);
				}
				file.delete();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 功能描述：删除文件目录或文件<br>
	 * 参数：file<br>
	 * 返回：
	 */
	public static boolean deleteFile(File file) {
		try {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.isFile()) {
						f.delete();
					} else if (f.isDirectory()) {
						deleteFile(f);
					}
				}
				file.delete();
			} else if (file.isFile()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	
	
	
	// 返回某个文件夹中的所有文件:添加了过滤器
	public static List<File> getFileList(File folder, FileFilter fileFilter) {
		if (!folder.isDirectory()) {
			return null;
		}
		List<File> fileNameList = new ArrayList<File>();
		File[] files = folder.listFiles(fileFilter);

		for (File file : files) {
			if (file.isFile()) {
				fileNameList.add(file);
			}
		}
		return fileNameList;
	}

	// 返回某个文件夹中的所有文件
	public static List<File> getFileList(File folder) {
		List<File> fileNameList = new ArrayList<File>();
		if (!folder.exists()) {
			return fileNameList;
		}
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				fileNameList.add(file);
			}
		}
		return fileNameList;
	}

	// 返回某个文件夹中的所有文件夹
	public static List<File> getFileFolderList(String folderPath) {
		List<File> fileNameList = new ArrayList<File>();
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				fileNameList.add(file);
			}
		}
		return fileNameList;
	}

	// 返回某个文件夹中的所有文件
	public static List<File> getFileList(String folderPath) {
		List<File> fileNameList = new ArrayList<File>();
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				fileNameList.add(file);
			}
		}
		return fileNameList;
	}

	// 返回某个文件夹中以ext为后缀的文件
	public static List<File> getFileList(File folder, String ext) {
		List<File> fileNameList = new ArrayList<File>();
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String extTemp = getFileType(file);
				if (extTemp.equalsIgnoreCase(ext)) {
					fileNameList.add(file);
				}
			}
		}
		return fileNameList;
	}
	
	// 返回某个文件夹中以某客串为前缀的文件:只返回文件，不返回文件夹
	public static List<File> getFileListByStartWith(File folder, String startWithStr) {
		
		
		class TmpFileFilter implements FileFilter{
			String startWithStr;
			private TmpFileFilter(String startWithStr){
				this.startWithStr = startWithStr;
			}
			
			public boolean accept(File pathname) {
				if(pathname==null || !pathname.exists()){
					return false;
				}
				String fileName = pathname.getName();
				
				return fileName.startsWith(startWithStr);
		   }
		};
		
		TmpFileFilter tmpFileFilter = new TmpFileFilter(startWithStr);
		 
		return getFileList(folder, tmpFileFilter);
	}
	
/*	//使用正则表达式
	public static List<File> getFileListByStartWith1(File folder, String startWithStr) {
		
		
		class TmpFileFilter implements FileFilter{
			String startWithStr;
			private TmpFileFilter(String startWithStr){
				this.startWithStr = startWithStr;
			}
			
			public boolean accept(File pathname) {
				if(pathname==null || !pathname.exists()){
					return false;
				}
				String fileName = pathname.getName();
				
				Pattern p = Pattern.compile(startWithStr);
				 
				return fileName.startsWith(startWithStr);
		   }
		};
		
		TmpFileFilter tmpFileFilter = new TmpFileFilter(startWithStr);
		 
		return getFileList(folder, tmpFileFilter);
	}
*/
	// 获取文件类型：后缀
	public static String getFileType(File file) {
		String ext = "";
		String fileName = file.getName();
		if (file.isFile()) {
			int pos = fileName.lastIndexOf('.');
			if (pos != -1)
				ext = fileName.substring(pos);
		}
		return ext;
	}

	public static boolean isEXTFile(String ext, String fileName) {
		if (fileName.toLowerCase().endsWith(ext.toLowerCase())) {
			return true;
		}

		return false;
	}

	// 返回观测数据文件中所有的O文件集合
	public static List<File> getOFileList(final File folder) {
		new Thread() {
			public void run() {
				dFileToOFile(folder);// ----------------------------090918
			}
		}.start();

		try {
			Thread.sleep(29);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<File> oFileNameList = new ArrayList<File>();
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			String str = files[i].getName();
			int index = str.length();
			if (str.charAt(index - 1) == 'o') {
				oFileNameList.add(files[i]);
			}
		}
		return oFileNameList;
	}

	// d文件转换成o文件 --------------------------------------090918 9:00添加
	public static void dFileToOFile(File folder) {
		File[] files = folder.listFiles();
		
		if (files==null || files.length == 0) {
			return;
		}

		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getName();
				int index = fileName.length();
				if (fileName.charAt(index - 1) == 'd') {
					String s = fileName.substring(0, index - 1) + "o";
					if (!FileUtil.isExist(s, folder.getAbsolutePath())) {
						try {
							Runtime.getRuntime().exec(
									".\\lib\\crx2rnx.exe" + " " + folder + "\\"
											+ fileName);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	// 返回某文件夹中文件个数
	public static int getFolderNumberInFolder(File folder) {
		if (folder == null)
			return 0;
		if (folder.isFile())
			return 0;

		File[] files = folder.listFiles();
		if (files == null)
			return 0;
		int count = 0;
		for (File file : files) {
			if (file.isDirectory()) {
				count++;
			}
		}
		return count;
	}

	// 移动文件
	public static void move(String sourceFilePath, String targetFolder) {
		File sourceFile = new File(sourceFilePath);
		String sourceFileName = sourceFile.getName();

		File targetFile = new File(targetFolder + File.separator
				+ sourceFileName);

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
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static boolean isExist2(String s, String path) {
		boolean flag = false;
		File file = new File(path);
		String[] fileNames = file.list();
		String fileName = null;
		for (int i = 0; i < fileNames.length; i++) {
			if (fileNames[i].startsWith(s)) {
				fileName = fileNames[i];
			}
		}
		if (fileName != null) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public static boolean isExist(String path, String start, String end) {
		boolean flag = false;
		File fileFolder = new File(path);
		File[] files = fileFolder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.startsWith(start) && fileName.endsWith(end)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	// 指定文件夹内是否存在某文件
	public static boolean isExist(String fileName, String targetFolder) {
		boolean exist = false;// 先假定不存在

		File targetFile = new File(targetFolder);

		if (!targetFile.isDirectory()) {
			return false;
		}

		String[] files = targetFile.list();
		for (String temp : files) {
			if (temp.equals(fileName)) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	public static String getFileName(String s, String path) {
		String fileName = "";
		File file = new File(path);
		String[] fileNames = file.list();
		for (int i = 0; i < fileNames.length; i++) {
			if (fileNames[i].startsWith(s)) {
				fileName = fileNames[i];
			}
		}

		return fileName;
	}

	public static String getNameByExt(String ext, String targetFolder) {
		String fileName = "";

		File targetFile = new File(targetFolder);

		for (File temp : targetFile.listFiles()) {
			if (temp.getName().endsWith(ext)) {
				fileName = temp.getAbsolutePath();// 绝对路径
				break;
			}
		}
		return fileName;
	}

	public static String getNameByExt1(String ext, String targetFolder) {
		String fileName = "";

		File targetFile = new File(targetFolder);

		for (String temp : targetFile.list()) {
			if (temp.endsWith(ext)) {
				fileName = temp;
				break;
			}
		}
		return fileName;
	}

	// 将字符串内容写入指定文件中
	public void save(String content, File targetFile) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
			bw.write(content);
			bw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save1(String content, String targetFile) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static StringBuffer getFileContent(File file) {
		String tmpLine = "";
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(file));
			while ((tmpLine = br.readLine()) != null) {
				sb.append(tmpLine + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb;
	}

	public static StringBuffer getFileContent(String filePath) {
		File file = new File(filePath);
		return getFileContent(file);
	}

	public static boolean copyFile(String source, String destdir, String newName) {
		boolean isCopySuccess = true;
		try {
			File sourceFile = new File(source);
			FileInputStream fis = new FileInputStream(sourceFile);
			File destPath = new File(destdir);
			// 不存在则创建
			if (!destPath.exists())
				destPath.mkdirs();

			FileChannel fileChannelRead = fis.getChannel();
			MappedByteBuffer byteBuffer = fileChannelRead.map(
					FileChannel.MapMode.READ_ONLY, 0, fileChannelRead.size());

			if (newName == null || newName.equals("")) {
				newName = sourceFile.getName();
			}
			FileOutputStream fos = new FileOutputStream(destdir
					+ File.separator + newName);
			FileChannel fileChannelWrite = fos.getChannel();
			fileChannelWrite.write(byteBuffer);
			fileChannelRead.close();
			fileChannelWrite.close();
		} catch (FileNotFoundException e) {
			isCopySuccess = false;
			e.printStackTrace();
		} catch (IOException ioe) {
			isCopySuccess = false;
			ioe.printStackTrace();
		}
		return isCopySuccess;
	}

	//拷贝整个目录:经测试有问题
	public static boolean copyDirectory(String srcDir, String desDir) {
		File originalFile = new File(srcDir);
		File filelist[] = originalFile.listFiles();
		BufferedInputStream bis = null;
		FileInputStream in = null;
		BufferedOutputStream bos = null;
		FileOutputStream out = null;
	    try{
		   for (File file : filelist) {
			  if (file.isDirectory()) {
				new File(desDir + File.separator + file.getName()).mkdirs();// 在目标路径上创建目录
				copyDirectory(srcDir + File.separator + file.getName(),// 递归
						desDir + File.separator + file.getName());
			  } else if (file.isFile()) {
				
				
					in = new FileInputStream(file);
					bis = new BufferedInputStream(in);
					out = new FileOutputStream(new File(desDir + File.separator
							+ file.getName()));
					bos = new BufferedOutputStream(out);
					byte[] b = new byte[1024 * 10];
					int len;
					while ((len = bis.read(b)) != -1) {
						bos.write(b, 0, len);
					}
				}
			  else {
				System.out.println("目标文件不存在");
			  }
		   }
	    }catch(Exception e){
	    	e.printStackTrace();
	    } finally {
			try {
				bos.flush();
				bos.close();
				out.close();
				bis.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		return true;
	}
	
	
	public static boolean copyDir(String srcDir, String desDir) {
		File originalFile = new File(srcDir);
		File filelist[] = originalFile.listFiles();
		if(filelist==null || filelist.length==0){
			return false;
		}
		 try{
			   for(File file : filelist){
				   copyFile(file.getAbsolutePath(), desDir,"");
			   }
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return false;
	    	
	    }  
		 
		return true;
	}
	
	/*
	 * //利用新IO处理 public static String getFileContentNIO(String filePath) { File
	 * file = new File(filePath); return getFileContent(file); }
	 */

	public static String getFileNameExceptExt(String name, String ext) {
		if (name.endsWith(ext)) {
			return name.substring(0, name.indexOf(ext));
		}
		return "";
	}

	public static String readTextFile(File f) throws IOException {

		StringBuffer buf = new StringBuffer();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(f)));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			buf.append(inputLine);
			buf.append('\n');
		}

		in.close();
		return buf.toString();

	}

	public static boolean saveAsFile(File file, StringBuffer sb) {
		FileOutputStream fos = null;
		BufferedWriter output = null;
		try {
			fos = new FileOutputStream(file);
			output = new BufferedWriter(new OutputStreamWriter(fos));
			output.write(sb.toString());
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (output != null) {
				try {
					output.close();
					output = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	//保存为文本文件
	public static void saveToTextFile(File f, String content)
			throws IOException {
		FileOutputStream out = new FileOutputStream(f);
		out.write(content.getBytes("UTF-8"));
		out.close();
	}

 
 
	 
	 
	 //重命名文件:在当前文件夹下
	 public static boolean renameFile(File file,String newName){
		 if(file==null || !file.exists()){
			 System.err.println("rename: " +  file.getAbsolutePath() + "文件不存在");
			 return false;
		 }
		 boolean flag = true;
		 
		 try{
		   flag = file.renameTo(new File(file.getParent() + File.separator + newName));
		 }catch(Exception e){
			 e.printStackTrace();
			 return false;
		 }
		return flag;
		 
	 }
 
	 
	 //重命名文件:在当前文件夹下
	 public static boolean renameFile(File file,File descFile){
		 if(file==null || !file.exists()){
			 System.err.println("rename: " +  file.getAbsolutePath() + "文件不存在");
			 return false;
		 }
		 boolean flag = true;
		 
		 try{
		   flag = file.renameTo(descFile);
		 }catch(Exception e){
			 e.printStackTrace();
			 return false;
		 }
		return flag;
		 
	 }
	 
/*
	 public static void main(String[] args) {
		 File srcFile = new File("D:/test/test_2010_0414_4/rinex/2008/050/abpo0500.08d.Z");
		 try {
			unZip(srcFile.getAbsolutePath(), srcFile.getParent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	 
	 
	 //将o文件重命名
	 public static void renameOFile(File folder) {
			File[] files = folder.listFiles();
			if (files==null || files.length == 0) {
				return;
			}

			for (File file : files) {
				if (file.isFile()) {
					String fileName = file.getName();
					int index = fileName.length();
					if (fileName.charAt(index - 1) == 'o') {
						String s = fileName.substring(0,7)+ "0" + fileName.substring(8);
					    System.out.println(renameFile(file,s));
					}
				}
			}
		}
	 
	 public static void main(String[] args) {
		  File file = new File("d:/复件 cf_leo_back");
		/* 　
		  System.out.println(renameFile(file, "xyz".));*/
		  
		 /* renameOFile(new File("D:/workspace_leoorbit/LeoOrbit/data/rinex/global_data/2008/362/hourly"));*/
		  
		 List<File> fileList = getFileListByStartWith(new File("D:/workspace_leoorbit/product/200419800R"), "log");
		 
		/* for(File file1 : fileList){
			 System.out.println(file1.getAbsolutePath());
		 }*/
		
		 
		 Pattern p = Pattern.compile("bbaa");
		 
		 System.out.println(p.matcher("aa").find());
		 
	}
}
