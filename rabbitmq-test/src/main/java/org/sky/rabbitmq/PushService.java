package org.sky.rabbitmq;

import org.sky.rabbitmq.ratelimiter.RedisRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author roc
 * @date 2018/01/23
 */
public class PushService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushService.class);

    private RedisRateLimiter redisRateLimiter;

    public PushService() {
        redisRateLimiter = new RedisRateLimiter("rateLimiterTest", 50, 5, TimeUnit.SECONDS);
    }

    public void push(Message message) {
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
