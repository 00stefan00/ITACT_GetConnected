package com.example.getconnected.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.getconnected.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TransportResultActivity extends BaseActivity {

	private int page = 0;
	private ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_result);
		initLayout(R.string.title_activity_transport_result, true, true, true,
				false);
		String json = getIntent().getExtras().getString("json");
		initTable(json);

	}

	private void initTable(String json) {
		JSONObject jObject = null;
		JSONArray itineraries = null;
		JSONObject itinerariy = null;
		try {
			jObject = new JSONObject(json);
			itineraries = jObject.getJSONArray("itineraries");
			TableLayout table = (TableLayout)findViewById(R.id.transport_result_table);
			for (int i = 0; i < itineraries.length(); i++) {
				itinerariy = itineraries.getJSONObject(i);
				TableRow row = (TableRow)findViewById(R.id.transport_result_row);
				setTextViews(row, itinerariy);
				table.addView(row);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setTextViews(TableRow row, JSONObject itinerariy) throws JSONException {
		TextView departure = (TextView)row.findViewById(R.id.transport_result_text_departure);
		TextView duration = (TextView)row.findViewById(R.id.transport_result_text_duration);
		TextView arival = (TextView)row.findViewById(R.id.transport_result_text_arival);
		TextView transfers = (TextView)row.findViewById(R.id.transport_result_text_transfers);
		departure.setText(itinerariy.getString("duration"));
		duration.setText(itinerariy.getString("startTime"));
		arival.setText(itinerariy.getString("endTime"));
		transfers.setText(itinerariy.getInt("transfers"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport_result, menu);
		return true;
	}

	private void nextPage() {
		// set next page
	}

	private void prefPage() {
		if (page == 0) {
			Button prefButton = (Button) findViewById(R.id.transport_results_pref);
			prefButton.setVisibility(View.INVISIBLE);
			return;
		}

		// set previous page
	}

}
