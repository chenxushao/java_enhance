package com.chenxushao.java.concurrent.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 随机数工具
 * 1. 获取无锁的ThreadLocalRandom, 性能较佳的SecureRandom
 * 2. 保证没有负数陷阱，也能更精确设定范围的nextInt/nextLong/nextDouble (copy from Common Lang
 * RandomUtils，但默认使用性能较优的ThreadLocalRandom，并可配置其他的Random)
 * 3. 随机字符串
 */
public final class RandomUtil {
	
	private static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/////////////////// 获取Random实例//////////////
	/**
	 * 返回无锁的ThreadLocalRandom
	 */
	public static Random threadLocalRandom() {
		return java.util.concurrent.ThreadLocalRandom.current();
	}

	/**
	 * 使用性能更好的SHA1PRNG, Tomcat的sessionId生成也用此算法.
	 * 但JDK7中，需要在启动参数加入 -Djava.security=file:/dev/./urandom （中间那个点很重要）
	 */
	public static SecureRandom secureRandom() {
		try {
			return SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			return new SecureRandom();
		}
	}

	////////////////// nextInt 相关/////////
	/**
	 * 返回0到Intger.MAX_VALUE的随机Int, 使用内置全局普通Random.
	 */
	public static int nextInt() {
		return nextInt(threadLocalRandom());
	}

	/**
	 * 返回0到Intger.MAX_VALUE的随机Int, 可传入SecureRandom或ThreadLocalRandom
	 */
	public static int nextInt(Random random) {
		int n = random.nextInt();
		if (n == Integer.MIN_VALUE) {
			n = 0; // corner case
		} else {
			n = Math.abs(n);
		}

		return n;
	}

	/**
	 * 返回0到max的随机Int, 使用内置全局普通Random.
	 */
	public static int nextInt(int max) {
		return nextInt(threadLocalRandom(), max);
	}

	/**
	 * 返回0到max的随机Int, 可传入SecureRandom或ThreadLocalRandom
	 */
	public static int nextInt(Random random, int max) {
		return random.nextInt(max);
	}

	/**
	 * 返回min到max的随机Int, 使用内置全局普通Random.
	 * min必须大于0.
	 */
	public static int nextInt(int min, int max) {
		return nextInt(threadLocalRandom(), min, max);
	}

	/**
	 * 返回min到max的随机Int,可传入SecureRandom或ThreadLocalRandom.
	 * min必须大于0.
	 * JDK本身不具有控制两端范围的nextInt，因此参考Commons Lang RandomUtils的实现, 不直接复用是因为要传入Random实例
	 */
	public static int nextInt(Random random, int min, int max) {
		Validate.isTrue(max >= min, "Start value must be smaller or equal to end value.");
		MoreValidate.nonNegative("min", min);

		if (min == max) {
			return min;
		}

		return min + random.nextInt(max - min);
	}

	////////////////// long 相关/////////
	/**
	 * 返回0－Long.MAX_VALUE间的随机Long, 使用内置全局普通Random.
	 */
	public static long nextLong() {
		return nextLong(threadLocalRandom());
	}

	/**
	 * 返回0－Long.MAX_VALUE间的随机Long, 可传入SecureRandom或ThreadLocalRandom
	 */
	public static long nextLong(Random random) {
		long n = random.nextLong();
		if (n == Long.MIN_VALUE) {
			n = 0; // corner case
		} else {
			n = Math.abs(n);
		}
		return n;
	}

	/**
	 * 返回0－max间的随机Long, 使用内置全局普通Random.
	 */
	public static long nextLong(long max) {
		return nextLong(threadLocalRandom(), 0, max);
	}

	/**
	 * 返回0-max间的随机Long, 可传入SecureRandom或ThreadLocalRandom
	 */
	public static long nextLong(Random random, long max) {
		return nextLong(random, 0, max);
	}

	/**
	 * 返回min－max间的随机Long, 使用内置全局普通Random.
	 * min必须大于0.
	 */
	public static long nextLong(long min, long max) {
		return nextLong(threadLocalRandom(), min, max);
	}

