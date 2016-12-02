package com.chenxushao.common.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//import org.springframework.util.Assert;

/******************************************************************************
 * 
 * <p>
 * Description: 日期时间工具类
 * </p>
 * Project: common Package: cn.com.gome.hotel.utils File: DateTimeUtils.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年4月23日 上午11:31:48
 * 
 *****************************************************************************/
public class DateTimeUtils {

	/*
	 * 时间样式参考　　 EEE:星期 aa:上午/下午等 0-yyyy-MM-dd<br> 1-yyyy-MM-dd HH<br>
	 * 2-yyyy-MM-dd HH:mm<br> 3-yyyy-MM-dd HH:mm:ss<br> 4-yyyy年MM月dd日 HH:mm:ss
	 * 5-MM/dd/yyyy hh:mm:ss aa 6- yy年MM月dd日 7-yyyy/MM/dd HH:mm:s 8-yyyy-MM-dd
	 * HH:mm:ss SSS (毫秒)
	 */
	private DateTimeUtils() {
	}

	/** 1 Day in Millis */
	public static final long DAY = 24L * 60L * 60L * 1000L;

	/** 1 Week in Millis */
	public static final long WEEK = 7 * DAY;

	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * @Title: getDefaultDateFormat
	 * @Description: 日期默认格式
	 * @return SimpleDateFormat
	 */
	public static SimpleDateFormat getDefaultDateFormat() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	/**
	 * @Title: getDefaultTimeFormat
	 * @Description: 时间默认格式
	 * @return SimpleDateFormat
	 * @throws
	 */
	public static SimpleDateFormat getDefaultTimeFormat() {
		return new SimpleDateFormat("kk:mm");
	}

	/**
	 * @Title: getDefaultWeekFormat
	 * @Description:年，星期默认格式
	 * @return SimpleDateFormat
	 * @throws
	 */
	public static SimpleDateFormat getDefaultWeekFormat() {
		return new SimpleDateFormat("yyyyww");
	}

	/**
	 * @Title: parse
	 * @Description:时间字符串根据指定格式转化为Date
	 * @param str
	 *            时间字符串
	 * @param formatPattern
	 *            时间格式
	 * @return Date
	 * @throws RuntimeException
	 */
	public static Date parse(String str, String formatPattern) {
		try {
			return new SimpleDateFormat(formatPattern).parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("could not parse date: " + str
					+ " LEGACY_FORMAT = "
					+ new SimpleDateFormat(formatPattern).toPattern(), e);
		}
	}

	/**
	 * 
	 * @Title: parse
	 * @Description: 转yyyy-MM-dd HH:mm:ss和 yyyy-MM-dd两种格式的日期
	 * @param str
	 * @return 返回Date 类型
	 */
	public static Date parse(String str) {
		if (str.length() != DATE_PATTERN.length()
				&& str.length() != DATE_TIME_PATTERN.length()) {
			return null;
		}
		String formatPattern = DATE_PATTERN;
		if (str.length() > 10) {
			formatPattern = DATE_TIME_PATTERN;
		}

		try {
			return new SimpleDateFormat(formatPattern).parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("could not parse date: " + str
					+ " LEGACY_FORMAT = "
					+ new SimpleDateFormat(formatPattern).toPattern(), e);
		}
	}

