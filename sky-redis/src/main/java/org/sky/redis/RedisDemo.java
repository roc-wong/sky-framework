package org.sky.redis;

import java.util.Set;

import org.springside.modules.nosql.redis.JedisTemplate;
import org.springside.modules.nosql.redis.pool.JedisPool;
import org.springside.modules.nosql.redis.pool.JedisPoolBuilder;

/**
 * Hello world!
 * 
 */
public class RedisDemo {
	
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		JedisPool jedisPool = new JedisPoolBuilder().setDirectHost("127.0.0.1:6379").setPoolSize(10).setPoolName("sky").buildPool();
		
		JedisTemplate jedisTemplate = new JedisTemplate(jedisPool);
		
		Set<String> keys = jedisTemplate.keys("*");
		System.out.println(keys);
		
		String name = jedisTemplate.get("name");
		System.out.println(name);
		
		String cursor ="";
		String pattern ="";
		int count =100;
		jedisTemplate.scan(cursor, pattern, count);
		
	}
}
