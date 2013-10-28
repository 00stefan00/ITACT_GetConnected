package com.app.getconnected.activities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.app.getconnected.R;

public class TransportResultActivity extends BaseActivity {

	private int page = 0;
	private int pageSize = 5;
	private JSONArray itineraries;
	private TableLayout table;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_result);
		initLayout(R.string.title_activity_transport_result, true, true, true,
				false);
		String json = getIntent().getExtras().getString("json");
		System.out.println(json);
		try {
			JSONObject jObject;
			jObject = (new JSONObject(json)).getJSONObject("plan");
			itineraries = jObject.getJSONArray("itineraries");
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong =(", Toast.LENGTH_LONG)
					.show();
			return;
		}
		table = (TableLayout) findViewById(R.id.transport_result_table);
		initTable();
		this.setVisibilities();

	}

	private void initTable() {
		JSONObject itinerariy = null;
		try {
			for (int i = (page * pageSize); i < itineraries.length() && i < (page * pageSize + pageSize); i++) {
				itinerariy = itineraries.getJSONObject(i);
				TableRow row = (TableRow) getLayoutInflater().inflate(
						R.layout.transport_result_row, table, false);
				setTextViews(row, itinerariy);
				table.addView(row);
			}
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong =(", Toast.LENGTH_LONG)
					.show();
			return;
		}
	}

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
		departure.setText("" + (itinerariy.getInt("duration") / 1000 / 60)
				+ " ");
		duration.setText("" + getDate(itinerariy.getLong("startTime"), "H:m")
				+ " ");
		arival.setText("" + getDate(itinerariy.getLong("endTime"), "H:m") + " ");
		transfers.setText("" + itinerariy.getInt("transfers"));
	}

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

	// removes all views except the header.
	private void removeTableRows() {
		// for (int i = 1; 1 < table.getChildCount(); i++) {
		// table.removeViewAt(i);
		// }
		table.removeAllViews();
	}

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

	public void nextPage(View v) {
		page++;
		this.removeTableRows();
		this.initTable();
		this.setVisibilities();

	}

	public void prefPage(View v) {
		page--;
		this.removeTableRows();
		this.initTable();
		this.setVisibilities();

	}

}
