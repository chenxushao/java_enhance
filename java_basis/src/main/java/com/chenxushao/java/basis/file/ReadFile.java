package com.chenxushao.java.basis.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	static long length = 0;
	public static void main(String[] args) {
		
		long beginTime =System.currentTimeMillis();
		
		System.out.println(readFile("E:/gnsstestdata/sp3/igs15036.clk_30s"));
		

		long endTime = System.currentTimeMillis();
		
		System.out.println("耗时：" + (endTime - beginTime)/1000);
		System.out.println("StringBuffer容量：" + length);
	}

	
	private static String readFile(String path){
		String content = "";
		StringBuffer sb = new StringBuffer(1024);
	    String tempLine = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ( (tempLine=br.readLine()) != null){
				sb.append(tempLine + "\n");
			}
			content = sb.toString();
			 length =sb.capacity();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception ce){
			ce.printStackTrace();
		}
		
		
		
		
		return content;
	}
}
