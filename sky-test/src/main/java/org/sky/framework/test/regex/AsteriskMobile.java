package org.sky.framework.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsteriskMobile {

	public static void main(String[] args) {
		System.out.println(asteriskMobile("18551208215"));

		String a = "a\":44.78,\"b\":saf3232sdfsdfsd,";
		Pattern pattern = Pattern.compile("^(a\":)\\d*");
		Matcher matcher = pattern.matcher(a);
		if (matcher.find()) {
			System.out.println(matcher.group().replace("a\":", ""));
		} else {
			System.out.println("未匹配");
		}
	}

	/**
	 * 替换掉手机号码的中间四位，用“*”号表示
	 * 
	 * @param mobile
	 * @return
	 * @author sky
	 */
	public static String asteriskMobile(String mobile) {

		Pattern pattern = Pattern.compile("(?<=\\d{3})\\d(?=\\d{4})");

		if (mobile != null && !"".equals(mobile.trim())) {
			return pattern.matcher(mobile).replaceAll("*");
		} else {
			return "";
		}

	}

	/**
	 * 替换掉手机号码的中间四位，用“*”号表示
	 * 
	 * @param mobile
	 * @return
	 * @author sky
	 */
	public static String asteriskString(String mobile) {

		Pattern pattern = Pattern.compile("^(a\":)/d*");

		if (mobile != null && !"".equals(mobile.trim())) {
			return pattern.matcher(mobile).replaceAll("*");
		} else {
			return "";
		}

	}

	public static void emailPattern() {

		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("(^1\\d{10}$)|(^([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+(|\\#+[a-zA-Z0-9]{1,20})+$)");
		Matcher matcher = pattern.matcher("sunshineboy_1232@163.com");
		if (matcher.find()) {
			System.out.println(matcher.group());
		} else {
			System.out.println("not found");
		}

	}

}
