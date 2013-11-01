package com.app.getconnected.network;

import com.app.getconnected.rest.RESTRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class GeoLocation {

	private HashMap<String, Double> location = new HashMap<String, Double>();
	private String url;

	/**
	 * Constructor
	 * @param address
	 */
	public GeoLocation(String address) {
		this.url = "https://maps.googleapis.com/maps/api/geocode/json";
				
		RESTRequest request = new RESTRequest(url);
		request.putString("address", address);
		request.putString("region", "nl");
		request.putString("sensor", "true");
		try {
			String result = request.execute().get();
			
			setLocation(new JSONObject(result));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the location from the JSONObject
	 * @param json
	 * @throws JSONException
	 */
	private void setLocation(JSONObject json) throws JSONException {
		JSONArray results = json.getJSONArray("results");
		JSONObject resultsObject = results.getJSONObject(0);
		JSONObject geometry = resultsObject.getJSONObject("geometry");
		JSONObject location = geometry.getJSONObject("location");
		
		this.location.put("lat", location.getDouble("lat"));
		this.location.put("lng", location.getDouble("lng"));
	}

	/**
	 * Checks whether the location is valid
	 * @return
	 */
	public boolean isValidLocation() {
		return location.get("lat") != null && location.get("lng") != null;
	}

	/**
	 * Gets the latitude
	 * @return
	 */
	public double getLatitude() {
		return location.get("lat");
	}

	/**
	 * Gets the longitude
	 * @return
	 */
	public double getLongitude() {
		return location.get("lng");
	}	
	
}
