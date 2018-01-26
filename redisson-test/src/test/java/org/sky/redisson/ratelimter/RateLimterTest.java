package org.sky.redisson.ratelimter;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author roc
 * @date 2018/01/22
 */
public class RateLimterTest {

    private Redisson redissonClient;

    @BeforeTest
    public void init(){
        Config config = new Config();
        config.useSingleServer().setAddress("10.0.30.66:6379");
        redissonClient = (Redisson) Redisson.create(config);
    }

    @Test
    public void testLimter(){

//        RRateLimiter rateLimiter = redissonClient.getRateLimiter("myRateLimiter");

    }

}