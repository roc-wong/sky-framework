package org.sky.framework.test;

import java.util.Comparator;
import java.util.Date;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class SortedSetAndMap {

	public static void main(String[] args) {

		System.out.println(new Date().getTime());
		
		Employee jim = new Employee("Jim");
		Employee bob = new Employee("Bob");
		Employee gin = new Employee("Gin");

		/** NavigableSet测试 ** */
		NavigableSet<Employee> navigableSet = new TreeSet<Employee>(
				new Comparator<Employee>() {
					@Override
					public int compare(Employee o1, Employee o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
		navigableSet.add(jim);
		navigableSet.add(bob);
		navigableSet.add(gin);
		
		System.out.println(navigableSet);
		
		SortedMap<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}

}

class Employee {
	public Employee(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return "Name:" + name;
	}

	private String name = "";// 实例域初始化
}
