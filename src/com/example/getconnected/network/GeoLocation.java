package com.example.getconnected.network;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.getconnected.rest.RESTRequest;

public class GeoLocation {

	private HashMap<String, Double> location = new HashMap<String, Double>();
	private String url; 
	
	public GeoLocation(String address) {
		this.url = "https://maps.googleapis.com/maps/api/geocode/json";
				
		RESTRequest request = new RESTRequest(url);
		request.putString("address", address);
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
	
	private void setLocation(JSONObject json) throws JSONException {
		JSONArray results = json.getJSONArray("results");
		JSONObject resultsObject = results.getJSONObject(0);
		JSONObject geometry = resultsObject.getJSONObject("geometry");
		JSONObject location = geometry.getJSONObject("location");
		
		this.location.put("lat", location.getDouble("lat"));
		this.location.put("lng", location.getDouble("lng"));
	}
	
	public double getLatitude() {
		return location.get("lat");
	}
	
	public double getLongitude() {
		return location.get("lng");
	}	
	
}
