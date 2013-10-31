package com.app.getconnected.activities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.app.getconnected.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TransportResultActivity extends BaseActivity {

	private int page = 0;
	private int pageSize = 5;
	private JSONArray itineraries;
	private TableLayout table;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("height = " + this.findViewById(R.id.activity_transport_result_content).getHeight());
		
		setContentView(R.layout.activity_transport_result);
		initLayout(R.string.title_activity_transport_result, true, true, true,
				false);
		String json = getIntent().getExtras().getString("json");
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
			for (int i = (page * pageSize); i < itineraries.length()
					&& i < (page * pageSize + pageSize); i++) {
				itinerariy = itineraries.getJSONObject(i);
				TableRow row = (TableRow) getLayoutInflater().inflate(
						R.layout.transport_result_row, table, false);
				setTextViews(row, itinerariy);
				row.setTag(i);
				setClickEvents(row);
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
		duration.setText("" + (itinerariy.getInt("duration") / 1000 / 60) + " ");
		departure.setText("" + getDate(itinerariy.getLong("startTime"), "H:m")
				+ " ");
		arival.setText("" + getDate(itinerariy.getLong("endTime"), "H:m") + " ");
		transfers.setText("" + itinerariy.getInt("transfers"));
	}

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
