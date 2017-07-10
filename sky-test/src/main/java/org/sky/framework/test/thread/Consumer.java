package org.sky.framework.test.thread;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

	private BlockingQueue<Integer> queen;

	public Consumer(BlockingQueue<Integer> queen) {
		super();
		this.queen = queen;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Consumed -----  : " + Thread.currentThread().getName() + " " + queen.take());
			} catch (InterruptedException ex) {
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

	}

}
