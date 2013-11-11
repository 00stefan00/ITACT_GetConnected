package com.app.getconnected.gps;

/**
 * @author getConnected 1
 */
public interface Location {
	
	public double getLatitude();
	public double getLongitude();
	public boolean isValidLocation();

}
