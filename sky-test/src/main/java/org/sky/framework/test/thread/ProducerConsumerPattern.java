package org.sky.framework.test.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumerPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<Integer>();

		Thread producerThread = new Thread(new Producer(blockingQueue));
		Thread consumerThread1 = new Thread(new Consumer(blockingQueue));
		Thread consumerThread2 = new Thread(new Consumer(blockingQueue));
		Thread consumerThread3 = new Thread(new Consumer(blockingQueue));
		Thread consumerThread4 = new Thread(new Consumer(blockingQueue));
		Thread consumerThread5 = new Thread(new Consumer(blockingQueue));
		Thread consumerThread6 = new Thread(new Consumer(blockingQueue));

		producerThread.start();
		consumerThread2.start();
		consumerThread3.start();
		consumerThread4.start();
		consumerThread1.start();
		consumerThread5.start();
		consumerThread6.start();

	}

}
