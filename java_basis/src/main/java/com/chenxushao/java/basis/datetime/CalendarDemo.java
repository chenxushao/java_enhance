package com.chenxushao.java.basis.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("now: " + sdf.format(c.getTime()));

		System.out.println("年：" + c.get(Calendar.YEAR));
		System.out.println("月：" + (c.get(Calendar.MONTH) + 1));
		System.out.println("日：" + c.get(Calendar.DATE));

		System.out.println("时：" + c.get(Calendar.HOUR_OF_DAY));
		System.out.println("分：" + c.get(Calendar.MINUTE));
		System.out.println("秒：" + c.get(Calendar.SECOND));

		// 这一天是星期几?
		// 1=周日 2==周一 3=周二 4=周三　5=周四　6=周五　7=周六
		System.out.println("星期：" + c.get(Calendar.DAY_OF_WEEK));

		// 这一天是一年内的第多少天？
		System.out.println("年内第几天：" + c.get(Calendar.DAY_OF_YEAR));

		// 这一天所在的星期是这个月的第几个星期
		System.out.println("月内第几周：" + c.get(Calendar.WEEK_OF_MONTH));

		// 这一天所在的星期是这一年的第几个星期
		System.out.println("本年第几个星期：" + c.get(Calendar.WEEK_OF_YEAR));

		// 这个月的最后一天是什么？
		System.out.println("本月最后一天："
				+ c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

}
