package org.sky.framework.test.reflect;

import java.lang.reflect.Method;

/**
 * 使用数组实现约瑟夫环问题 由m个人围成一个首尾相连的圈报数。 从第一个人开始，从1开始报数，报到n的人出圈，
 * 剩下的人继续从1开始报数，直到所有的人都出圈为止。 对于给定的m和n，求出所有人的出圈顺序.
 */
public class ReflectTest {
	public static void main(String[] args) throws Exception {

		CustomerEntrustInfo customerEntrustInfo = new CustomerEntrustInfo();
		customerEntrustInfo.setFid_addr_ip("195.125.124.32");
		
		
		try {
			Method method = customerEntrustInfo.getClass().getDeclaredMethod(getMethod("fid_wtpch"));
			Object param = method.invoke(customerEntrustInfo);
			
			System.out.println(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			Method method = customerEntrustInfo.getClass().getMethod(getMethod("fid_wtpch"));
			Object param = method.invoke(customerEntrustInfo);
			
			System.out.println(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * description：get的方法 author：nsz date:2014年12月2日
	 */
	private static String getMethod(String str) {
		return "get" + str.substring(0, 1).toUpperCase()
				+ str.substring(1, str.length());
	}
}