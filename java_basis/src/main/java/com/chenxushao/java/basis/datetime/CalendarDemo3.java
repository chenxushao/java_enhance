package com.chenxushao.java.basis.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class CalendarDemo3 {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// 时区
		// Calendar.getInstance()或new Date()是按照操作系统的默认时区得到的时间

		// 东８区
		Calendar beijing = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

		// 西５区
		Calendar newyork = Calendar.getInstance(TimeZone.getTimeZone("GMT+5"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("北京时间: " + sdf.format(beijing.getTime()));
		System.out.println("纽约时间: " + sdf.format(newyork.getTime()));
	}

}
