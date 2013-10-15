package com.util.getconnected;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;import java.lang.System;import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: johan_000
 * Date: 10/15/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSONParser {

	private static JSONParser instance;


	private JSONParser() {
	}

	public JSONObject getObjectFromJSON(JSONObject jsonObject, String keyword) throws JSONException{
		return jsonObject.getJSONObject(keyword);
	}

	public JSONObject getObjectFromRequest(String json) throws JSONException{
		return new JSONObject(json);
	}

	public ArrayList<JSONObject> getArrayFromRequest(String json, String keyword) throws JSONException{
		JSONObject jsonObject = this.getObjectFromRequest(json);
		return this.JSONArrayToArrayList(jsonObject.getJSONArray(keyword));
	}

	private ArrayList<JSONObject> JSONArrayToArrayList(JSONArray array) throws JSONException{
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		for (int i=0; i<array.length(); i++) {
			list.add( array.getJSONObject(i) );
		}
		return list;
	}



	/**
	 * @return JSONParser
	 */
	public static JSONParser getInstance() {
		if(instance == null) {
			instance = new JSONParser();
		}
		return instance;
	}
}
