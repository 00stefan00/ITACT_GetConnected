package com.app.getconnected.rides;

import java.util.ArrayList;

import org.json.JSONObject;

import com.app.getconnected.config.Config;
import com.util.getconnected.JSONParser;

/**
 * @author getConnected 2
 */

public abstract class Rides {

	private static final String baseURL = Config.OPEN_RIDE_API;

	protected String url;

	protected String JSONKey;

	public void setURL(String url) {
		this.url = url;
	}

	public String getUsername() {
		// TODO make connection to database, get username
		return "e";
	}

	protected boolean isEmpty() {
		String username = getUsername();
		return username.equals("") || this.url.equals("") || this.url == null
				|| this.JSONKey.equals("") || this.JSONKey == null;
	}

	protected ArrayList<JSONObject> parseJSONAsArray(String json) {
		ArrayList<JSONObject> jsonObjects = null;
		try {
			JSONParser jsonParser = JSONParser.getInstance();
			jsonObjects = jsonParser.getArrayFromRequest(json, JSONKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjects;
	}

	protected JSONObject parseJSONAsObject(String json) {
		JSONObject jsonObject = null;
		try {
			JSONParser jsonParser = JSONParser.getInstance();
			jsonObject = jsonParser.getObjectFromRequest(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	protected String getUrl() {
		return baseURL + url;
	}

	public void setJsonKey(String jsonKey) {
		this.JSONKey = jsonKey;
	}

	public abstract ArrayList<JSONObject> createRequest() throws Exception;
}
