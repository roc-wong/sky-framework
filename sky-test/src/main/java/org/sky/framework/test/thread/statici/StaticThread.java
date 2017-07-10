package org.sky.framework.test.thread.statici;

public class StaticThread implements Runnable {
	@Override
	public void run() {
		System.out.print(Thread.currentThread().getId() + " -->");
		StaticAction.print();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new StaticThread()).start();
		}
	}
}
