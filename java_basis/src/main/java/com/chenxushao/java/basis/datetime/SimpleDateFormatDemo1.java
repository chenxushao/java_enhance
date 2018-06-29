package com.chenxushao.java.basis.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleDateFormatDemo1 {

	public static void main(String[] args) {
		// Date d=newstate Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());
		rightNow.get(Calendar.YEAR);
		// SimpleDateFormat dft=newstate SimpleDateFormat("yyyy年MM月dd日");
		// SimpleDateFormat dft=newstate SimpleDateFormat("yyyy:MM:dd");
		SimpleDateFormat dft = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
		// System.out.println(dft.format(d));
		System.out.println(System.currentTimeMillis());
		Date d = new Date(System.currentTimeMillis());
		rightNow.setTime(d);
		System.out.println(dft.format(d));
	}

}
