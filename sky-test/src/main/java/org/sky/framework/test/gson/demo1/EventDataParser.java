package org.sky.framework.test.gson.demo1;

import java.util.Calendar;

import org.sky.framework.test.gson.demo1.NucleonEvent.NucleonEventType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  swang6@ebay.com
 */
public abstract class EventDataParser {
	
	public static Gson createGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		builder.registerTypeAdapter(NucleonEventType.class, new NucleonEventTypeSerializer());
		
		Gson gson = builder.create();
		return gson;
	}
	
	
	/*public static Gson createGson1() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(NucleonEventType.class, new NucleonEventTypeAdapter());
		builder.registerTypeAdapter(Calendar.class, new NucleonEventCalendarAdapter());
		builder.setDateFormat(DateUtils.MIDDLE_LINE_TIMESTAMP);
		
		Gson gson = builder.create();
		return gson;
	}*/

	/**
	 * Parse the given JSON String to NucleonEvent
	 * @param jsonStr
	 * @return
	 */
	public abstract NucleonEvent fromGson(String jsonStr);
	
	/**
	 * Parse the NucleonEvent to JSON String
	 * @return
	 */
	public abstract String toGson(NucleonEvent event);
}
