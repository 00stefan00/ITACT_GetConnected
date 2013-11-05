package com.app.getconnected.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.getconnected.R;

public class RateRideActivity extends BaseActivity {
	String rideId;
	RadioGroup rideRatingField;
	EditText rideCommentField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate_ride);
		//TODO find out why initLayout crashes
		//initLayout(R.string.title_activity_rate_ride, true, false, false, false);
		rideId = getIntent().getStringExtra("rideId");
		rideRatingField=(RadioGroup) findViewById(R.id.ride_rating);
		rideRatingField.check(R.id.good);
		rideCommentField=(EditText) findViewById(R.id.ride_comment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.rate_ride, menu);
		return true;
	}
	
	public void cancelRating(View v) {
		finish();
	}
	
	public void sendRating(View v) {
		String rating=((RadioButton)findViewById(rideRatingField.getCheckedRadioButtonId())).getTag().toString();
		String comment=rideCommentField.getText().toString();
		Log.d("DEBUG", "ride ID:"+rideId+", Rating: "+rating+", Comment: "+comment);
		finish();
	}

}
