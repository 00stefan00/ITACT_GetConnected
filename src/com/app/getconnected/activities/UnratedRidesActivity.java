package com.app.getconnected.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import com.app.getconnected.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UnratedRidesActivity extends BaseActivity {
	TableLayout tl;
	boolean isGray = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unrated_rides);
		initLayout(R.string.title_activity_unrated_rides, true, true, true,
				false);
		tableinit();
		addTableRow("1371546903022", "r", "template_user", "m", "14002");
		addTableRow("1371446993022", "d", "template_user", "m", "14003");
		addTableRow("1371246703022", "r", "template_user_2", "f", "14004");
	}

	/**
	 * Initializes the table
	 */

	private void tableinit() {
		tl = (TableLayout) findViewById(R.id.tableLayoutUnratedRides);

		TextView endTime = new TextView(this);
		endTime.setText(getResources().getString(
				R.string.unrated_ride_time_realized));
		TextView role = new TextView(this);
		role.setText(getResources()
				.getString(R.string.unrated_ride_person_role));
		TextView name = new TextView(this);
		name.setText(getResources()
				.getString(R.string.unrated_ride_person_name));
		TextView gender = new TextView(this);
		gender.setText(getResources().getString(
				R.string.unrated_ride_person_gender));

		TableRow rowHeader = new TableRow(this);

		rowHeader.addView(endTime);
		rowHeader.addView(role);
		rowHeader.addView(name);
		rowHeader.addView(gender);

		tl.addView(rowHeader, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tl.setStretchAllColumns(true);
	}

	/**
	 * Adds a table row
	 * 
	 * @param endTimev
	 * @param rolev
	 * @param namev
	 * @param genderv
	 */

	private void addTableRow(String endTimev, String rolev, String namev,
			String genderv, String rideIdv) {
		long endTimeLong = Long.valueOf(endTimev);
		Date endTimeDate = new java.util.Date(endTimeLong);
		String endTimeString = new SimpleDateFormat("yyyy-MM-dd hh:mm",
				Locale.ENGLISH).format(endTimeDate);
		TextView endTime = new TextView(this);
		endTime.setText(endTimeString);
		TextView role = new TextView(this);
		role.setText(rolev);
		TextView name = new TextView(this);
		name.setText(namev);
		TextView gender = new TextView(this);
		gender.setText(genderv);
		Button buttonRate = new Button(this);
		// TODO use strings.xml
		buttonRate.setText(getString(R.string.unrated_ride_button_rate));
		buttonRate.setTag(rideIdv);
		buttonRate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(UnratedRidesActivity.this,
						RateRideActivity.class);
				intent.putExtra("rideId", v.getTag().toString());
				startActivity(intent);
			}
		});
		int color=getColor();

		TableRow datarow = new TableRow(this);
		datarow.setBackgroundColor(color);
		datarow.addView(endTime);
		datarow.addView(role);
		datarow.addView(name);
		datarow.addView(gender);
		datarow.setGravity(Gravity.CENTER_HORIZONTAL);
		tl.addView(datarow, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		datarow = new TableRow(this);
		datarow.setBackgroundColor(color);
		datarow.addView(buttonRate);
		datarow.setGravity(Gravity.CENTER_HORIZONTAL);
		tl.addView(datarow, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		tl.setStretchAllColumns(true);
	}

	private int getColor()
	{
		isGray=!isGray;
		if(isGray) {
			return Color.LTGRAY;
		}
		return Color.WHITE;
	}
}
