package com.util.getconnected;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author getConnected 2
 */

public class JSONParser {

	private static JSONParser instance;

	/**
	 * Private constructor
	 */
	private JSONParser() {
	}

	/**
	 * Gets a JSONObject from the keyword
	 * 
	 * @param jsonObject
	 * @param keyword
	 * @return
	 * @throws JSONException
	 */
	public JSONObject getObjectFromJSON(JSONObject jsonObject, String keyword)
			throws JSONException {
		return jsonObject.getJSONObject(keyword);
	}

	/**
	 * Converts string to JSON
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public JSONObject getObjectFromRequest(String json) throws JSONException {
		return new JSONObject(json);
	}

	/**
	 * Creates an arraylist of JSONObjects
	 * 
	 * @param json
	 * @param keyword
	 * @return
	 * @throws JSONException
	 */
	public ArrayList<JSONObject> getArrayFromRequest(String json, String keyword)
			throws JSONException {
		JSONObject jsonObject = this.getObjectFromRequest(json);
		return this.JSONArrayToArrayList(jsonObject.getJSONArray(keyword));
	}

	/**
	 * Parses a list to JSON
	 * 
	 * @param list
	 * @param identifier
	 * @return {"identifier" : ["value", "value2"]}
	 * @throws JSONException
	 */
	public JSONObject parseList(List<?> list, String identifier)
			throws JSONException {
		String json = "{\"" + identifier + "\": [";
		for (int i = 0; i < list.size(); i++) {
			json += "\"" + list.get(i).toString() + "\",";
		}
		json = json.substring(0, json.length() - 1);
		return this.getObjectFromRequest(json + "]}");
	}

	/**
	 * Parses a map as JSON array
	 * 
	 * @param map
	 * @param identifier
	 * @return {"identifier" : [{"key" : "value"},{"key1" : "value1"}]}
	 * @throws JSONException
	 */
	public JSONObject parseMapAsArray(Map<?, ?> map, String identifier)
			throws JSONException {
		String json = "{\"" + identifier + "\" : [";
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			json += "{\"" + key.toString() + "\" : \"" + value.toString()
					+ "\"},";
		}
		json = json.substring(0, json.length() - 1);
		return this.getObjectFromRequest(json + "]}");
	}

	/**
	 * Parses a map as JSON object
	 * 
	 * @param map
	 * @return String, example: {key : value, key1 : value1}
	 * @throws JSONException
	 */
	public JSONObject parseMapAsObject(Map<?, ?> map) throws JSONException {
		String json = "{";
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			json += "\"" + key.toString() + "\" : \"" + value.toString()
					+ "\",";
		}
		json = json.substring(0, json.length() - 1);
		return this.getObjectFromRequest(json + "}");
	}

	/**
	 * Converts JSONArray to an arraylist of JSONObjects
	 * 
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	private ArrayList<JSONObject> JSONArrayToArrayList(JSONArray array)
			throws JSONException {
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i < array.length(); i++) {
			list.add(array.getJSONObject(i));
		}
		return list;
	}

	/**
	 * Gets the instance of the JSONParser
	 * 
	 * @return JSONParser
	 */
	public static JSONParser getInstance() {
		if (instance == null) {
			instance = new JSONParser();
		}
		return instance;
	}
}
