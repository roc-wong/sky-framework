package org.sky.framework.test.guava.rateLimiter;

/**
 */
public class FibonacciSequence {

	private int i;

	public int next() {
		++i;
		return get(i);
	}

	public int get(int i) {
		switch (i) {
		case 0:
			return 1;
		case 1:
			return 1;
		default:
			return get(i - 1) + get(i - 2);
		}
	}
}
