package com.app.getconnected.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import com.app.getconnected.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("DefaultLocale")
public class TransportResultActivity extends BaseActivity {

	private int page = 0;
	private int pageSize = 10;
	private JSONArray itineraries;
	private TableLayout table;
	private String departureLocation;
	private String arivalLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_result);
		initLayout(R.string.title_activity_transport_result, true, true, true,
				false);
		String json = getIntent().getExtras().getString("json");
		try {
			JSONObject jObject;
			jObject = (new JSONObject(json)).getJSONObject("plan");
			setLocations(jObject);
			itineraries = jObject.getJSONArray("itineraries");
		} catch (Exception e) {
			itineraries = new JSONArray();
		}
		table = (TableLayout) findViewById(R.id.transport_result_table);

		initTable();
		this.setVisibilities();
	}
	
	/**
	 * @param jObject
	 * @throws JSONException
	 */
	private void setLocations(JSONObject jObject) throws JSONException{
		departureLocation = jObject.getJSONObject("from").getString("name");
		arivalLocation = jObject.getJSONObject("to").getString("name");
	}

	/**
	 * Initializes the table
	 * instatiate the table view.
	 */
	private void initTable() {
		JSONObject itinerariy = null;
		try {
			if (itineraries.length() <= 0) {
				TableRow row = (TableRow) getLayoutInflater().inflate(
						R.layout.transport_no_result_row, table, false);
				table.addView(row);
				return;
			}

			for (int i = (page * pageSize); i < itineraries.length()
					&& i < (page * pageSize + pageSize); i++) {
				itinerariy = itineraries.getJSONObject(i);
				TableRow row = (TableRow) getLayoutInflater().inflate(
						R.layout.transport_result_row, table, false);
				setTextViews(row, itinerariy);
				row.setTag(i);
				setClickEvents(row);
				table.addView(row);
				row.setBackgroundResource(R.drawable.table_style);
			}
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong =(", Toast.LENGTH_LONG)
					.show();
			return;
		}
	}

	/**
	 * Sets the text views
	 * @param row
	 * @param itinerariy
	 * @throws Exception
	 * @param row
	 * @param itinerariy
	 * @throws Exception
	 * 
	 * Sets the text views in the table
	 */
	private void setTextViews(TableRow row, JSONObject itinerariy)
			throws Exception {
		TextView departure = (TextView) row
				.findViewById(R.id.transport_result_text_departure);
		TextView duration = (TextView) row
				.findViewById(R.id.transport_result_text_duration);
		TextView arival = (TextView) row
				.findViewById(R.id.transport_result_text_arival);
		TextView transfers = (TextView) row
				.findViewById(R.id.transport_result_text_transfers);
		TextView arivalLocation = (TextView) findViewById(R.id.transportation_result_arival_header);
		TextView departureLocation = (TextView) findViewById(R.id.transportation_result_departure_header);

		duration.setText(""
				+ minutesToHourString(itinerariy.getInt("duration") / 1000 / 60)
				+ " ");
		departure.setText(""
				+ getDate(itinerariy.getLong("startTime"), "HH:mm") + " ");
		arival.setText("" + getDate(itinerariy.getLong("endTime"), "HH:mm")
				+ " ");
		transfers.setText("" + itinerariy.getInt("transfers"));
		arivalLocation.setText(this.arivalLocation);
		departureLocation.setText(this.departureLocation);
	}

	/**
	 * Converts minutes to hours
	 * @param t
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	private String minutesToHourString(int t) {
		int hours = t / 60; // since both are ints, you get an int
		int minutes = t % 60;
		return String.format("%d:%02d", hours, minutes);
	}

	/**
	 * Sets the events for clicks
	 * @param row
	 */
	private void setClickEvents(TableRow row) {
		row.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View row) {
				int index = (Integer) row.getTag();
				Intent intent = new Intent(TransportResultActivity.this,
						TransportDetailsActivity.class);
				try {
					intent.putExtra("json", itineraries.getJSONObject(index)
							.toString());
				} catch (JSONException e) {
					Toast.makeText(TransportResultActivity.this,
							"Something went wrong =(", Toast.LENGTH_LONG)
							.show();
					return;
				}
				startActivity(intent);
			}
		});

	}

	/**
	 * @param time
	 * @param format
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	private String getDate(Long time, String format) {
		Date date = new Date(time);
		SimpleDateFormat SDF = new SimpleDateFormat(format);
		return SDF.format(date);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport_result, menu);
		return true;
	}

	/**
	 * removes all views except the header
	 */
	private void removeTableRows() {
		table.removeAllViews();
	}

	/**
	 * Sets the visibilities of the pager buttons.
	 */
	private void setVisibilities() {
		Button prefButton = (Button) findViewById(R.id.transport_results_pref);
		Button nextButton = (Button) findViewById(R.id.transport_results_next);
		if ((page * pageSize + pageSize) >= itineraries.length()) {

			nextButton.setVisibility(View.INVISIBLE);
		} else {
			nextButton.setVisibility(View.VISIBLE);
		}
		if (page <= 0) {
			prefButton.setVisibility(View.INVISIBLE);
		} else {
			prefButton.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * Show the next page.
	 * @param v
	 */
	public void nextPage(View v) {
		page++;
		this.removeTableRows();
		this.initTable();
		this.setVisibilities();

	}

	/**
	 * Show the previous page.
	 * @param v
	 */
	public void prefPage(View v) {
		page--;
		this.removeTableRows();
		this.initTable();
		this.setVisibilities();

	}
}
