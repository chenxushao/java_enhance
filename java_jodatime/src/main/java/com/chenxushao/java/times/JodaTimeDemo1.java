package com.chenxushao.java.times;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;

public class JodaTimeDemo1 {

	public static void main(String[] args) {
		DateTime dateTime;
		// 创建当前时间
		dateTime = DateTime.now();
		// 创建某一时刻的时间
		dateTime = new DateTime(2016, 6, 6, 12, 1, 1, 999);
		// 通过系统毫秒数创建当前时间
		dateTime = new DateTime(System.currentTimeMillis());
		// 通过格式化字符串创建时间
		dateTime = DateTime.parse("2016-06-30 12:01:01",
				DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));

		// 与JDK 的 Date 相互转换
		dateTime = new DateTime(new Date());
		Date jdkDate = dateTime.toDate();

		System.out.println(jdkDate);
		// 默认输出格式
		System.out.println(dateTime.toString()); // 2016-06-29T15:36:41.591+08:00
		// 格式化输出
		System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS")); // 2016-06-06
																			// 12:01:01.999
		// 输出星期几
		System.out.println(dateTime.toString("E yyyy-MM-dd HH:mm:ss")); // 星期三
																		// 2016-06-29
																		// 15:38:47

		// 时间操作, 注意每个返回都是一个新对象
		// 33天后
		dateTime = new DateTime(2016, 1, 1, 12, 10, 10, 10); // 2016-01-01
																// 12:10:10.010
		dateTime = dateTime.plusDays(33); // 2016-02-03 12:10:10.010
		// 年月日时分秒 还有毫秒, 都能加

		// 某月最后一天
		dateTime = new DateTime(2016, 1, 1, 12, 10, 10, 10); // 2016-01-01
																// 12:10:10.010
		dateTime = dateTime.dayOfMonth().withMaximumValue(); // 2016-01-31
																// 12:10:10.010

		// 某周第一天
		dateTime = new DateTime(2016, 1, 1, 12, 10, 10, 10); // 2016-01-01
																// 12:10:10.010
		dateTime = dateTime.dayOfWeek().withMinimumValue(); // 2015-12-28
															// 12:10:10.010
		// 类似的有 yearOfCentury dayOfYear monthOfYear dayOfMonth dayOfWeek

		// 距离今天多少天
		dateTime = DateTime.parse("2016-06-30 12:01:01",
				DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
		int days = Days.daysBetween(dateTime, DateTime.now()).getDays(); // 后面早则为负数
		System.out.println(days);
	}
}