	/**
	 * @Title: format
	 * @Description:　时间转化为指定的格式的时间字符串
	 * @param date
	 *            　　时间
	 * @param formatPattern
	 *            　时间格式 如："yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String format(Date date, String formatPattern) {
		// Assert.notNull(date);
		return new SimpleDateFormat(formatPattern).format(date);
	}

	/**
	 * @Title: isWeekEnd
	 * @Description: 是否为周末
	 * @param date
	 * @return boolean
	 */
	public static boolean isWeekEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int weekEnd = cal.get(Calendar.DAY_OF_WEEK);
		return (weekEnd == Calendar.SUNDAY || weekEnd == Calendar.SATURDAY);
	}

	/**
	 * @Title: getWeekOfYear
	 * @Description: 一年的第几周
	 * @param date
	 * @return int
	 * @throws
	 */
	public static int getWeekOfYear(Date date) {
		String weekOfYear = format(date, "ww");
		return Integer.parseInt(weekOfYear);
	}

	public static void main(String[] args) {
		System.out.println(getWeekOfYear(new Date()));
		System.out.println(parse("2015-06-01 14:52:00"));
		System.out.println(format(parse("2015-06-01 14:52:00"),
				DATE_TIME_PATTERN));
	}

	/**
	 * @Title: getDayOfWeek
	 * @Description: 获得当前日期是星期几　说明：1，2，3，4，5，6，7 即星期一，二，三，四，五，六，日。
	 * @param date
	 *            时间
	 * @return int
	 */
	public static int getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return (dayOfWeek == 1) ? 7 : (dayOfWeek - 1);
	}

	/**
	 * @Title: getYear
	 * @Description: 返回年
	 * @param date
	 * @return int
	 */
	public static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.YEAR);
	}

	/**
	 * @Title: getMonth
	 * @Description: 返回月份
	 * @param date
	 * @return int
	 */
	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MONTH) + 1;
	}

	/**
	 * @Title: getDay
	 * @Description: 返回天
	 * @param date
	 * @return int
	 */
	public static int getDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @Title: getHour
	 * @Description: 返回小时
	 * @param date
	 * @return int
	 */
	public static int getHour(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * @Title: getMinute
	 * @Description: 返回分钟
	 * @param date
	 * @return int
	 */
	public static int getMinute(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MINUTE);
	}

	/**
	 * @Title: getSecond
	 * @Description: 返回秒钟
	 * @param date
	 * @return int
	 */
	public static int getSecond(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.SECOND);
	}

	/**
	 * @Title: getQuarter
	 * @Description: 返回季度
	 * @param date
	 * @return int
	 */
	public static int getQuarter(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		Double dd = new Double(Math.floor(cld.get(Calendar.MONTH) / 3));
		return dd.intValue() + 1;
	}

	/**
	 * @Title: leapYear
	 * @Description: 判断一年是否为闰年，如果给定年份小于1全部为 false
	 * @param year
	 *            年份，比如 2012 就是二零一二年
	 * @return boolean
	 */
	public static boolean leapYear(int year) {
		if (year < 4) {
			return false;
		}
		return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
	}

	/**
	 * @Title: countLeapYear
	 * @Description: 判断一年（不包括自己）之前有多少个闰年
	 * @param year
	 *            年份，比如 2012 就是二零一二年
	 * @return 闰年的个数
	 */
	public static int countLeapYear(int year) {
		// 因为要计算年份到公元元年（0001年）的年份跨度，所以减去1
		int span = year - 1;
		return (span / 4) - (span / 100) + (span / 400);
	}

	/**
	 * 
	 * @author zr_zhangyunhe
	 * @Description: 计算两个日期相差的天数
	 * @param smdate
	 * @param bdate
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获得指定日期的下 N 天的日期
	 * 
	 * @param startDate
	 * @return
	 */
	public static Date getNextNumDay(Date startDate, int num) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE, num);
		return calendar.getTime();
	}

	/**
	 * 获得指定日期的下 N 天的日期数组
	 * 
	 * @Description :
	 * @author houchangren
	 * @param startDate
	 * @param num
	 * @return
	 */
	public static Date[] getSpcilNumDayArray(Date startDate, int num) {
		Date[] dates = new Date[2];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dates[0] = sdf.parse(format(startDate, "yyyy-MM-dd"));
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dates[0]);
			calendar.add(Calendar.DATE, num);
			calendar.add(Calendar.SECOND, -1);
			dates[1] = calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates;
	}

}
