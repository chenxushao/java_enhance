package com.chenxushao.java.basis.datetime;

import java.text.SimpleDateFormat;  //日期格式化类
import java.util.Calendar;   //日历类，格式化日期最常用的类
import java.util.Date;  //日期类

public class DateTimeFormat {

	
	public static void main(String[] args)
	{
      //Date d=new Date();
      Calendar rightNow=Calendar.getInstance();
      rightNow.setTime(new Date());
      rightNow.get(Calendar.YEAR);
      //SimpleDateFormat dft=new SimpleDateFormat("yyyy年MM月dd日");
      //SimpleDateFormat dft=new SimpleDateFormat("yyyy:MM:dd");
      SimpleDateFormat dft=new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
      //System.out.println(dft.format(d));
      System.out.println(System.currentTimeMillis());
      Date d=new Date(System.currentTimeMillis());
      rightNow.setTime(d);
      System.out.println(dft.format(d));
	}

}
