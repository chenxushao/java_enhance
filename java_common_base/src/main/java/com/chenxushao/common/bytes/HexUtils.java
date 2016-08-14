package com.chenxushao.common.bytes;

public class HexUtils {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	
	public static String encodeHexStr(byte[] buf, int pos, int len) {
		StringBuffer hex = new StringBuffer();
		while (len-- > 0) {
			byte ch = buf[pos++];
			int d = (ch >> 4) & 0xf;
			hex.append((char) (d >= 10 ? 'a' - 10 + d : '0' + d));
			d = ch & 0xf;
			hex.append((char) (d >= 10 ? 'a' - 10 + d : '0' + d));
		}
		return hex.toString();
	}
}
