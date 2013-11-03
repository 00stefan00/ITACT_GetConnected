package com.app.getconnected.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import com.app.getconnected.R;

public class RateRideActivity extends BaseActivity {
	String rideId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate_ride);
		initLayout(R.string.title_activity_unrated_rides, true, true, true,
				false);
		rideId=getIntent().getStringExtra("rideId");
		Log.wtf("DEBUG", rideId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rate_ride, menu);
		return true;
	}

}
