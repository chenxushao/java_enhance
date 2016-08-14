package com.chenxushao.java.basis.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateDemo1 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println( System.currentTimeMillis());
		
		 
		//如何创建一个表示2008-8-8 20:00:00的时间
		//用Calendar的子类GregorianCalendar来创建
		//注意月份是从0至11表示的，即0代表一月，1代表2月
		Calendar c = new GregorianCalendar(2008,7,8,20,0,0);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(c.getTime()));
		
		//根据一个格式化的字符串来创建日期
		Date sameDate = sdf.parse("2008-8-8 20:00:00");
		System.out.println(sdf.format(sameDate));
	}

}
