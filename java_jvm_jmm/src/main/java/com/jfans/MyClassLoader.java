package com.jfans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

	
	
	
	public MyClassLoader() {
//		super(null);
		// TODO Auto-generated constructor stub
	}





	public MyClassLoader(ClassLoader parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}





	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] buff;
		try {
			buff = loadClassDatas(name);
			return defineClass(name, buff, 0, buff.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("¿‡√ª’“µΩ");
		}
		
	}
	
	
	
	
	
	private byte[] loadClassDatas(String name) throws IOException{
		String fileName = name.replace('.', '/').concat(".class");
		 
		
	   
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 
		System.out.println(new File(fileName).getAbsolutePath());
		FileInputStream fis = new FileInputStream(new File(fileName));
		System.out.println();
		int ava= fis.available();
		byte[] bytes = new byte[ava];
		fis.read(bytes);
		
		baos.write(bytes);
		
		baos.close();
		fis.close();
		return baos.toByteArray();
	}
	
	

}
