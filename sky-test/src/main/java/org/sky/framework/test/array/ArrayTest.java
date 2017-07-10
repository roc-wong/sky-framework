package org.sky.framework.test.array;

import java.util.LinkedList;
import java.util.List;

public class ArrayTest {
	
	
	
	public static void main(String[] args) {
			
		List<Object> params = new LinkedList<>();
		
		params.add("4, 5");
		params.add("1");
		params.add("2");
		params.add("3");
		params.add("6, 7");
		
		Object[] a = params.toArray();
		
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		
		System.out.println("调用可变参数");
		
		variableParameters(params.toArray());
	}
	
	
	public static void variableParameters(Object... args){
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
}
