package com.app.getconnected.rides;

import com.app.getconnected.rest.RESTRequest;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: EnquiringStone
 * Date: 18-10-13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class OfferedRides extends Rides{

	@Override
	public ArrayList<JSONObject> createRequest() throws Exception {
		if(isEmpty()) throw new Exception("Not all arguments have been filled");
		RESTRequest restRequest = new RESTRequest(getUrl(), RESTRequest.Method.GET, "1");
		String json = restRequest.execute().get();
		return parseJSON(json);
	}
}
