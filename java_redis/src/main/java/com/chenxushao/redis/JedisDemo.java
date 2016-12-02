package com.chenxushao.redis;

import redis.clients.jedis.Jedis;

public class JedisDemo {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		jedis.set("key1", "I am value 1");
		String ss = jedis.get("key1");
		System.out.println(ss);
	}
}