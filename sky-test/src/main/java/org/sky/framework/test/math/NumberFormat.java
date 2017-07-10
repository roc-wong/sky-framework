package org.sky.framework.test.math;

import java.math.BigDecimal;

public class NumberFormat {

	public static void main(String[] args) {

		// 方法一：四舍五入
		double f = 111231.5585;
		double num1 = 11.556;
		double num2 = 111231.5585;
		double num3 = 111231.5585;
		double num4 = 111231.5585;
		double num5 = 111231.5585;
		
		BigDecimal bigDecimal = new BigDecimal(f);
		double f1 = bigDecimal.setScale(2, java.math.RoundingMode.HALF_UP).doubleValue();
		System.out.println(f1);
		
		System.out.println(f1);

		//方法二：DecimalFormat
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		System.out.println(df.format(f));
		
		
		//%.2f %. 表示 小数点前任意位数   2 表示两位小数 格式后的结果为f 表示浮点型。
		double d = 3.1415926;
		String result = String.format("%.2f", d);
		System.out.println(result);
		
	}

}
