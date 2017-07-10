package org.sky.framework.test.thread;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

	private BlockingQueue<Integer> queen;

	public Producer(BlockingQueue<Integer> queen) {
		super();
		this.queen = queen;
	}

	@Override
	public void run() {

		try {
			int i = 0;
			while (i < 20) {
				i++;
				System.out.println(Thread.currentThread().getName() + " Produced: " + i);
				queen.put(i);
			}
		} catch (InterruptedException e) {
			Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null,
					e);
		}
	}

}
