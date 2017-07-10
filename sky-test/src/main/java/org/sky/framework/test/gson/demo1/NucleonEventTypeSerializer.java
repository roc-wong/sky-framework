package org.sky.framework.test.gson.demo1;

import java.lang.reflect.Type;

import org.sky.framework.test.gson.demo1.NucleonEvent.NucleonEventType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  swang6@ebay.com
 */
public class NucleonEventTypeSerializer implements JsonSerializer<NucleonEventType>,
		JsonDeserializer<NucleonEventType> {

	/**
	 * JSON to Object
	 */
	public NucleonEventType deserialize(JsonElement json, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
        return NucleonEventType.valueOf(json.getAsString()) ; 
	}

	/**
	 * Object to JSON
	 */
	public JsonElement serialize(NucleonEventType type, Type arg1,
			JsonSerializationContext arg2) {
		return new JsonPrimitive(type.name());
	}


}
