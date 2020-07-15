package org.sky.framework.test.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author roc
 * @since 2019/8/20 19:28
 */
public class GetAccessTokenTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAccessTokenTest.class);

    private int expiresInSeconds = 50;
    private LongAdder longAdder = new LongAdder();

    private volatile long expiresTime;
    private volatile String accessToken;

    private final Object globalAccessTokenRefreshLock = new Object();

    @Test(threadPoolSize = 10, invocationCount = 20)
    public void testMultiThreadGet() {
        String accessToken = getAccessToken();
        String threadId = Thread.currentThread().getId() + "_" + Thread.currentThread().getName();
        LOGGER.info("Thread={}, AccessToken={}", threadId, accessToken);
    }

    private String getAccessToken() {
        if(!isAccessTokenExpired()){
            return accessToken;
        }
        synchronized (this.globalAccessTokenRefreshLock){
            if(!isAccessTokenExpired()){
                return accessToken;
            }
            longAdder.increment();
            String newAccessToken = "token_" + longAdder.longValue();
            updateAccessToken(newAccessToken, expiresInSeconds);
        }
        return accessToken;
    }

    private boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > expiresTime;
    }

    public void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 20) * 1000L;
    }

}
