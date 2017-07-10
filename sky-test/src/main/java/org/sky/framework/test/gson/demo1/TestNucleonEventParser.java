package org.sky.framework.test.gson.demo1;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sky.framework.test.gson.demo1.NucleonEvent.NucleonEventType;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  swang6@eaby.com
 */
public class TestNucleonEventParser {

	private static String json;
	
	@BeforeClass
	public static void init() {
		json = "{\"id\": \"eventId\", \"type\":\"nwmon_checkCPU_busyio\", "
				+ "\"format\" : \"normalizedEvent\", \"detectionTime\": \"23424234234\", "
				+ " \"severity\":\"low\", "
				+ "\"metadata\":{\"passed\": true,\"name\": \"Recent Changes Check\", \"msgs\": [{\"data\": \"<HTML Allowed>\"}, {\"eve\": \"<Pdf Allowed>\"}]}}";
	}
	
	@Test
	public void fromGson() {
		NucleonEvent event = new NucleonEventDataParser().fromGson(json);
		
		Assert.assertEquals("eventId", event.getId());
		Assert.assertEquals("nwmon_checkCPU_busyio", event.getType().toString());
		
		System.out.println(event.toString());
	}
	
	@Test 
	public void toGson() {
		NucleonEvent event = new NucleonEvent();
		
		event.setId("eventId2");
		event.setType(NucleonEventType.nwmon_checkCPU_busyio);
		
		Assert.assertTrue(new NucleonEventDataParser().toGson(event).contains("eventId2"));
	}
}