	/**
	 * 返回min-max间的随机Long,可传入SecureRandom或ThreadLocalRandom.
	 * min必须大于0.
	 * JDK本身不具有控制两端范围的nextLong，因此参考Commons Lang RandomUtils的实现, 不直接复用是因为要传入Random实例
	 * @see org.apache.commons.lang3.RandomUtils#nextLong(long, long)
	 */
	public static long nextLong(Random random, long min, long max) {
		Validate.isTrue(max >= min, "Start value must be smaller or equal to end value.");
		MoreValidate.nonNegative("min", min);

		if (min == max) {
			return min;
		}

		return (long) (min + ((max - min) * random.nextDouble()));
	}

	///////// Double //////
	/**
	 * 返回0-之间的double
	 */
	public static double nextDouble() {
		return nextDouble(threadLocalRandom(), 0, Double.MAX_VALUE);
	}

	/**
	 * 返回0-Double.MAX之间的double
	 */
	public static double nextDouble(Random random) {
		return nextDouble(random, 0, Double.MAX_VALUE);
	}

	/**
	 * 返回0-max之间的double
	 * 注意：与JDK默认返回0-1的行为不一致.
	 */
	public static double nextDouble(double max) {
		return nextDouble(threadLocalRandom(), 0, max);
	}

	/**
	 * 返回0-max之间的double
	 */
	public static double nextDouble(Random random, double max) {
		return nextDouble(random, 0, max);
	}

	/**
	 * 返回min-max之间的double
	 */
	public static double nextDouble(final double min, final double max) {
		return nextDouble(threadLocalRandom(), min, max);
	}

	/**
	 * 返回min-max之间的double
	 */
	public static double nextDouble(Random random, final double min, final double max) {
		Validate.isTrue(max >= min, "Start value must be smaller or equal to end value.");
		MoreValidate.nonNegative("min", min);

		if (min == max) {
			return min;
		}

		return min + ((max - min) * random.nextDouble());
	}
	//////////////////// String/////////

	/**
	 * 随机字母或数字，固定长度
	 */
	public static String randomStringFixLength(int length) {
		return RandomStringUtils.random(length, 0, 0, true, true, null, threadLocalRandom());
	}

	/**
	 * 随机字母或数字，固定长度
	 */
	public static String randomStringFixLength(Random random, int length) {
		return RandomStringUtils.random(length, 0, 0, true, true, null, random);
	}

	/**
	 * 随机字母或数字，随机长度
	 */
	public static String randomStringRandomLength(int minLength, int maxLength) {
		return RandomStringUtils.random(nextInt(minLength, maxLength), 0, 0, true, true, null, threadLocalRandom());
	}

	/**
	 * 随机字母或数字，随机长度
	 */
	public static String randomStringRandomLength(Random random, int minLength, int maxLength) {
		return RandomStringUtils.random(nextInt(random, minLength, maxLength), 0, 0, true, true, null, random);
	}

	/**
	 * 随机字母，固定长度
	 */
	public static String randomLetterFixLength(int length) {
		return RandomStringUtils.random(length, 0, 0, true, false, null, threadLocalRandom());
	}

	/**
	 * 随机字母，固定长度
	 */
	public static String randomLetterFixLength(Random random, int length) {
		return RandomStringUtils.random(length, 0, 0, true, false, null, random);
	}

	/**
	 * 随机字母，随机长度
	 */
	public static String randomLetterRandomLength(int minLength, int maxLength) {
		return RandomStringUtils.random(nextInt(minLength, maxLength), 0, 0, true, false, null, threadLocalRandom());
	}

	/**
	 * 随机字母，随机长度
	 */
	public static String randomLetterRandomLength(Random random, int minLength, int maxLength) {
		return RandomStringUtils.random(nextInt(random, minLength, maxLength), 0, 0, true, false, null, random);
	}

	/**
	 * 随机ASCII字符(含字母，数字及其他符号)，固定长度
	 */
	public static String randomAsciiFixLength(int length) {
		return RandomStringUtils.random(length, 32, 127, false, false, null, threadLocalRandom());
	}

	/**
	 * 随机ASCII字符(含字母，数字及其他符号)，固定长度
	 */
	public static String randomAsciiFixLength(Random random, int length) {
		return RandomStringUtils.random(length, 32, 127, false, false, null, random);
	}

