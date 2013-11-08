package com.app.getconnected.gps;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

/**
 * @author 	Jorian Plat <jorianplat@hotmail.com>
 * @version 1.0			
 * @since	2013-10-10
 */
public class GPSLocator implements com.app.getconnected.gps.Location, LocationListener {
	
	/** Unrealistic latitude and longitude to be able to test whether or not a valid location was retrieved. */
	private double latitude = 999;
	private double longitude = 999;
	
	private Context context;

	/**
	 * Constructor
	 * @param context
	 */
	public GPSLocator(Context context) {
		this.context = context;
		
		enableGPS();
		
		//Initialize GPS
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	    Criteria criteria = new Criteria();
	    String provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);

	    if (location != null) {
	    	onLocationChanged(location);
	    }
	    
	}
	
	/**
	 * Enable the GPS on the device, return if disabled;
	 */
	public void enableGPS() {
		String provider = Settings.Secure.getString(context.getContentResolver(), 
	    Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(provider.contains("gps")) {
	        return;
	    }

	    final Intent intent = new Intent();
	    intent.setClassName("com.android.settings", 
	    		"com.android.settings.widget.SettingsAppWidgetProvider");
	    intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
	    intent.setData(Uri.parse("3"));
	    context.sendBroadcast(intent);
	}
	
	/**
	 * Get the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	
	/**
	 * Get the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * Check whether location is valid;
	 * @return boolean 	True when valid location; false when invalid location
	 */
	public boolean isValidLocation() {
		return (latitude >= -90 || latitude <= 90) && (longitude >= -180 || longitude <= 180);
	}

	@Override
	public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
	    longitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		
	}
	
}
