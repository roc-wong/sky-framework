package org.sky.framework.test.guava.stopwatch;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Created by roc on 8/7/2017.
 */
public class StopwatchTest {

	public static void main(String[] args) {

		Stopwatch stopwatch = Stopwatch.createStarted();


		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long elapse = stopwatch.elapsed(TimeUnit.SECONDS);

		System.out.println("elapse=" + elapse);
	}

}
