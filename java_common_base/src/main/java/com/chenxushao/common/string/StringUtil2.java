package com.chenxushao.common.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 字符串处理的实用类
 */
public class StringUtil2 {
	// 取缺省
	public static SimpleDateFormat getDefaultDateFormat() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	public static SimpleDateFormat getDefaultTimeFormat() {
		return new SimpleDateFormat("kk:mm");
	}

	public static SimpleDateFormat getDefaultWeekFormat() {
		return new SimpleDateFormat("yyyyww");
	}

	// 字符串为空时返回""
	public static String convertNull(String s) {
		return s == null ? "" : s;
	}

	public static String decodeXml(String s) {
		if (s == null || s.equals(""))
			return s;
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&quot", "\"");
		// 兼容以前的版本
		s = s.replaceAll("rrnn", "\r\n");
		return s;
	}

	/**
	 * 处理xml的保留字
	 */
	public static String encodeXml(String s) {
		if (s == null || s.equals(""))
			return s;
		s = s.replaceAll("&", "&amp;");
		s = s.replaceAll(">", "&gt;");
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll("\"", "&quot;");
		s = s.replaceAll("\r\n", "&#x0A;");
		s = s.replaceAll("\n", "&#x0A;");
		return s;
	}

	/**
	 * 将数组转化为delim分隔的字符串
	 */
	public static String fromArray(String[] ss, String delim) {
		if (ss == null || ss.length == 0)
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ss.length - 1; i++) {
			sb.append(convertNull(ss[i]).trim()).append(delim);
		}
		sb.append(convertNull(ss[ss.length - 1]).trim());
		return sb.toString();
	}

	/**
	 * 将数组转化为delim分隔的字符串
	 */
	public static String fromList(List list, String delim) {
		if (list == null || list.size() == 0)
			return null;
		String[] ss = new String[list.size()];
		list.toArray(ss);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ss.length - 1; i++) {
			sb.append(convertNull(ss[i]).trim()).append(delim);
		}
		sb.append(convertNull(ss[ss.length - 1]).trim());
		return sb.toString();
	}

	// 将Map转化为字符串
	public static String fromMap(Map map, String delim) {
		if (map == null || map.size() == 0)
			return null;
		StringBuffer sb = new StringBuffer();
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			sb.append(key).append("=");
			sb.append(map.get(key)).append(delim);
		}
		return sb.toString();
	}

	/**
	 * 取一组日期
	 * 
	 * @param bday
	 *            String 开始日YYYYMMDD
	 * @param eday
	 *            String 结束日YYYYMMDD
	 * @return String[] 日期型字符数组
	 */
	public static String[] getDayRange(String bday, String eday) {
		GregorianCalendar rightNow = new GregorianCalendar(
				Integer.parseInt(bday.substring(0, 4)), Integer.parseInt(bday
						.substring(4, 6)) - 1, Integer.parseInt(bday.substring(
						6, 8)));
		int i = 0;
		String s = null;
		ArrayList al = new ArrayList();
		al.add(bday);
		while (true) {
			i++;
			rightNow.add(Calendar.DAY_OF_YEAR, 1);
			s = getDefaultDateFormat().format(rightNow.getTime());
			al.add(s);
			if (s.equals(eday))
				break;

		}

		return (String[]) al.toArray(new String[0]);
	}

	/**
	 * 取差日
	 * 
	 * @param day
	 *            String 基准日YYYYMMDD
	 * @param delta
	 *            int 基准日YYYYMMDD
	 * @return String 日期型字符
	 */
	public static String getDeltaDay(String day, int delta) {
		GregorianCalendar rightNow = new GregorianCalendar(Integer.parseInt(day
				.substring(0, 4)), Integer.parseInt(day.substring(4, 6)) - 1,
				Integer.parseInt(day.substring(6, 8)));
		rightNow.add(Calendar.DAY_OF_YEAR, delta);
		return getDefaultDateFormat().format(rightNow.getTime());
	}

	/**
	 * 取后一日
	 * 
	 * @param day
	 *            String 基准日YYYYMMDD
	 * @return String 日期型字符
	 */
	public static String getNextDay(String day) {
		return getDeltaDay(day, 1);
	}

	/**
	 * 取前一日
	 * 
	 * @param day
	 *            String 基准日YYYYMMDD
	 * @return String 日期型字符
	 */
	public static String getPreDay(String day) {
		return getDeltaDay(day, -1);
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

	// 检查为空或为""或"null"
	public static boolean isNull(Object s) {
		if (s == null || s.toString().trim().length() == 0 || s.equals("null"))
			return true;
		else
			return false;
	}

	public static boolean isNotNull(Object s) {
		return !isNull(s);
	}

	/**
	 * 测试一个字符是否为0，为null或""也返回0.
	 * 
	 * @param o
	 *            java.lang.Object默认输入一个数字型的字符
	 * @return String字符串
	 */
	public static String isZero(String o) {
		return (o == null || o.toString().trim().equals("")) ? "0" : o;
	}

	/**
	 * 无空指针的浮点型转换器.
	 * 
	 * @param s
	 *            String字符
	 * @return double浮点数
	 */
	public static double parseDouble(String s) {
		return Double.parseDouble(isZero(s));
	}

	/**
	 * 无空指针的整型转换器.
	 * 
	 * @param s
	 *            String字符
	 * @return int整型
	 */
	public static int parseInt(String s) {
		return Integer.parseInt(isZero(s));
	}

	/**
	 * 将一组以逗号分隔的字符串返回一个字符串数组
	 * 
	 * @param s
	 *            String 字符串
	 * @return String[] 字符串数组
	 */
	public static String[] toArray(String s) {
		return toArray(s, ",");
	}

	/**
	 * 将一组以分隔符分隔的字符串返回一个字符串数组
	 */
	public static String[] toArray(String s, String delim) {
		if (isNull(s))
			return new String[0];
		return s.split(delim);
	}

	/**
	 * 将一组以分隔符分隔的字符串返回一个字符串数组
	 */
	public static List toList(String s, String delim) {
		if (isNull(s))
			return new ArrayList(0);
		return Arrays.asList(s.split(delim));
	}

	/**
	 * 将整型数组转化为,分隔的字符串
	 */
	public static String int2string(int[] a) {
		if (a.length < 1)
			return "";
		String s = String.valueOf(a[0]);
		for (int i = 1; i < a.length; i++)
			s += "," + String.valueOf(a[i]);
		return s;
	}

	/**
	 * 字符串数组转化为int数组
	 */
	public static int[] string2int(String[] ss, String[] defautls) {
		if (ss == null || ss.length == 0) {
			ss = defautls;
			if (ss == null)
				return null;
		}
		int[] iRet = new int[ss.length];
		for (int i = 0; i < iRet.length; i++)
			iRet[i] = parseInt(ss[i]);
		return iRet;
	}

	/**
	 * 字符串转化为Map,格式为 k1,v1;k2,v2，或者格式为k1=v1;k2=v2 不进行正确性检查
	 */
	public static Map<String, String> toMap(String s) {
		Map<String, String> map = new HashMap<String, String>();
		if (isNull(s))
			return map;
		String[] ss = toArray(s, ";");
		for (int i = 0; i < ss.length; i++) {
			String[] value = toArray(ss[i], "=");
			if (value.length == 1)
				value = toArray(ss[i], ",");
			map.put(value[0], value[1]);
		}
		return map;
	}

	/**
	 * 两日期相减，求天数
	 */
	public static int delta(Date date1, Date date2) {
		return Math
				.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24)));
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
	 * 分钟转小时，保留小数点后一位，四舍五入
	 */
	public static String minute2hour(int minute) {
		return String.valueOf(1.0 * ((minute + 3) * 10 / 60) / 10);
	}

	/**
	 * 取近似字串
	 */
	public static String soundLike(String str) {
		if (isNull(str))
			return "";
		return new StringTokenizer(str, ",;，；.。").nextToken();
	}

	/**
	 * 去掉尾字符
	 */
	public static String rtrim(String str, String suffixs) {
		if (isNull(str) || isNull(suffixs))
			return str;
		int i = str.length() - 1;
		for (; i >= 0; i--) {
			if (suffixs.indexOf(str.charAt(i)) == -1)
				break;
		}
		return str.substring(0, i + 1);
	}
}
