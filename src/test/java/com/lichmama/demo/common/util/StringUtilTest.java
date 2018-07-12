package com.lichmama.demo.common.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class StringUtilTest extends TestCase {
	private static final String MessageQueue = "com:lichmama:demo";
	
	@Test
	public void testRedisClient() {
		RedisClient.lpush(MessageQueue, "lebron james");
		Object value = RedisClient.rpop(MessageQueue);
		Assert.assertEquals("lebron james", value);
	}
}
