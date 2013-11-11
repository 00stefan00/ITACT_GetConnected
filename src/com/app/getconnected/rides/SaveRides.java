package com.app.getconnected.rides;

import com.app.getconnected.rest.RESTRequest;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author getConnected 2
 */

public class SaveRides extends Rides implements Argumentable {

	private String argument;

	private String key;

	@Override
	public ArrayList<JSONObject> createRequest() throws Exception {
		if (isEmpty())
			throw new Exception("Not all arguments have been filled");
		RESTRequest restRequest = new RESTRequest(getUrl(),
				RESTRequest.Method.POST, "1");
		restRequest.putString(key, argument);
		String json = restRequest.execute().get();
		return parseJSONAsArray(json);
	}

	public JSONObject createRequestJsonObject() throws Exception {
		if (isEmpty())
			throw new Exception("Not all arguments have been filled");
		RESTRequest restRequest = new RESTRequest(getUrl(),
				RESTRequest.Method.POST, "1");
		restRequest.putString(key, argument);
		String json = restRequest.execute().get();
		return parseJSONAsObject(json);
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isEmpty() {
		return super.isEmpty() || argument.isEmpty() || key.isEmpty();
	}
}
