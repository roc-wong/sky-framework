package org.sky.framework.test.innerclass;

public class Callee3 extends MyIncrement {
	private int i = 0;

	private void incr() {
		i++;
		System.out.println(i);
	}

	private class Closure implements Incrementable {
		public void increment() {
			incr();
		}
	}

	Incrementable getCallbackReference() {
		return new Closure();
	}

	public static void main(String[] args) {
		Callee3 callee3 = new Callee3();
		Incrementable incrementable = callee3.getCallbackReference();
		incrementable.increment();
	}

}