	/**
	 * 随机ASCII字符(含字母，数字及其他符号)，随机长度
	 */
	public static String randomAsciiRandomLength(int minLength, int maxLength) {
		return RandomStringUtils.random(nextInt(minLength, maxLength), 32, 127, false, false, null,
				threadLocalRandom());
	}

	/**
	 * 随机ASCII字符(含字母，数字及其他符号)，随机长度
	 */
	public static String randomAsciiRandomLength(Random random, int minLength, int maxLength) {
		return RandomStringUtils.random(nextInt(random, minLength, maxLength), 32, 127, false, false, null, random);
	}
	
	
	//pre method 
	
	/** 
	* @Title: generateRandomBytes 
	* @Description: 随机生成字节数组
	* @param  num_to_generate number of bytes to generate
	* @return byte[]
	*/
	public static byte[] generateRandomBytes(int num_to_generate) {
		Random random = new Random(System.currentTimeMillis());
		byte[] id = new byte[num_to_generate];
		random.nextBytes(id);
		return id;
	}

	/**
	 * @Title: generateRandomAlphanumerics
	 * @Description: 随机生成指定长度的字符串
	 * @param length
	 * @return String
	 */
	public static String generateRandomAlphanumerics(int length) {
		if (length < 1) {
			return "";
		}
		Random random = new Random(System.currentTimeMillis());
		StringBuilder buff = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int pos = (int) (random.nextDouble() * alphabet.length());
			buff.append(alphabet.charAt(pos));
		}
		return buff.toString();
	}

	/** 
	* @Title: generateRandomPlusMinus
	* @Description: 随时生成1或-1
	* @return int  +1 or -1
	*/
	public static int generateRandomPlusMinus() {
		Random random = new Random(System.currentTimeMillis());
		return random.nextBoolean() ? -1 : 1;
	}

	/** 
	* @Title: nextFloat 
	* @Description: 随机生成float数值
	* @return float
	*/
	public static float nextFloat() {
		Random random = new Random(System.currentTimeMillis());
		return random.nextFloat();
	}
	
	/** 
	* @Title: nextBoolean 
	* @Description: 随机生成布尔值
	* @return boolean
	*/
	public static boolean nextBoolean() {
		Random random = new Random(System.currentTimeMillis());
		return random.nextBoolean();
	}

	public static byte[] nextSecureHash() {
	    SecureRandom secureRandom = new SecureRandom();
		byte[] hash = new byte[20];
		secureRandom.nextBytes(hash);
		return (hash);
	}

	public static byte[] nextHash() {
		Random random = new Random(System.currentTimeMillis());
		byte[] hash = new byte[20];
		random.nextBytes(hash);
		return (hash);
	}

	/** 
	* @Title: nextByte 
	* @Description: 随机生成字节数值
	* @return byte
	*/
	public static byte nextByte() {
		Random random = new Random(System.currentTimeMillis());
		return (byte) random.nextInt();
	}

	/** 
	* @Title: generateRandomIntUpto 
	* @Description:  随机生成 0 and max-1范围的整型数值
	* @param max
	* @return int   random int between 0 and max-1. e.g. param of 10 returns 0->9
	*/
	public static int generateRandomIntUpto(int max) {
		return nextInt(max);
	}

	/** 
	* @Title: generateRandomIntBetween 
	* @Description: 返回指定范围的整型数值
	* @param min
	* @param max
	* @return int  random int between min and max, e.g params of [5,7] returns 5,6 or 7
	*/
	public static int generateRandomIntBetween(int min, int max) {
		return min + generateRandomIntUpto(max + 1 - min);
	}

	/**
	 * 随机指定范围内N个不重复的数
	 * 最简单最基本的方法
	 * @param min 指定范围最小值（包含）
	 * @param max 指定范围最大值(不包含)
	 * @param n 随机数个数
	 */
	public static int[] getNRandom(int min, int max, int n){
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		for(int i = 0 ; i < n ; i++){
			result[i] = -9999;
		}
		int count = 0;
		while(count < n) {
			int num = (int) ((Math.random() * (max - min)) + min);
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if(num == result[j]){
					flag = false;
					break;
				}
			}
			if(flag){
				result[count] = num;
				count++;
			}
		}
		return result;
	}
}
