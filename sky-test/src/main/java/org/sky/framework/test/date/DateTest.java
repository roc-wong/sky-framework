package org.sky.framework.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) throws Exception {
//		test1();
		
		
		String day1 = "2016-03-02 00:00:00";
		String day2 = "2016-03-06 00:00:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date1 = dateFormat.parse(day1);
		Date date2 = dateFormat.parse(day2);
		
		System.out.println(daysBetween(date1, date2));
		
	}
	
	
	private static void test1() {
		Calendar calendar = Calendar.getInstance();
		// calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date date = calendar.getTime();
		System.out.println(calendar.getTimeInMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(calendar.getTime()));

		int MILLISECONDS_ONE_DAY = 86400000;
		int MILLISECONDS_ONE_MINUTE = 60000;
		Date today = new Date((new Date().getTime()) / MILLISECONDS_ONE_DAY * MILLISECONDS_ONE_DAY
				+ new Date().getTimezoneOffset() * MILLISECONDS_ONE_MINUTE);
		Date today1 = new Date((new Date().getTime()) / MILLISECONDS_ONE_DAY * MILLISECONDS_ONE_DAY);
		System.out.println(dateFormat.format(today));
		System.out.println(today1.toGMTString());
		System.out.println(today1.toLocaleString());
	}


	public static int daysBetween(Date smdate, Date bdate) {  
	    return (int)(((bdate.getTime() + bdate.getTimezoneOffset() * 60000L)/ 86400000L) - ((smdate.getTime() + smdate.getTimezoneOffset() * 60000L) / 86400000L));  
	}  
}
