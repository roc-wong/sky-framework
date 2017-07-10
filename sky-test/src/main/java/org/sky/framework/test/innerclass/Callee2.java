package org.sky.framework.test.innerclass;

public class Callee2 extends MyIncrement implements Incrementable,
		Incrementable1 {

	public static void main(String[] args) {
		Incrementable incrementable = new Callee2();
		incrementable.increment();

	}
}