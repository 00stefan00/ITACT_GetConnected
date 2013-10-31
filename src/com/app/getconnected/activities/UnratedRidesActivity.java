package com.app.getconnected.activities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

import com.app.getconnected.R;

public class UnratedRidesActivity extends BaseActivity
{
	TableLayout tl;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unrated_rides);
		initLayout(R.string.title_activity_unrated_rides, true, true, true,
				false);
		tableinit();
		addTableRow("1371546903022", "r", "template_user", "m");
	}
	
	private void tableinit() {
		/* 	Incoming data example
			{"list":[{"OpenRatingResponse":{"riderRouteId":14002,"custId":6151,
			"custNickname":"template_user","custGender":"m","custRole":"r",
			"timestamprealized":1371546903022}}]}
		 */
		tl = (TableLayout) findViewById(R.id.tableLayoutUnratedRides);
		
		TextView endTime = new TextView(this);
		endTime.setText(getResources().getString(R.string.marketplace_unrated_ride_time_realized));
        TextView role = new TextView(this);
        role.setText(getResources().getString(R.string.marketplace_unrated_ride_person_role));
        TextView name = new TextView(this);
        name.setText(getResources().getString(R.string.marketplace_unrated_ride_person_name));
        TextView gender = new TextView(this);
        gender.setText(getResources().getString(R.string.marketplace_unrated_ride_person_gender));
        
        TableRow rowHeader = new TableRow(this);

        rowHeader.addView(endTime);
        rowHeader.addView(role);
        rowHeader.addView(name);
        rowHeader.addView(gender);   
        
        tl.addView(rowHeader, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tl.setStretchAllColumns(true);		
	}
	
	private void addTableRow(String endTimev, String rolev, String namev, String genderv){
		long endTimeLong = Long.valueOf(endTimev);
		Date endTimeDate = new java.util.Date(endTimeLong);
		String endTimeString = new SimpleDateFormat("yyyy-MM-dd hh:mma", Locale.ENGLISH).format(endTimeDate);
		
		TextView endTime = new TextView(this);
		endTime.setText(endTimeString);
        TextView role = new TextView(this);
        role.setText(rolev);
        TextView name = new TextView(this);
        name.setText(namev);
        TextView gender = new TextView(this);
        gender.setText(genderv);
        
        TableRow datarow = new TableRow(this);

        datarow.addView(endTime);
        datarow.addView(role);
        datarow.addView(name);
        datarow.addView(gender);
        
        tl.addView(datarow, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
        tl.setStretchAllColumns(true);		
	}
}
