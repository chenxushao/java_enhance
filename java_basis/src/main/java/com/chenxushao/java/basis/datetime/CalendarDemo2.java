package com.chenxushao.java.basis.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//日期增减
public class CalendarDemo2 {

	public static void main(String[] args) throws Exception {

		// 假设小明是1984.11.2出生的,那么从今天起，再这30年，小明是多少岁?
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sBirthday = "1984-11-02";
		Date dBirthday = sdf.parse(sBirthday);

		// 根据这个Date创建对应的Calendar对象
		Calendar cBirthday = Calendar.getInstance();
		cBirthday.setTime(dBirthday);

		// 今天起再过30年
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, 30);

		long longTime = now.getTimeInMillis() - cBirthday.getTimeInMillis();
		int year = (int) (longTime / (365L * 24 * 60 * 60 * 1000));
		System.out.println(year);
	}

}
