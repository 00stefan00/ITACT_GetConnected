package com.test.getconnected;

import android.test.AndroidTestCase;
import com.util.getconnected.JSONParser;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: johan_000
 * Date: 10/15/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSONParserTest extends AndroidTestCase{

	public String getJSON() {
		return "{\"list\":[{\"OpenRatingResponse\":{\"riderRouteId\":14002,\"custId\":6151,\"custNickname\":\"template_user\",\"custGender\":\"m\",\"custRole\":\"r\",\"timestamprealized\":1371546903022}}]}";
	}

	public void testGetObjectFromRequest() throws Exception {
		JSONParser jsonParser = JSONParser.getInstance();
		JSONObject jsonObject = jsonParser.getObjectFromRequest(this.getJSON());
		assertTrue(jsonObject.has("list"));

	}

	public void testGetArrayFromRequest() throws Exception {
		JSONParser jsonParser = JSONParser.getInstance();
		ArrayList<JSONObject> jsonObjects = jsonParser.getArrayFromRequest(this.getJSON(), "list");
		assertFalse(jsonObjects.isEmpty());
	}

	public void testGetObjectFromJSON() throws Exception {
		JSONParser jsonParser = JSONParser.getInstance();
		ArrayList<JSONObject> jsonObjects = jsonParser.getArrayFromRequest(this.getJSON(), "list");
		JSONObject jsonObject = jsonParser.getObjectFromJSON(jsonObjects.get(0), "OpenRatingResponse");
		assertEquals("14002", jsonObject.get("riderRouteId").toString());
	}

	public void testGetInstance() throws Exception {
		JSONParser jsonParser = JSONParser.getInstance();
		assertEquals(jsonParser, JSONParser.getInstance());
	}
}
