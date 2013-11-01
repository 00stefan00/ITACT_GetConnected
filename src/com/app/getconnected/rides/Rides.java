package com.app.getconnected.rides;

import com.util.getconnected.JSONParser;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: johan
 * Date: 10/30/13
 * Time: 10:26 AM
 */
public abstract class Rides {

	private static final String baseURL = "http://localhost:8181/";

	protected String url;

	protected String JSONKey;

	protected void setURL(String url) {
		this.url = url;
	}

	protected String getUsername() {
		//TODO make connection to database, get username
		return "e";
	}

	protected boolean isEmpty() {
		String username = getUsername();
		return username.equals("") || this.url.equals("") || this.url == null || this.JSONKey.equals("") || this.JSONKey == null;
	}

	protected ArrayList<JSONObject> parseJSON(String json) {
		ArrayList<JSONObject> jsonObjects = null;
		try {
			JSONParser jsonParser = JSONParser.getInstance();
			jsonObjects = jsonParser.getArrayFromRequest(json, JSONKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjects;
	}

	protected String getUrl() {
		return baseURL + url;
	}

	public abstract ArrayList<JSONObject> createRequest() throws Exception;
}
