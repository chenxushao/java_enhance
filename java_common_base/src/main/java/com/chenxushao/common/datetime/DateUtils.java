package com.chenxushao.common.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {
	private DateUtils() {
	}

	public final static String CST = "yyyy年MM月dd日 HH:mm:ss";
	public final static String LONG_EN = "yyyyMMdd_HHmmss";
	public final static String f = "yyyy-MM-dd HH:mm:ss";

	private static final String FORMAT = "MM/dd/yyyy hh:mm:ss aa";
	private static final String LEGACY_FORMAT = "MM/dd/yyyy HH:mm aa";
	private static final String FORMAT_UI = "MM/dd/yyyy hh:mm aa";

	private static final SimpleDateFormat rfc822DateFormats[] = new SimpleDateFormat[] {
			new SimpleDateFormat("EEE, d MMM yy HH:mm:ss z", Locale.US),
			new SimpleDateFormat("EEE, d MMM yy HH:mm z", Locale.US),
			new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US),
			new SimpleDateFormat("EEE, d MMM yyyy HH:mm z", Locale.US),
			new SimpleDateFormat("d MMM yy HH:mm z", Locale.US),
			new SimpleDateFormat("d MMM yy HH:mm:ss z", Locale.US),
			new SimpleDateFormat("d MMM yyyy HH:mm z", Locale.US),
			new SimpleDateFormat("d MMM yyyy HH:mm:ss z", Locale.US) };

	/* Create Date Formats */
	final String[] possibleDateFormats = new String[] {
			/* RFC 1123 with 2-digit Year */"EEE, dd MMM yy HH:mm:ss z", //$NON-NLS-1$

			/* RFC 1123 with 4-digit Year */"EEE, dd MMM yyyy HH:mm:ss z", //$NON-NLS-1$

			/* RFC 1123 with no Timezone */"EEE, dd MMM yy HH:mm:ss", //$NON-NLS-1$

			/* Variant of RFC 1123 */"EEE, MMM dd yy HH:mm:ss", //$NON-NLS-1$

			/* RFC 1123 with no Seconds */"EEE, dd MMM yy HH:mm z", //$NON-NLS-1$

			/* Variant of RFC 1123 */"EEE dd MMM yyyy HH:mm:ss", //$NON-NLS-1$

			/* RFC 1123 with no Day */"dd MMM yy HH:mm:ss z", //$NON-NLS-1$

			/* RFC 1123 with no Day or Seconds */"dd MMM yy HH:mm z", //$NON-NLS-1$

			/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ssZ", //$NON-NLS-1$

			/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss'Z'", //$NON-NLS-1$

			/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:sszzzz", //$NON-NLS-1$

			/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss z", //$NON-NLS-1$

			/* ISO 8601 */"yyyy-MM-dd'T'HH:mm:ssz", //$NON-NLS-1$

			/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss", //$NON-NLS-1$

			/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HHmmss.SSSz", //$NON-NLS-1$

			/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss", //$NON-NLS-1$

			/* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mmZ", //$NON-NLS-1$

			/* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mm'Z'", //$NON-NLS-1$

			/* RFC 1123 without Day Name */"dd MMM yyyy HH:mm:ss z", //$NON-NLS-1$

			/* RFC 1123 without Day Name and Seconds */"dd MMM yyyy HH:mm z", //$NON-NLS-1$

			/* Simple Date Format */"yyyy-MM-dd", //$NON-NLS-1$

			/* Simple Date Format */"MMM dd, yyyy" //$NON-NLS-1$
	};

	/** 1 Day in Millis */
	public static final long DAY = 24L * 60L * 60L * 1000L;

	/** 1 Week in Millis */
	public static final long WEEK = 7 * DAY;

	/**
	 * @return A Calendar instance with the time being Today with a Time of
	 *         0:00:00
	 */
	public static Calendar getToday() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today;
	}

	public static Date getNow() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	public static Date parse(String s, String formatPattern) {
		try {
			return new SimpleDateFormat(formatPattern).parse(s);
		} catch (ParseException e) {
			throw new RuntimeException("could not parse date: " + s
					+ " LEGACY_FORMAT = "
					+ new SimpleDateFormat(LEGACY_FORMAT).toPattern(), e);
		}
	}

	public static String format(Date date, String formatPattern) {
		if (date == null) {
			return "";
		} else {
			return new SimpleDateFormat(formatPattern).format(date);
		}
	}

	public static void main(String[] args) {
	}

	public static SimpleDateFormat getDefaultDateFormat() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	public static SimpleDateFormat getDefaultTimeFormat() {
		return new SimpleDateFormat("kk:mm");
	}

	public static SimpleDateFormat getDefaultWeekFormat() {
		return new SimpleDateFormat("yyyyww");
	}

	public static String getDeltaDay(String day, int delta) {
		GregorianCalendar rightNow = new GregorianCalendar(Integer.parseInt(day
				.substring(0, 4)), Integer.parseInt(day.substring(4, 6)) - 1,
				Integer.parseInt(day.substring(6, 8)));
		rightNow.add(Calendar.DAY_OF_YEAR, delta);
		return getDefaultDateFormat().format(rightNow.getTime());
	}

	public static String getNextDay(String day) {
		return getDeltaDay(day, 1);
	}

	public static String getPreDay(String day) {
		return getDeltaDay(day, -1);
	}

	/**
	 * 求两天之间的工作日
	 */
	public static int deltaWorkDay(Date date1, Date date2) {
		int delta = delta(date1, date2);
		if (delta == 0)
			return 0;
		// 扣除周末
		delta = delta - (delta / 7) * 2;
		// 扣除头尾
		if (delta < 7) {
			if (isWeekEnd(date1))
				delta--;
			if (isWeekEnd(date2))
				delta--;
		}
		return delta;
	}

	/**
	 * 判断一天是否为周末
	 */
	private static boolean isWeekEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return true;
		else
			return false;
	}

	/**
	 * 取某周的第一天
	 */
	public static String getFirstDayOfWeek(String week) {
		try {
			return getDefaultDateFormat().format(
					getDefaultWeekFormat().parse(week));
		} catch (ParseException e) {
			return "ERROR_DAY";
		}
	}

	/**
	 * 取当前的系统时间，格式HH:MM
	 */
	public static String getSysTime() {
		return getDefaultTimeFormat().format(
				java.util.Calendar.getInstance().getTime());
	}

	/**
	 * 取当前系统日期，格式YYYYMMDD
	 * 
	 * @return String日期型字符
	 */
	public static String getCurrentDay() {
		return getDefaultDateFormat().format(
				java.util.Calendar.getInstance().getTime());
	}

	// 取当前星期，格式yyyyww
	public static String getCurrentWeek() {
		return getDefaultWeekFormat().format(
				java.util.Calendar.getInstance().getTime());
	}

	// 取差周
	public static String getDeltaWeek(String week, int delta) {
		GregorianCalendar rightNow = new GregorianCalendar();
		try {
			Date date = getDefaultWeekFormat().parse(week);
			rightNow.setTime(date);
			rightNow.add(Calendar.WEEK_OF_YEAR, delta);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getDefaultWeekFormat().format(rightNow.getTime());
	}

	// 取前周
	public static String getPreWeek(String week) {
		return getDeltaWeek(week, -1);
	}

	// 取后周
	public static String getNextWeek(String week) {
		return getDeltaWeek(week, 1);
	}

	/**
	 * 分钟转小时，保留小数点后一位，四舍五入
	 */
	public static String minute2hour(int minute) {
		return String.valueOf(1.0 * ((minute + 3) * 10 / 60) / 10);
	}

	/**
	 * 两日期相减，求天数
	 */
	public static int delta(Date date1, Date date2) {
		return Math
				.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24)));
	}
}
