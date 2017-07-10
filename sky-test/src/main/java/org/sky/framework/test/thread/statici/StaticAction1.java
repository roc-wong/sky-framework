package org.sky.framework.test.thread.statici;

public class StaticAction1 {
	public static int i = 0;

	public synchronized static void incValue() {
		int temp = StaticAction.i;
		temp++;
		StaticAction.i = temp;
	}
}
