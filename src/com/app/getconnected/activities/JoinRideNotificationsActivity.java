package com.app.getconnected.activities;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.getconnected.R;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class JoinRideNotificationsActivity extends NotificationsActivity {
	
	TableLayout tl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_ride_notifications);
		initLayout(R.string.title_activity_join_ride_notifications, true, true, true, false);
		
		try {
			JSONObject obj = new JSONObject(getIntent().getStringExtra("jsonString"));
			Log.d("Error", "" + obj.toString());
			dealWithJSONObject(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join_ride_notifications, menu);
		return true;
	}
	
	
	/**
	 * Display details of ride (Read from JSONObject)
	 */
	public void dealWithJSONObject(JSONObject obj){
		
		tl = (TableLayout) findViewById(R.id.layoutNotificationJoinRide);
		    
	    TextView departure_time = new TextView(this);
	    departure_time.setText(getResources().getString(R.string.departure_time));
	    TextView arrival_time = new TextView(this);
	    arrival_time.setText(getResources().getString(R.string.arrival_time));
	    TextView from = new TextView(this);
	    from.setText(getResources().getString(R.string.location_from));
	    TextView destination = new TextView(this);
	    destination.setText(getResources().getString(
	        R.string.location_destination));
	    TextView driverName = new TextView(this);
	    driverName.setText(getResources().getString(R.string.driver_name));
	    TextView driverGender = new TextView(this);
	    driverGender.setText(getResources().getString(R.string.driver_gender));

	    TextView tv1 = new TextView(this);
	    TextView tv2 = new TextView(this);
	    TextView tv3 = new TextView(this);
	    TextView tv4 = new TextView(this);
	    TextView tv5 = new TextView(this);
	    TextView tv6 = new TextView(this);
	    try {
	      tv1.setText(obj.getString("departureTime"));
	      tv2.setText(obj.getString("arrivalTime"));
	      tv3.setText(obj.getString("departurePlace"));
	      tv4.setText(obj.getString("arrivalPlace"));
	      tv5.setText(obj.getString("driverName"));
	      tv6.setText(obj.getString("driverGender"));
	      
	    } catch (JSONException e) {
	      Log.d("Error", "Dit gaat heel hard stuk");
	    }
	    
	    TableRow row1 = new TableRow(this);
	    row1.addView(departure_time);
	    row1.addView(tv1);
	    
	    TableRow row2 = new TableRow(this);
	    row2.addView(arrival_time);
	    row2.addView(tv2);
	    
	    TableRow row3 = new TableRow(this);
	    row3.addView(from);
	    row3.addView(tv3);
	    
	    TableRow row4 = new TableRow(this);
	    row4.addView(destination);
	    row4.addView(tv4);
	    
	    TableRow row5 = new TableRow(this);
	    row5.addView(driverName);
	    row5.addView(tv5);
	    
	    TableRow row6 = new TableRow(this);
	    row6.addView(driverGender);
	    row6.addView(tv6);
	    
	    Log.d("Error", " row: " + row1.toString());
	    Log.d("ERROR", "tl: " + tl.toString());
	    tl.addView(row1);
	    tl.addView(row2);
	    tl.addView(row3);
	    tl.addView(row4);
	    tl.addView(row5);
	    tl.addView(row6);
		
	}

}
