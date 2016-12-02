package com.chenxushao.java.basis.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

	// 给类加载器指定一个名字，在本例中只是为了便于区分不同的加载器对象
	private String name;
	private String path = "d:\\";
	private final String fileType = ".class";

	public MyClassLoader(String name) {
		super();
		this.name = name;
	}

	public MyClassLoader(ClassLoader parent, String name) {
		super(parent);
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return this.name;
	}

	// 自定义私有方法
	private byte[] loadClassData(String name) throws ClassNotFoundException {
		FileInputStream fis = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;

		try {
			// 把name字符串中的"."替换为"\"，从而把类中的包名转变为路径名
			// 例如，如果name为com.jfans.Sample，那么将转变为"com\jfans\Sample"
			name = name.replaceAll("\\.", "\\\\");
			fis = new FileInputStream(new File(path + name + fileType));
			baos = new ByteArrayOutputStream();
			int ch = 0;
			while ((ch = fis.read()) != -1) {
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (IOException e) {
			// System.out.println("exception e: " + e);
			// 异常转换
			throw new ClassNotFoundException("class is not found:" + name, e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (baos != null) {
					baos.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return data;
	}

	// 自定义加载器要重写的方法
	protected Class findClass(String name) throws ClassNotFoundException {
		byte[] data = loadClassData(name);
		return defineClass(name, data, 0, data.length);
	}

}
