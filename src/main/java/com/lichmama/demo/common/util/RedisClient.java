package com.lichmama.demo.common.util;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.lichmama.demo.core.spring.SpringContextHolder;

public final class RedisClient {

	private static RedisTemplate<String, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");

	private RedisClient() {
	}

	public static void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public static void set(String key, Object value, long seconds) {
		redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
	}

	public static Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public static void expire(String key, long seconds) {
		redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	public static long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	public static boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public static void delete(String key) {
		redisTemplate.delete(key);
	}

	public static void delete(String... keys) {
		redisTemplate.delete(Arrays.asList(keys));
	}

	public static long incr(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	public static long decr(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	public static void hset(String key, String field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}
	
	public static void hset(String key, String field, Object value, long seconds) {
		hset(key, field, value);
		expire(key, seconds);
	}

	public static Object hget(String key, String field) {
		return redisTemplate.opsForHash().get(key, field);
	}
}
