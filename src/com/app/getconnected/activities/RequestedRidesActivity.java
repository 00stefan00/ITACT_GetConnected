package com.app.getconnected.activities;

import com.app.getconnected.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.util.getconnected.ActivityHelper.DatePickerFragment;

public class RequestedRidesActivity extends BaseActivity {
	TableLayout tl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestedrides);
		initLayout(R.string.title_activity_requestrides, true, true, true, false);
		
		tableinit();
		addTableRow("08:30", "12:00", "Groningen", "Berlijn");
	}

	/**
	 * Initializes the table
	 */
	private void tableinit() {
		tl = (TableLayout) findViewById(R.id.tableLayoutRequestedRides);
		
		TextView departure_time = new TextView(this);
		departure_time.setText(getResources().getString(R.string.departure_time));
        TextView arrival_time = new TextView(this);
        arrival_time.setText(getResources().getString(R.string.arrival_time));
        TextView from = new TextView(this);
        from.setText(getResources().getString(R.string.location_from));
        TextView destination = new TextView(this);
        destination.setText(getResources().getString(R.string.location_destination));
        
        TableRow rowHeader = new TableRow(this);

        rowHeader.addView(departure_time);
        rowHeader.addView(arrival_time);
        rowHeader.addView(from);
        rowHeader.addView(destination);   
        
        tl.addView(rowHeader, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        addTableRow("", "", "", "");
        tl.setStretchAllColumns(true);		
	}

	/**
	 * Adds a table row to a given table
	 * @param departurev
	 * @param arrivalv
	 * @param fromv
	 * @param destinationv
	 */
	private void addTableRow(String departurev, String arrivalv, String fromv, String destinationv){
		TextView departure_time = new TextView(this);
		departure_time.setText(departurev);
        TextView arrival_time = new TextView(this);
        arrival_time.setText(arrivalv);
        TextView from = new TextView(this);
        from.setText(fromv);
        TextView destination = new TextView(this);
        destination.setText(destinationv);
        
        TableRow datarow = new TableRow(this);

        datarow.addView(departure_time);
        datarow.addView(arrival_time);
        datarow.addView(from);
        datarow.addView(destination);   
        
        tl.addView(datarow, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
        tl.setStretchAllColumns(true);		
	}

	/**
	 * Shows the date picker
	 * @param v
	 */
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
		
		if(DatePickerFragment.year == 2014){
			tl.removeAllViewsInLayout();
			tableinit();
		}
		if(DatePickerFragment.year == 2013){
			tl.removeAllViewsInLayout();
			tableinit();
			addTableRow("08:30", "12:00", "Groningen", "Berlijn");
		}
		tl.invalidate();
	    tl.refreshDrawableState();	
	}
}
