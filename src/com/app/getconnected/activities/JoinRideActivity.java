package com.app.getconnected.activities;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;
import com.app.getconnected.R;
import com.app.getconnected.activities.BaseActivity;
import com.app.getconnected.sqllite.MarketplaceDatabaseHandler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author getConnected 2
 */

public class JoinRideActivity extends BaseActivity {
	
  TableLayout tl;
  JSONObject obj;
  MarketplaceDatabaseHandler helper = new MarketplaceDatabaseHandler(this);
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_ride);
		initLayout(R.string.title_activity_join_ride, true, true, true, true);
		try {
      obj = new JSONObject(getIntent().getStringExtra("json"));
      Log.d("test","jsonobject id: "+obj.getInt("rideID"));
      
    } catch (JSONException e) {
      e.printStackTrace();
    }
		
		tableinit();
	}
	
	private void tableinit() {
    tl = (TableLayout) findViewById(R.id.tableLayoutJoinRide);
    
    TextView departure_time = new TextView(this);
    departure_time.setText(getString(R.string.departure_time));
    TextView arrival_time = new TextView(this);
    arrival_time.setText(getString(R.string.arrival_time));
    TextView from = new TextView(this);
    from.setText(getString(R.string.location_from));
    TextView destination = new TextView(this);
    destination.setText(getString(
        R.string.location_destination));
    TextView driverName= new TextView(this);
    driverName.setText(getString(
            R.string.driver_name));
    TextView driverGender= new TextView(this);
    driverGender.setText(getString(
            R.string.driver_gender));

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
      e.printStackTrace();
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
    
    TableRow row7 = new TableRow(this);
    Button b = new Button(this);
    b.setText(getString(R.string.join_ride_button_join));
    row7.addView(b);
    
    b.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d("DEBUG", obj.toString());
			
			//Create timestamp and insert into sqlite
		    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		    helper.executeQuery("INSERT INTO joins VALUES ('" + timestamp + "','" + obj.toString() + "')");
		    
			Toast toast = Toast.makeText(JoinRideActivity.this, getString(
			        R.string.join_ride_requested), Toast.LENGTH_LONG);
			toast.show();
			finish();
		}
	});
    
    tl.addView(row1);
    tl.addView(row2);
    tl.addView(row3);
    tl.addView(row4);
    tl.addView(row5);
    tl.addView(row6);
    tl.addView(row7);
    
	}
}