package com.chenxushao.common.string;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @ClassName: StringUtils
 * @Description: String处理工具类
 * @author chenxushao@gmail.com
 * @date 2013-9-13 下午12:30:24
 */
public class StringUtils {
	// 在工具类中可以定义一些经常使用的最终变量
	private static final String EMPTY_STRING = ""; // 空字符串
	private static final String DOT_DOT_DOT = "..."; // ...

	public static final String INFINITY_STRING = "\u221E"; // "oo";pa
	public static final int CRAPPY_INFINITY_AS_INT = 365 * 24 * 3600; // seconds
																		// (365days)
	public static final long CRAPPY_INFINITE_AS_LONG = 10000 * 365 * 24 * 3600; // seconds
																				// (10k
																				// years)

	public static final int DEFAULT_WRAPLENGTH = 150;

	private static final String NEWLINE_SEPARATOR = "\n";

	private static final String NEWLINE_EXPR = "\\n";

	private static final String RETURN_EXPR = "\\r";

	private static final String TAB_EXPR = "\\t";

	/* This utility class constructor is hidden */
	private StringUtils() {
		// private default constructor
	}

	// 安全的trim方法,避免了传入参数为null时产生的NullPointerException异常
	public static final String safeTrim(String string) {
		return string == null ? null : string.trim();
	}

	/*
	 * 将null转化 为""
	 */
	public static String convertNull(String s) {
		return s == null ? "" : s;
	}

	// 判断字符串不为空且字符个数大于0:即不为空串
	public static boolean isSet(String str) {
		return (str != null && str.length() > 0);
	}

	// 是否为空串""
	public static boolean isBlack(String str) {
		return (str != null && str.length() == 0);
	}

	// 合并2个字符串数组合并为一个新数组
	public static String[] concat(String[] part1, String[] part2) {
		// 创建合适大小的数组
		String[] full = new String[part1.length + part2.length];
		// 使用System.arraycopy方法,虽然也可以自已去实现，但尽量使用系统库有得于重用，而且效率可能更好
		System.arraycopy(part1, 0, full, 0, part1.length);
		System.arraycopy(part2, 0, full, part1.length, part2.length);
		return full;
	}

	// 合并2个客串数组为一个新数组,同时保持值唯一，即去掉重复的
	public static String[] concatUniq(String[] part1, String[] part2) {
		// 当然了也可以直接使用Set,但set不按照加入的顺序存放，List按照加入的顺序存放
		List<String> nlist = new ArrayList<String>();

		for (int i = 0; i < part1.length; i++) {
			if (!nlist.contains(part1[i]))
				nlist.add(part1[i]);
		}

		for (int i = 0; i < part2.length; i++) {
			if (!nlist.contains(part2[i]))
				nlist.add(part2[i]);
		}
		// 传入一个长度为0的客串数组，这点值得借鉴,有时候在需要返回一个数组的时候，其实没必要返回一个null，也可以返回一个长度为0数组，这样也避免了NullPointerxception
		return nlist.toArray(new String[0]);
	}

	// 针对字符串集合的replaceAll，只需要增加一个for循环即可
	public static Set<String> replaceAll(Set<String> strings, String search,
			String replace) {
		Set<String> replacedStrings = new HashSet<String>(strings.size());// 只new实际大小的，节约空间

		for (String string : strings) {
			replacedStrings.add(replaceAll(string, search, replace));
		}
		return replacedStrings;
	}

	// 用指定字符replace替换字符串中search字符
	public static String replaceAll(String str, String search, String replace) {
		int start = 0;
		int pos;
		StringBuilder result = null;
		while ((pos = str.indexOf(search, start)) >= 0) {// String类的indexOf返回值>=0则表示找到了
			if (result == null)
				result = new StringBuilder(str.length());
			result.append(str.substring(start, pos));
			result.append(replace);
			start = pos + search.length();
		}

		if (result != null)
			result.append(str.substring(start));

		return result != null ? result.toString() : str;
	}

	// 字符串转化整型：返回-1表示非数字字符串
	public static int stringToInt(String str) {
		try {
			return Integer.parseInt(str);// 注意这种用法，在try中直接返回
		} catch (NumberFormatException e) {// 这个异常捕不捕获都行，在catch中返回-1，但好像当str确实为"-1"时没有考虑到
			return -1;
		}
	}

