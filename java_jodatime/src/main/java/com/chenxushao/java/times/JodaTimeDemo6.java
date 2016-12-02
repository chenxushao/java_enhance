package com.chenxushao.java.times;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

public class JodaTimeDemo6 {

	public static void main(String[] args) {

		Date orderTrain = new Date();
		// .日期差 （时间比较，时间加减）
		Date trainArrivalTime = orderTrain;
		boolean isOrderFinish = new DateTime().minusHours(30).isAfter(
				trainArrivalTime.getTime());
		// 或者：

		Date orderCreateTime = new Date();
		boolean isOrderTimeoutCancel = new DateTime().minusMinutes(30).isAfter(
				orderCreateTime.getTime());

		// 或者：

		boolean isOrderFinish1 = Hours.hoursBetween(
				new DateTime(trainArrivalTime), new DateTime()).getHours() >= 24;

		// 或者：

		boolean isOrderTimeoutCancel1 = Minutes.minutesBetween(
				new DateTime(orderCreateTime), new DateTime()).getMinutes() >= 24;

	}
}
