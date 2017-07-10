package org.sky.framework.test.thread.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Task task = new Task();
		FutureTask<Integer> future = new FutureTask<Integer>(task);
		executorService.submit(future);
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

