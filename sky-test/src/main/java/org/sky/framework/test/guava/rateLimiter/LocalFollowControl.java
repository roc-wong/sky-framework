package org.sky.framework.test.guava.rateLimiter;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 */
public class LocalFollowControl {

	private int maxControl;
	private AtomicInteger atomicInteger = new AtomicInteger(0);

	public LocalFollowControl(int maxControl) {
		this.maxControl = maxControl;
	}

	public boolean acquireToken() {
		if (atomicInteger.get() < maxControl) {
			int current = atomicInteger.get();
			if (atomicInteger.compareAndSet(current, current + 1)) {
				return true;
			}
		}
		return false;
	}

	public void returnToken() {
		atomicInteger.decrementAndGet();
	}

	public <T> T execute(Callable<T> callable) {
		if (acquireToken()) {
			try {
				return callable.call();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				returnToken();
			}
		}
		return null;
	}

	public void execute(Runnable runnable) {
		if (acquireToken()) {
			runnable.run();
		}
	}

}
