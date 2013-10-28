package com.app.getconnected.network;

import com.app.getconnected.rest.RESTRequest;
import com.util.getconnected.JSONParser;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: EnquiringStone
 * Date: 18-10-13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class OfferedRides {

	private static final String[] url = {"https://localhost:8181/OpenRideServer-RS/resources/users/", "/rides/offers"};
	private static String username;

	public static void setUsername(String username) {
		OfferedRides.username = username;
	}

	public static ArrayList<JSONObject> createRequest() {
		RESTRequest restRequest = new RESTRequest(getUrl(), RESTRequest.Method.GET, "1");
		String json = null;
		try {
			json = restRequest.execute().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parseJSON(json);
	}

	private static ArrayList<JSONObject> parseJSON(String json) {
		ArrayList<JSONObject> jsonObjects = null;
		try {
			JSONParser jsonParser = JSONParser.getInstance();
			jsonObjects = jsonParser.getArrayFromRequest(json, "list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjects;
	}

	public static String getUrl() {
		String updatedUrl = "";
		for (String url : OfferedRides.url) {
			updatedUrl += url + username;
		}
		return updatedUrl.substring(0, updatedUrl.length() - username.length());
	}
}
