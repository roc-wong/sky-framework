package org.sky.framework.test.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Task task = new Task();
		Future<Integer> future = executorService.submit(task);
		executorService.shutdown();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("主线程在执行任务");
		
		try {
			System.out.println("task运行结果" + future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 System.out.println("所有任务执行完毕");
	}

}

class Task implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {

		System.out.println("子线程开始运行00000000");
		Thread.sleep(2000);
		int sum = 0;
		for (int i = 0; i < 300; i++) {
			sum += i;
		}
		return sum;
	}

}