	// 在IO读写的方法中，一般都在方法定义中直接申请抛出IOException，在方法内部反而不需要try,catch之类，当然，针对文件读写的，finally是一定要的，要关闭流
	public static String readString(Reader reader) throws IOException {
		StringBuilder str = new StringBuilder();// 在单线程环境下，优先使用StringBuilder
		int len = 0;
		char[] buf = new char[1000];

		while ((len = reader.read(buf)) != -1)
			str.append(buf, 0, len);

		return str.toString();
	}

	// 返回某字符串在数组中的位置:下标
	private static int indexOfString(String[] strArray, String searchStr,
			boolean ignoreCase) {
		if ((strArray == null) || (strArray.length == 0))
			return -1;

		boolean found = false;
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i] == null) {
				if (searchStr == null)
					found = true;
			} else {
				if (ignoreCase)
					found = strArray[i].equalsIgnoreCase(searchStr);
				else
					found = strArray[i].equals(searchStr);
			}
			if (found)
				return i;
		}
		return -1;
	}

	// 返回某字符串在数组中的位置：区分大小写
	public static int indexOf(String[] strArray, String searchStr) {
		return indexOfString(strArray, searchStr, false);
	}

	public static int compare(String s1, String s2) {
		if (s1 == null) {
			return s2 == null ? 0 : -1;
		}

		if (s2 == null) {
			return 1;
		}

		return s1.compareTo(s2);
	}

	// 返回某字符串在数组中的位置：不区分大小写
	public static int indexOfIgnoreCase(String[] strArray, String searchStr) {
		return indexOfString(strArray, searchStr, true);
	}

	// 字符串数组中是否存在某字符串:区分大小写
	public static boolean contains(String[] strArray, String searchStr) {
		return indexOf(strArray, searchStr) >= 0;
	}

	// 字符串数组中是否存在某字符串:不区分大小
	public static boolean containsIgnoreCase(String[] strArray, String searchStr) {
		return indexOfIgnoreCase(strArray, searchStr) >= 0;
	}

	public static boolean contains(String[] strArray, String searchStr,
			boolean ignoreCase) {
		if (ignoreCase)
			return containsIgnoreCase(strArray, searchStr);
		else
			return contains(strArray, searchStr);
	}

	protected static String[] removeFromStringArray(String[] strings,
			String[] removeStrings) {
		// new合适大小的List
		List<String> list = new ArrayList<String>(strings.length);
		boolean remains;

		for (int i = 0; i < strings.length; i++) {
			if (removeStrings == null) {
				remains = strings[i] != null;
			} else {
				remains = !contains(removeStrings, strings[i]);
			}
			if (remains) {
				list.add(strings[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	} // removeFromStringArray()

	public static String[] remove(String[] strings, String[] removeStrings) {
		if (strings == null || strings.length == 0 || removeStrings == null
				|| removeStrings.length == 0) {
			return strings;
		}
		return remove(strings, removeStrings);
	}

	public static String[] remove(String[] strings, String removeString) {
		// 这点巧妙，构造 一个字符串数组
		String[] removeStrings = { removeString };
		return remove(strings, removeStrings);
	}

	// 去掉字符串数组中的null值
	public String[] removeNull(String[] strings) {
		if (strings == null)
			return strings;

		return removeFromStringArray(strings, null);
	} // removeNull()

	// 将字符串数组转化为字符串，以separator分隔
	public static String asString(String[] strings, String separator) {
		if (strings == null || strings.length == 0) {
			return "";
		}
		StringBuilder sb = null;

		sb = new StringBuilder(strings.length * 20);//
		if (strings.length > 0) {
			// 先取第0个
			sb.append(strings[0].toString());
			for (int i = 1; i < strings.length; i++) {
				// 分隔符
				sb.append(separator);
				if (strings[i] != null)
					sb.append(strings[i]);
				else {
					sb.deleteCharAt(sb.length() - 1);// 若为空，则将前加入的分隔符删去
				}
			}
		}
		return sb.toString();
	} // asString()

	// 以","分隔
	public static String asString(String[] strings) {
		return asString(strings, ",");
	} // asString()

	// 用指定长度显示字符串，超出部分用"..."代替
	public static String getShortenedString(String value, int length) {

		if (value == null)
			return "";

		if (value.length() > length) {
			value = value.substring(0, length) + "...";
		}

		return value;
	}

	public static boolean containsIgnoreCase(Collection<String> c, String s) {
		if (c == null || s == null) {
			return false;
		}

		for (String string : c) {
			if (string.equalsIgnoreCase(s)) {
				return true;
			}
		}

		return false;
	}

	public static String formatBytes(long bytes) {
		String size = "";
		if (bytes > 1024 * 1024)
			size += (bytes / 1024 / 1024) + " MB (" + bytes + " Bytes)";
		else if (bytes > 1024)
			size += (bytes / 1024) + " KB (" + bytes + " Bytes)";
		else if (bytes > 1)
			size += bytes + " Bytes";
		else
			size += bytes + " Byte";
		return size;
	}

	private static final char[] hexDigit = "0123456789abcdef".toCharArray();

	private static void appendHex(StringBuffer buffer, byte b) {
		buffer.append(hexDigit[(b >> 4) & 15]).append(hexDigit[b & 15]);
	}

	private static void appendHex(StringBuffer buffer, byte[] b) {
		for (int i = 0; i < b.length; ++i)
			buffer.append(hexDigit[(b[i] >> 4) & 15]).append(
					hexDigit[b[i] & 15]);
	}

	/**
	 * 
	 * @param b
	 * @return 字节转化为16进制字符串形式
	 */
	public static String toHexString(byte b) {
		StringBuffer buffer = new StringBuffer(2);
		appendHex(buffer, b);
		return buffer.toString();
	}

	/**
	 * 字节转化为16进制字符串形式
	 * 
	 * @param b
	 * @return
	 */
	public static String toHexString(byte[] b) {
		StringBuffer buffer = new StringBuffer(b.length * 2);
		appendHex(buffer, b);
		return buffer.toString();
	}

	/**
	 * Concatenates an array of string using a separator.
	 * 
	 * @param strings
	 *            the strings to concatenate
	 * @param separator
	 *            the separator between two strings
	 * @return the concatenated strings 将字符串数组以指定分隔符连起来
	 */
	public static String toString(String[] strings, String separator) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strings.length; i++) {
			if (i > 0) {// 首字符不需要加分隔符
				sb.append(separator);
			}
			sb.append(strings[i]);
		}
		return sb.toString();
	}

	/**
	 * Returns a string with size of count and all characters initialized with
	 * ch. <br>
	 * 
	 * @param ch
	 *            the character to be repeated in the result string.
	 * @param count
	 *            the number of times the given character should occur in the
	 *            result string.
	 * @return A string containing <i>count</i> characters <i>ch</i>.
	 *         返回count个ch组成的字符串
	 */
	public String repeat(char ch, int count) {
		StringBuffer buffer = null;

		buffer = new StringBuffer(count);
		for (int i = 1; i <= count; i++) {
			buffer.append(ch);
		}

		return (buffer.toString());
	} // repeat()

	/**
	 * Shortens the given label to the given maximum length and filters
	 * non-printable characters.
	 * 
	 * @param label
	 *            the label
	 * @param maxLength
	 *            the max length
	 * 
	 * @return the shortened label
	 */
	public static String shorten(String label, int maxLength) {
		if (label == null) {
			return null;
		}

		// shorten label
		if (maxLength < 3) {
			return DOT_DOT_DOT;
		}
		if (label.length() > maxLength) {
			label = label.substring(0, maxLength / 2)
					+ DOT_DOT_DOT
					+ label.substring(label.length() - maxLength / 2,
							label.length());

		}

		// filter non-printable characters
		StringBuffer sb = new StringBuffer(maxLength + 3);
		for (int i = 0; i < label.length(); i++) {
			char c = label.charAt(i);
			if (Character.isISOControl(c)) {
				sb.append('.');
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String arrayToString(String[] array) {
		if (array == null || array.length == 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer(array[0]);
			for (int i = 1; i < array.length; i++) {
				sb.append(", ");
				sb.append(array[i]);
			}
			return sb.toString();
		}
	}

	public static boolean equals(byte[] data1, byte[] data2) {
		if (data1 == data2)
			return true;
		if (data1 == null || data2 == null)
			return false;
		if (data1.length != data2.length)
			return false;
		for (int i = 0; i < data1.length; i++) {
			if (data1[i] != data2[i])
				return false;
		}
		return true;
	}

	public static String getNonNullString(Object o) {
		return o == null ? "-" : o.toString();
	}

	public static boolean containsIgnoreCase4Collections(Collection<String> c,
			String s) {
		if (c == null || s == null) {
			return false;
		}

		for (String string : c) {
			if (string.equalsIgnoreCase(s)) {
				return true;
			}
		}

		return false;
	}

	public static boolean equals(String s0, String s1) {
		boolean s0Null = s0 == null;
		boolean s1Null = s1 == null;
		if (s0Null || s1Null) {
			return s0Null == s1Null;
		}
		return s0.equals(s1);
	}

	/**
	 * Decode a string containing two hex digits for each byte.
	 * 
	 * @param hex
	 *            The hex encoded string
	 * @return The byte array represented by the given hex string
	 * @see #encodeHexStr(byte[])
	 * @see #encodeHexStr(byte[], int, int)
	 */
	public static byte[] decodeHexStr(String hex) {
		if (hex.length() % 2 != 0)
			throw new IllegalArgumentException(
					"The hex string must have an even number of characters!");

		byte[] res = new byte[hex.length() / 2];

		int m = 0;
		for (int i = 0; i < hex.length(); i += 2) {
			res[m++] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
		}

		return res;
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
	 * 取近似字串
	 */
	public static String soundLike(String str) {
		if (isNull(str))
			return "";
		return new StringTokenizer(str, ",;，；.。").nextToken();
	}

	public static String fromUTF8ByteArray(byte[] bytes) {
		int i = 0;
		int length = 0;

		while (i < bytes.length) {
			length++;
			if ((bytes[i] & 0xf0) == 0xf0) {
				// surrogate pair
				length++;
				i += 4;
			} else if ((bytes[i] & 0xe0) == 0xe0) {
				i += 3;
			} else if ((bytes[i] & 0xc0) == 0xc0) {
				i += 2;
			} else {
				i += 1;
			}
		}

		char[] cs = new char[length];

		i = 0;
		length = 0;

		while (i < bytes.length) {
			char ch;

			if ((bytes[i] & 0xf0) == 0xf0) {
				int codePoint = ((bytes[i] & 0x03) << 18)
						| ((bytes[i + 1] & 0x3F) << 12)
						| ((bytes[i + 2] & 0x3F) << 6) | (bytes[i + 3] & 0x3F);
				int U = codePoint - 0x10000;
				char W1 = (char) (0xD800 | (U >> 10));
				char W2 = (char) (0xDC00 | (U & 0x3FF));
				cs[length++] = W1;
				ch = W2;
				i += 4;
			} else if ((bytes[i] & 0xe0) == 0xe0) {
				ch = (char) (((bytes[i] & 0x0f) << 12)
						| ((bytes[i + 1] & 0x3f) << 6) | (bytes[i + 2] & 0x3f));
				i += 3;
			} else if ((bytes[i] & 0xd0) == 0xd0) {
				ch = (char) (((bytes[i] & 0x1f) << 6) | (bytes[i + 1] & 0x3f));
				i += 2;
			} else if ((bytes[i] & 0xc0) == 0xc0) {
				ch = (char) (((bytes[i] & 0x1f) << 6) | (bytes[i + 1] & 0x3f));
				i += 2;
			} else {
				ch = (char) (bytes[i] & 0xff);
				i += 1;
			}

			cs[length++] = ch;
		}

		return new String(cs);
	}

	public static byte[] toUTF8ByteArray(String string) {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		char[] c = string.toCharArray();
		int i = 0;

		while (i < c.length) {
			char ch = c[i];

			if (ch < 0x0080) {
				bOut.write(ch);
			} else if (ch < 0x0800) {
				bOut.write(0xc0 | (ch >> 6));
				bOut.write(0x80 | (ch & 0x3f));
			}
			// surrogate pair
			else if (ch >= 0xD800 && ch <= 0xDFFF) {
				// in error - can only happen, if the Java String class has a
				// bug.
				if (i + 1 >= c.length) {
					throw new IllegalStateException("invalid UTF-16 codepoint");
				}
				char W1 = ch;
				ch = c[++i];
				char W2 = ch;
				// in error - can only happen, if the Java String class has a
				// bug.
				if (W1 > 0xDBFF) {
					throw new IllegalStateException("invalid UTF-16 codepoint");
				}
				int codePoint = (((W1 & 0x03FF) << 10) | (W2 & 0x03FF)) + 0x10000;
				bOut.write(0xf0 | (codePoint >> 18));
				bOut.write(0x80 | ((codePoint >> 12) & 0x3F));
				bOut.write(0x80 | ((codePoint >> 6) & 0x3F));
				bOut.write(0x80 | (codePoint & 0x3F));
			} else {
				bOut.write(0xe0 | (ch >> 12));
				bOut.write(0x80 | ((ch >> 6) & 0x3F));
				bOut.write(0x80 | (ch & 0x3F));
			}

			i++;
		}

		return bOut.toByteArray();
	}

	/**
	 * A locale independent version of toUpperCase.
	 * 
	 * @param string
	 *            input to be converted
	 * @return a US Ascii uppercase version
	 */
	public static String toUpperCase(String string) {
		boolean changed = false;
		char[] chars = string.toCharArray();

		for (int i = 0; i != chars.length; i++) {
			char ch = chars[i];
			if ('a' <= ch && 'z' >= ch) {
				changed = true;
				chars[i] = (char) (ch - 'a' + 'A');
			}
		}

		if (changed) {
			return new String(chars);
		}

		return string;
	}

	/**
	 * A locale independent version of toLowerCase.
	 * 
	 * @param string
	 *            input to be converted
	 * @return a US ASCII lowercase version
	 */
	public static String toLowerCase(String string) {
		boolean changed = false;
		char[] chars = string.toCharArray();

		for (int i = 0; i != chars.length; i++) {
			char ch = chars[i];
			if ('A' <= ch && 'Z' >= ch) {
				changed = true;
				chars[i] = (char) (ch - 'A' + 'a');
			}
		}

		if (changed) {
			return new String(chars);
		}

		return string;
	}

	public static byte[] toByteArray(String string) {
		byte[] bytes = new byte[string.length()];

		for (int i = 0; i != bytes.length; i++) {
			char ch = string.charAt(i);

			bytes[i] = (byte) ch;
		}

		return bytes;
	}

	public static String[] split(String input, char delimiter) {
		Vector v = new Vector();
		boolean moreTokens = true;
		String subString;

		while (moreTokens) {
			int tokenLocation = input.indexOf(delimiter);
			if (tokenLocation > 0) {
				subString = input.substring(0, tokenLocation);
				v.addElement(subString);
				input = input.substring(tokenLocation + 1);
			} else {
				moreTokens = false;
				v.addElement(input);
			}
		}

		String[] res = new String[v.size()];

		for (int i = 0; i != res.length; i++) {
			res[i] = (String) v.elementAt(i);
		}
		return res;
	}

	/**
	 * Clear all linebreaks and carriage returns from input text.
	 * 
	 * @return cleaned string
	 */
	public static String removeLineBreaks(String input) {
		if (input == null) {
			return null;
		}
		String tmp = input.replaceAll(NEWLINE_EXPR, " ");
		tmp = tmp.replaceAll(TAB_EXPR, " ");
		return tmp.replaceAll(RETURN_EXPR, "");
	}

	/**
	 * Return the text reformatted to have a max charwidth of maxWidth.
	 * 
	 * @param maxWidth
	 *            number of chars that the text can be wide.
	 */
	public static String getWrappedText(String input) {
		return getWrappedText(input, DEFAULT_WRAPLENGTH);
	}

	/**
	 * Return the text reformatted to have a max charwidth of maxWidth.
	 * 
	 * @param maxWidth
	 *            number of chars that the text can be wide.
	 */
	public static String getWrappedText(String input, int maxWidth) {

		if (input == null) {
			return "";
		}

		String[] text = input.split(NEWLINE_EXPR);
		String wrappedText = "";

		for (int i = 0; i < text.length; i++) {

			text[i] = text[i].replaceAll(RETURN_EXPR, "");

			if (text[i].length() == 0) {
				continue;
			}

			if (text[i].length() <= maxWidth) {
				wrappedText += text[i];

				if (i < text.length - 1) {
					wrappedText += NEWLINE_SEPARATOR;
				}
			} else {

				String tmp = text[i];

				while (tmp.length() > maxWidth) {

					for (int j = tmp.length() - 1; j >= 0; j--) {

						if (j < maxWidth) {

							char c = text[i].charAt(j);
							if (c == ',') {
								wrappedText += tmp.substring(0, j + 1);
								wrappedText += NEWLINE_SEPARATOR;
								tmp = tmp.substring(j + 1);
								break;
							}
							if (c == ' ') {
								wrappedText += tmp.substring(0, j + 1);
								wrappedText += NEWLINE_SEPARATOR;
								tmp = tmp.substring(j + 1);
								break;
							}
						}

						if (j == 0) {
							wrappedText += tmp.substring(0, maxWidth + 1);
							tmp = "";
							break;
						}
					}

				}

				wrappedText += tmp;
				wrappedText += NEWLINE_SEPARATOR;
			}

		}

		return wrappedText;
	}

	/**
	 * Trims whitespace from a string and compresses all internal whitespace
	 * down to a single space.
	 * 
	 * @param source
	 *            the string to compress
	 * @return the compressed string
	 */
	public static String compressWhitespace(CharSequence source) {
		return compressWhitespace(source, 0);
	}

	/**
	 * Trims whitespace from a string and compresses all internal whitespace
	 * down to a single space. Keeps the length of the string to at most
	 * maxLength, but if it truncates then it makes the last 3 characters an
	 * elipsis
	 * 
	 * @param source
	 *            the string to compress
	 * @param maxLength
	 *            maximum length of the result
	 * @return the compressed string
	 */
	public static String compressWhitespace(CharSequence source, int maxLength) {
		StringBuffer sb = new StringBuffer(source);

		// Trim leading whitespace
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0)))
			sb.deleteCharAt(0);

		boolean lastWasWhite = false;
		for (int i = 0; i < sb.length(); i++) {
			if (Character.isWhitespace(sb.charAt(i))) {
				if (lastWasWhite) {
					// Delete continguous whitespace
					sb.deleteCharAt(i);
					i--;
				} else {
					lastWasWhite = true;

					// Force all whitespace to be a space - IE no funny
					// characters for CR etc
					sb.setCharAt(i, ' ');
				}
			} else
				lastWasWhite = false;
		}

		// Optionally trim to size
		if (maxLength > 0 && sb.length() > maxLength) {
			if (maxLength > 3) {
				sb.delete(maxLength - 3, sb.length());
				sb.append("...");
			} else
				sb.delete(maxLength, sb.length());
		}

		return sb.toString().trim();
	}

	/**
	 * Replace all occurrences of replaceFrom in inputString with replaceTo.
	 * 
	 * @param inputString
	 *            string to update
	 * @param replaceFrom
	 *            occurrences to replace
	 * @param replaceTo
	 *            string that replaces occurrences
	 * @return
	 */
	public static String replaceChar(String inputString, char replaceFrom,
			String replaceTo) {

		if (inputString == null || inputString.length() == 0) {
			return inputString;
		}

		StringBuffer buffer = new StringBuffer();
		char[] input = inputString.toCharArray();

		for (int i = 0; i < input.length; i++) {

			if (input[i] == replaceFrom) {
				buffer.append(replaceTo);
			} else {
				buffer.append(input[i]);
			}
		}

		return buffer.toString();
	}

	// 去年字符串右边的空格
	public static String rtrim(String input) {
		if (input == null)
			return null;
		int i = 0;
		for (i = input.length() - 1; i >= 0
				&& Character.isWhitespace(input.charAt(i)); i--)
			;
		return input.substring(0, i + 1);
	}

	/**
	 * Computes and returns a formatted representation of the percent that the
	 * currentProgress represents in the totalSize.
	 * 
	 * @param currentProgress
	 * @param totalSize
	 * @return
	 */
	public static String getPercent(long currentProgress, long totalSize) {
		return (int) ((currentProgress * 100) / totalSize) + "%";
	}

	public static void main(String[] args) {
		System.out.println(rtrim("   cxs    ") + "user");
	}

	/**
	 * Replace reserved xml chars.
	 * 
	 * @param s
	 *            DOCUMENT_ME
	 * 
	 * @return the string
	 */
	private static String replaceReservedXMLChars(final String s) {
		String sOut = s;
		if (s.contains("&")) {
			sOut = sOut.replaceAll("&", "&amp;");
		}
		if (s.contains("\'")) {
			sOut = sOut.replaceAll("\'", "&apos;");
		}
		if (s.contains("\"")) {
			sOut = sOut.replaceAll("\"", "&quot;");
		}
		if (s.contains("<")) {
			sOut = sOut.replaceAll("<", "&lt;");
		}
		if (s.contains(">")) {
			sOut = sOut.replaceAll(">", "&gt;");
		}
		return sOut;
	}

	/**
	 * Make sure to reduce a string to the given size.
	 * 
	 * @param sIn
	 *            Input string, exemple: blabla
	 * @param iSize
	 *            max size, exemple: 3
	 * 
	 * @return bla...
	 */
	public static String getLimitedString(final String sIn, final int iSize) {
		String sOut = sIn;
		if (sIn.length() > iSize) {
			sOut = sIn.substring(0, iSize) + "...";
		}
		return sOut;
	}

	/**
	 * Checks if is char.
	 * 
	 * @param ucs4char
	 *            char to test
	 * 
	 * @return whether the char is valid, code taken from Apache sax
	 *         implementation
	 */
	public static boolean isChar(final int ucs4char) {
		return ((ucs4char >= 32) && (ucs4char <= 55295)) || (ucs4char == 10)
				|| (ucs4char == 9) || (ucs4char == 13)
				|| ((ucs4char >= 57344) && (ucs4char <= 65533))
				|| ((ucs4char >= 0x10000) && (ucs4char <= 0x10ffff));
	}

	/**
	 * Checks if is xml valid.
	 * 
	 * @param s
	 *            DOCUMENT_ME
	 * 
	 * @return whether given string is XML-valid
	 */
	public static boolean isXMLValid(final String s) {
		// check invalid chars
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);

			// check reserved chars
			if (-1 != "&\'\"<>".indexOf(c)) {
				return false;
			}

			if (!isChar(c)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Encode a string to unicode representation (ie \\uxxxx\\uyyyyy...)
	 * 
	 * @param in
	 *            string to encode
	 * 
	 * @return encoded string
	 */
	public static String encodeToUnicode(String in) {
		StringBuilder sb = new StringBuilder(in.length() * 5);
		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			byte hi = (byte) (c >>> 8);
			byte lo = (byte) (c & 0xff);
			sb.append("\\u");
			sb.append(byteToHex(hi) + byteToHex(lo));
		}
		return sb.toString();
	}

	/**
	 * Convert byte to hexadecimal representation.
	 * 
	 * @param b
	 *            DOCUMENT_ME
	 * 
	 * @return the string
	 */
	public static String byteToHex(byte b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
		return new String(array);
	}

	/**
	 * Returns a concatenation of argument array.
	 * 
	 * @param strings
	 *            strings to be concatened
	 * 
	 * @return concatenation of given strings
	 */
	public static String concat(Object... strings) {
		StringBuilder sb = new StringBuilder();
		for (Object element : strings) {
			sb.append(element);
		}
		return sb.toString();
	}

	public static String cap(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}

		char first = str.charAt(0);
		if (Character.isUpperCase(first)) {
			return str;
		}

		if (str.length() == 1) {
			return str.toUpperCase();
		}

		StringBuilder builder = new StringBuilder(str);
		builder.setCharAt(0, Character.toUpperCase(first));
		return builder.toString();
	}

	public static String uncap(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}

		char first = str.charAt(0);
		if (Character.isLowerCase(first)) {
			return str;
		}

		if (str.length() == 1) {
			return str.toLowerCase();
		}

		StringBuilder builder = new StringBuilder(str);
		builder.setCharAt(0, Character.toLowerCase(first));
		return builder.toString();
	}

	public static int occurrences(String str, char c) {
		int count = 0;
		for (int i = 0; (i = str.indexOf(c, i)) != -1; ++i) {
			++count;
		}

		return count;
	}

	public static int occurrences(String str, String c) {
		int count = 0;
		for (int i = 0; (i = str.indexOf(c, i)) != -1; i += c.length()) {
			++count;
		}

		return count;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

}
