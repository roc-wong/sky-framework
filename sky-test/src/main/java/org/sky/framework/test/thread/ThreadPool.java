package org.sky.framework.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(4);
		int i = 0;
		while (i < 50) {
			threadPoolExecutor.submit(new Runnable() {
				@Override
				public void run() {
					try {
						String out = "消费者线程 -> " + Thread.currentThread().getName() + " <- 开始消费数据" + "-----消费者线程id -> " + Thread.currentThread().getId();
						
						System.out.println(out);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			i++;
		}
		
	}

}
