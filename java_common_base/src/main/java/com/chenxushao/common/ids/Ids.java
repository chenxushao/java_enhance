package com.chenxushao.common.ids;

import java.security.SecureRandom;
import java.util.Map;
import java.util.UUID;

import com.chenxushao.common.crypto.Encodes;
import com.google.common.collect.Maps;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 */
public class Ids {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		Map<Long, Long> map = Maps.newHashMap();
		for (int i = 0; i < 10000000; i++) {
			long key = randomLong();
			map.put(key, key);
		}
		System.out.println("times : " + (System.currentTimeMillis() - begin)
				/ 1000);
		System.out.println(map.size());
	}
}
