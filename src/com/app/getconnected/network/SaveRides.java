package com.app.getconnected.network;

import com.app.getconnected.rest.RESTRequest;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: johan
 * Date: 10/28/13
 * Time: 1:56 PM
 */
public class SaveRides extends Rides {

	private String argument;

	private String key;

	@Override
	public ArrayList<JSONObject> createRequest() throws Exception {
		if(isEmpty() || argument.isEmpty() || key.isEmpty()) throw new Exception("Not all arguments have been filled");
		RESTRequest restRequest = new RESTRequest(getUrl(), RESTRequest.Method.POST, "1");
		restRequest.putString(key, argument);
		String json = null;
		try {
			json = restRequest.execute().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parseJSON(json);
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
