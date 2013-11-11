package com.app.getconnected.rides;

import com.app.getconnected.rest.RESTRequest;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author getConnected 2
 */

public class OfferedRides extends Rides {

	public ArrayList<JSONObject> createRequest() throws Exception {
		if (isEmpty())
			throw new Exception("Not all arguments have been filled");
		RESTRequest restRequest = new RESTRequest(getUrl(),
				RESTRequest.Method.GET, "1");
		String json = restRequest.execute().get();
		return parseJSONAsArray(json);
	}
}
