package com.chenxushao.java.times;

import org.joda.time.DateTime;

public class JodaTimeDemo5 {

	public static void main(String[] args) {
		// --取得月份的头一天和最后一天
		beginAndEndOfDates();
	}

	/**
	 * 取得月份的头一天和最后一天. 取得一天的0:00和23:59:59 其他如年，星期的头一天，最后一天同理可证
	 */
	public static void beginAndEndOfDates() {
		String dateString = "2016-08-10T12:10:08";
		DateTime dt = new DateTime(dateString);
		DateTime startOfMonth = dt.dayOfMonth().withMinimumValue()
				.withTimeAtStartOfDay();
		System.out.println(startOfMonth.toString());

		DateTime endOfMonth = dt.dayOfMonth().withMaximumValue().millisOfDay()
				.withMaximumValue();
		System.out.println(endOfMonth);
	}
}
