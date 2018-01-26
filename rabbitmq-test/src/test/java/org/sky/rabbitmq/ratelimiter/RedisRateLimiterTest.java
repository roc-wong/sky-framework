package org.sky.rabbitmq.ratelimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author roc
 * @date 2018/01/26
 */
public class RedisRateLimiterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRateLimiterTest.class);

    private RateLimiter rateLimiter;

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    @BeforeTest
    public void init() {
        String rateLimiterName = "rateLimiterROC";
        rateLimiter = new RedisRateLimiter(rateLimiterName, 50, 5, TimeUnit.SECONDS);
    }


    @Test(threadPoolSize = 10, invocationCount = 60)
    public void testTryAcquire() throws Exception {
        boolean acquired = rateLimiter.tryAcquire(1);
        if(acquired){
            LOGGER.info("thread {} acquire the permit {} times.", Thread.currentThread().getId(), atomicInteger.getAndIncrement());
        }
    }

    @Test(threadPoolSize = 10, invocationCount = 60)
    public void testAcquire() throws Exception {
        rateLimiter.acquire(1);
        LOGGER.info("thread {} acquire the permit {} times.", Thread.currentThread().getId(), atomicInteger.getAndIncrement());

    }

}