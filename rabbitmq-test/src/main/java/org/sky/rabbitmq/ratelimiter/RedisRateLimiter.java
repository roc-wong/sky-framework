package org.sky.rabbitmq.ratelimiter;

import org.sky.rabbitmq.constant.RedisConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.nosql.redis.JedisTemplate;
import org.springside.modules.nosql.redis.pool.JedisPool;
import org.springside.modules.nosql.redis.pool.JedisPoolBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author roc
 * @date 2018/01/23
 */
public class RedisRateLimiter implements RateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRateLimiter.class);

    private static final String ZERO = "0";

    private static final String NOT_EXISTS = "NX";

    private static final String P_EXPIRE = "PX";

    private JedisTemplate jedisTemplate;

    /**
     * 限流标识名称，redis key
     */
    private String rateLimiterName;

    /**
     * 存活时间
     */
    private int timeToLive;

    /**
     * 每秒查询率
     */
    private int maxQPS;

    private Random random = new Random();

    public RedisRateLimiter(String rateLimiterName, int maxQPS, int time, TimeUnit timeUnit) {
        this.rateLimiterName = rateLimiterName;
        this.maxQPS = maxQPS;
        JedisPool jedisPool = new JedisPoolBuilder().setDirectHost("10.0.30.66:6379").setPoolSize(1000).setPoolName("sky").buildPool();
        jedisTemplate = new JedisTemplate(jedisPool);
        timeToLive = (int) timeUnit.toMillis(time);
    }

    @Override
    public boolean tryAcquire(final int permits) {
        return acquireSync(permits).isAcquired();
    }

    @Override
    public void acquire(final int permits) throws InterruptedException {
        LimiterResult limiterResult;
        do {
            limiterResult = acquireSync(permits);
            if (!limiterResult.isAcquired()) {
                long sleepTime = limiterResult.getRemainderTTL() + random.nextInt(5);
                Thread.sleep(sleepTime);
            }
        } while (!limiterResult.isAcquired());
    }

    public LimiterResult acquireSync(final int permits) {
        return jedisTemplate.execute(new JedisTemplate.JedisAction<LimiterResult>() {

            @Override
            public LimiterResult action(Jedis jedis) {

                Transaction transaction = jedis.multi();
                transaction.set(rateLimiterName, ZERO, NOT_EXISTS, P_EXPIRE, timeToLive);
                Response<Long> currentQPS = transaction.incrBy(rateLimiterName, permits);
                Response<Long> keyTTL = transaction.pttl(rateLimiterName);
                transaction.exec();

                Long currentPermit = currentQPS.get();
                Long remainderTTL = keyTTL.get();

                boolean acquired = currentPermit <= maxQPS;

                if (!acquired && remainderTTL == RedisConstant.NOT_SET) {
                    LOGGER.error("Warning! Set ttl false, key={}, current ttl={}", rateLimiterName, remainderTTL);
                    jedis.expire(rateLimiterName, timeToLive);
                    remainderTTL = (long) timeToLive;
                }

                LOGGER.debug("rateLimiterName={}, passed={}, remainderTTL={}, time={}",
                        rateLimiterName, acquired ? "true" : "false",
                        remainderTTL, currentPermit);
                return LimiterResult.newBuilder().setAcquired(acquired).setRemainderTTL(remainderTTL).build();
            }
        });
    }

}
