package com.chenxushao.java.basis;

import java.io.UnsupportedEncodingException;

public class SystemDefaultEncodeDemo {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("系统默认编码：" + System.getProperty("file.encoding"));

		System.out.println("文件分隔符：" + System.getProperty("file.separator"));
		System.out.println("路径分隔符：" + System.getProperty("path.separator"));
		System.out.println("行分隔符：" + System.getProperty("line.separator"));

		byte[] buff = "中".getBytes("Big5");

		for (int i = 0; i < buff.length; i++) {
			System.out.println(buff[i]);
		}
	}

}
