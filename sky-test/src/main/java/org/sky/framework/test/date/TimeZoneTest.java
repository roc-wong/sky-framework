package org.sky.framework.test.date;

import java.text.*;
import java.util.*;

public class TimeZoneTest {
	public static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

	public static String dateTransformBetweenTimeZone(Date sourceDate,
			DateFormat formatter, TimeZone sourceTimeZone,
			TimeZone targetTimeZone) {
		Long targetTime = sourceDate.getTime() - sourceTimeZone.getRawOffset()
				+ targetTimeZone.getRawOffset();
		return TimeZoneTest.getTime(new Date(targetTime), formatter);
	}

	public static String getTime(Date date, DateFormat formatter) {
		return formatter.format(date);
	}

	/**
	 * 
	* 计算纽约的时间
	* @param args    
	* void  
	* @author <a href="193832433@qq.com">sky</a>   
	* @date 2016年3月11日 上午10:43:46
	 */
	public static void main(String[] args) {
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		TimeZone targetTimeZone = TimeZone.getTimeZone("America/New_York");
		TimeZone sourceTimeZone = TimeZone.getTimeZone("GMT+8:00");
		Date date = Calendar.getInstance().getTime();
		Long targetTime = date.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset();
		String targetTimeStr = getTime(new Date(targetTime), formatter);
		System.out.println(targetTimeStr);
	}
}
