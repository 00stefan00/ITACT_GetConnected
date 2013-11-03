package com.app.getconnected.rides;

import com.app.getconnected.rest.RESTRequest;
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

	private static final String baseURL = RESTRequest.API_URL;

	protected String url;

	protected String JSONKey;

	/**
	 * Sets part of the url
	 * @param url
	 */
	protected void setURL(String url) {
		this.url = url;
	}

	/**
	 * Checks whether essential data is not empty
	 * @return
	 */
	protected boolean isEmpty() {
		return this.url.equals("") || this.url == null || this.JSONKey.equals("") || this.JSONKey == null;
	}

	/**
	 * Parses JSON to an arraylist of JSONObjects
	 * @param json
	 * @return
	 */
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

	/**
	 * Gets the whole url
	 * @return
	 */
	protected String getUrl() {
		return baseURL + url;
	}

	/**
	 * Creates the request
	 * @return
	 * @throws Exception
	 */
	public abstract ArrayList<JSONObject> createRequest() throws Exception;
}
