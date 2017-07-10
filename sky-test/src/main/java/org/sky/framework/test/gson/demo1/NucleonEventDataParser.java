package org.sky.framework.test.gson.demo1;


/**
 * @author Josh Wang(Sheng)
 *
 * @email  swang6@ebay.com
 */
public class NucleonEventDataParser extends EventDataParser {

	@Override
	public NucleonEvent fromGson(String jsonStr) {
		return NucleonEventDataParser.createGson().fromJson(jsonStr, NucleonEvent.class);
	}

	@Override
	public String toGson(NucleonEvent event) {
		return NucleonEventDataParser.createGson().toJson(event);
	}

}
