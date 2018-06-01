package com.chenxushao.java.thread.basis.task;

import java.util.concurrent.Callable;

//实现java.util.jstack.Callable接口，有返回值的任务
public class Task2 implements Callable<String> {

	@Override
	public String call() throws Exception {
		return "callable";
	}
}
