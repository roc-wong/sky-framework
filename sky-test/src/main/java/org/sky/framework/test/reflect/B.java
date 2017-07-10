package org.sky.framework.test.reflect;

public class B extends A<String> {

	public static void main(String[] args) {
		B bb = new B();
		System.out.println(bb.getTClass(0));// 即答案
	}
}