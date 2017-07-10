package org.sky.framework.test.thread.statici;

public class StaticThread1 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		StaticAction1.incValue();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new StaticThread1()).start();
		}
		try {
			Thread.sleep(3000); // 预留足够的时间让上面的线程跑完
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(StaticAction.i);
	}
}