package org.sky.rabbitmq.ratelimiter;

/**
 * @author roc
 * @date 2018/01/24
 */
public interface RateLimiter {

    /**
     * Acquires a permit only if one is available at the
     * time of invocation.
     *
     * <p>Acquires a permit, if one is available and returns immediately,
     * with the result {@code LimiterResult#acquired is true},
     * reducing the number of available permits by one.
     *
     * <p>If no permit is available then this method will return
     * immediately with the value {@code false}.
     *
     * @param permits the number of permits to acquire
     * @return acquire result {@code true} if a permit was acquired,
     * and {@code false is false} otherwise
     */
    boolean tryAcquire(int permits);

    /**
     * Acquires a permit from this method, blocking until one is
     * available, or the thread is {@linkplain Thread#interrupt interrupted}.
     *
     * <p>Acquires a permit, if one is available and returns immediately,
     * reducing the number of available permits by one.
     *
     * @param permits the number of permits to acquire
     */
    void acquire(int permits) throws InterruptedException;
}
