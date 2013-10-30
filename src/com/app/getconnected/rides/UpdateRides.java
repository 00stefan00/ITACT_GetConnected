package com.app.getconnected.rides;

import com.app.getconnected.rest.RESTRequest;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: johan
 * Date: 10/30/13
 * Time: 12:08 PM
 */
public class UpdateRides extends Rides {



	@Override
	public ArrayList<JSONObject> createRequest() throws Exception {
		if(isEmpty()) throw new Exception("Not all params have been filled");
		RESTRequest restRequest = new RESTRequest(getUrl(), RESTRequest.Method.PUT, "1");
		String json = restRequest.execute().get();
		return parseJSON(json);
	}
}
