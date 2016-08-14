package com.chenxushao.java.times;

import java.text.MessageFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.chenxushao.common.datetime.DateTimeUtils;

public class JodaTimeDemo2 {

	public static void main(String[] args) {
		Date now = new Date();

		// 返回给定时间所在月份的最后一天
		Date lastDayOfMonth = new DateTime(now).withHourOfDay(23)
				.withMinuteOfHour(59).withSecondOfMinute(59).dayOfMonth()
				.withMaximumValue().toDate();

		System.out.println(lastDayOfMonth);
		// 返回给定时间所在月份的天数
		int maxDayOfMonth = new DateTime(now).dayOfMonth().withMaximumValue()
				.getDayOfMonth();
		System.out.println(maxDayOfMonth);
	}

	/**
	 * 获取服务开始时间
	 *
	 * @return
	 */
	public static DateTime getBeginServiceDt() {
		int min = 700;
		return DateTime.now().withHourOfDay(min / 100)
				.withMinuteOfHour(min % 100).withSecondOfMinute(0);

	}

	/**
	 * 获取服务结束时间
	 *
	 * @return
	 */
	public static DateTime getEndServiceDt() {
		int max = 2230;
		return DateTime.now().withHourOfDay(max / 100)
				.withMinuteOfHour((max - 1) % 100).withSecondOfMinute(59);

	}

	/**
	 * @return void
	 * @throws ServiceException
	 * @Title: assertServiceTime
	 * @Description: 断言服务时间　 默认情况下7:00至22:30方可退票
	 */
	public static void assertServiceTime() {

		DateTime beginServiceDt = getBeginServiceDt();
		DateTime endServiceDt = getEndServiceDt();

		Interval interval = new Interval(beginServiceDt, endServiceDt);
		String beginServiceDtHint = DateTimeUtils.format(
				beginServiceDt.toDate(), "HH:mm");
		if (!interval.containsNow()) {
			String endServiceDtHint = DateTimeUtils.format(endServiceDt
					.plusMinutes(1).toDate(), "HH:mm");
			String hintWordsTemplate = "系统每日{0}~{1}提供服务！";
			String hintWords = MessageFormat.format(hintWordsTemplate,
					beginServiceDtHint, endServiceDtHint);
			throw new RuntimeException(hintWords);
		}
	}

	/**
	 * @Title: isServiceTime
	 * @Description: 是否在服务时间范围内
	 * @throws ServiceException
	 * @return void
	 */
	public static boolean isInServiceTime() {
		DateTime beginServiceDt = getBeginServiceDt();
		DateTime endServiceDt = getEndServiceDt();
		return new Interval(beginServiceDt, endServiceDt).containsNow();
	}

	// 断言退票时间，默认情况下，列表车开35分钟方可退票
	public static void assertRefuntTicketTime(Date trainStartTime) {
		if (trainStartTime == null
				|| new DateTime(trainStartTime).minusMinutes(35).isBeforeNow()) {
			throw new RuntimeException("退票须不晚于开车前" + 35 + "分钟！");
		}
	}
}
