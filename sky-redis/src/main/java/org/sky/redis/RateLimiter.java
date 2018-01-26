package org.sky.redis;

/**
 * @author roc
 * @date 2018/01/23
 */
public class RateLimiter {
//
//    private static final int ZERO = 0;
//
//    private static final int ONE = 1;
//
//    private RedisClient redisClient;
//    private String rateLimiterName;
//
//    public RateLimiter(String rateLimiterName) {
//        this.rateLimiterName = rateLimiterName;
//        RedisClientConfig config = new RedisClientConfig();
//        config.setAddress("10.0.30.66:6379");
//        redisClient = RedisClient.create(config);
//    }
//
//    public boolean tryAcquire(int permits, int time, TimeUnit timeUnit) {
//        long timeToLive = timeUnit.toMillis(time);
//        RedisConnection redisConnection = redisClient.connect();
//        redisConnection.sync(RedisCommands.MULTI);
//        redisConnection.sync(IntegerCodec.INSTANCE, RedisCommands.SET, rateLimiterName, ZERO, "NX", "EX", timeToLive);
//        redisConnection.sync(IntegerCodec.INSTANCE, RedisCommands.INCRBY, rateLimiterName, ONE);
//        redisConnection.sync(RedisCommands.EXEC);
//    }


}
