package org.sky.framework.test.gson.demo1;

import java.lang.reflect.Type;
import java.util.Calendar;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email swang6@ebay.com
 */
public class NucleonEventCalendarAdapter implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {

	public Calendar deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(json.getAsJsonPrimitive().getAsLong());
		return calendar;
	}

	public JsonElement serialize(Calendar calendar, Type arg1, JsonSerializationContext arg2) {
		return new JsonPrimitive(Long.valueOf(calendar.getTimeInMillis()));
	}

}
