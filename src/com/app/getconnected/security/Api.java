package com.app.getconnected.security;

import com.app.getconnected.config.Config;
import com.app.getconnected.rest.RESTRequest;
import com.util.getconnected.JSONParser;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: johan
 * Date: 11/1/13
 * Time: 11:48 AM
 */
public abstract class Api {

	private HashMap<String, String> map = new HashMap<String, String>();

	protected static final String API_URL = Config.OPEN_RIDE_API;

	/**
	 * Attempts an api request
	 * @param url
	 * @param method
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String attemptApiRequest(String url, RESTRequest.Method method, String key) throws Exception {
		if(map.isEmpty()) throw new Exception("The map is empty");
		RESTRequest restRequest = new RESTRequest(API_URL + url);
		restRequest.setMethod(method);
		JSONObject jsonObject = JSONParser.getInstance().parseMapAsObject(map);
		String jsonString="{\""+ key +"\":" + jsonObject.toString()+"}";
		restRequest.putString("json", jsonString);

		//TODO implement once API is reachable
		//String result = request.execute().get();
		//return result;
		return "body";
	}

	/**
	 * Adds a value to the hashmap
	 * @param key
	 * @param value
	 */
	protected void addToMap(String key, String value) {
		map.put(key, value);
	}

	/**
	 * Empties the hashmap
	 */
	protected void emptyMap() {
		map.clear();
	}

	/**
	 * Gets the hashmap
	 * @return
	 */
	public HashMap<String, String> getMap() {
		return map;
	}

	/**
	 * Fills the hashmap
	 */
	public abstract void fillMap();
}
