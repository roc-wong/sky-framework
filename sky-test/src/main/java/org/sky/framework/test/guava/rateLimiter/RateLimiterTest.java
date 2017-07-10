package org.sky.framework.test.guava.rateLimiter;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

public class RateLimiterTest {

	public static final int THREAD_SLEEP_UNIT = 100;
	public static final int QUERY_TIME_OUT = 3000;

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		LocalFollowControl followControl = new LocalFollowControl(3);
		List<Task> tasks = Lists.newArrayList();
		for (int i = 0; i < 20; i++) {
			Task task = new Task(followControl);
			tasks.add(task);
		}
		List<Future<String>> futures = executorService.invokeAll(tasks);

		for (Future<String> future : futures) {
			System.out.println(future.get());
		}
		executorService.shutdown();
	}

}

class Task implements Callable<String> {

	private LocalFollowControl followControl;

	public Task(LocalFollowControl followControl) {
		this.followControl = followControl;
	}

	@Override
	public String call() throws Exception {

		return followControl.execute(new Callable<String>() {
			@Override
			public String call() throws Exception {
				StopWatch stopWatch = new StopWatch();
				stopWatch.begin();
				int sleepMilliSecondsTotal = 0;
				FibonacciSequence fibonacciSequence = new FibonacciSequence();
				while (true) {
					if (new Random().nextInt(100) % 5 == 0) {
						return Thread.currentThread().getId() + ", 共休眠 " + sleepMilliSecondsTotal + " acquire token";
					} else {
						if (stopWatch.endAsMilliSecond().intValue() < RateLimiterTest.QUERY_TIME_OUT) {
							// 继续等待，然后继续轮询
							int sleepMilliSeconds = fibonacciSequence.next() * RateLimiterTest.THREAD_SLEEP_UNIT;
							sleepMilliSecondsTotal += sleepMilliSeconds;
							System.out.println(Thread.currentThread().getId() + " 本次休眠 " + sleepMilliSeconds + " s" + ",共休眠 " + sleepMilliSecondsTotal + "s");
							Thread.sleep(sleepMilliSeconds);
						}
					}

				}

			}
		});
	}
}