package com.chenxushao.java.jvm.basis;

/*
 * 从java -D<name>=<value>中参数
 * 本参数中参数为
 * -Dconfig_path="cheunxushao dir path"
 */
public class JavaParameterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("config_path"));
	}
}
