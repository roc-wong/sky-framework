package org.sky.framework.test.guava.rateLimiter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

/**
 * Created by caosh on 2015/6/30.
 */
public class StopWatch {

	public static <T> T watch(Callable<T> callable, String description) {
		long beginTime = System.nanoTime();
		T ret = null;
		try {
			ret = callable.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long diffTime = System.nanoTime() - beginTime;
		System.out.println(String.format("%s: %dus", description, diffTime / 1000));
		return ret;
	}

	public static void watch(Runnable runnable, String description) {
		long beginTime = System.nanoTime();
		runnable.run();
		long diffTime = System.nanoTime() - beginTime;
		System.out.println(String.format("%s: %dus", description, diffTime / 1000));
	}
	
	private long beginTime;

	public StopWatch begin() {
		beginTime = System.nanoTime();
		return this;
	}

	public long end() {
		return System.nanoTime() - beginTime;
	}

	public BigDecimal endAsMicroSecond() {
		return BigDecimal.valueOf(end()).divide(BigDecimal.valueOf(1000), 3, RoundingMode.HALF_UP);
	}

	public BigDecimal endAsMilliSecond() {
		return BigDecimal.valueOf(end()).divide(BigDecimal.valueOf(1000 * 1000), 3, RoundingMode.HALF_UP);
	}
}
