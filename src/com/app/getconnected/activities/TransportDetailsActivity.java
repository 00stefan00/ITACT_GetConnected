package com.app.getconnected.activities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.getconnected.R;

import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TransportDetailsActivity extends BaseActivity {

	private int page = 0;
	private int pageSize = 4;
	private JSONArray legs;
	LinearLayout wrapper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_details);
		initLayout(R.string.title_activity_transport_result, true, true, true,
				true);
		String json = getIntent().getExtras().getString("json");
		wrapper = (LinearLayout) this
				.findViewById(R.id.transport_details_wrapper);
		try {
			JSONObject jObject;
			jObject = new JSONObject(json);
			legs = jObject.getJSONArray("legs");
			initViews();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong =(", Toast.LENGTH_LONG)
					.show();
			return;
		}
		this.setVisibilities();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport_details, menu);
		return true;
	}

	private void initViews() throws JSONException {
		for (int i = (page * pageSize); i < legs.length()
				&& i < (page * pageSize + pageSize); i++) {
			LinearLayout view = (LinearLayout) getLayoutInflater().inflate(
					R.layout.transport_detail_view, wrapper, false);
			initView(legs.getJSONObject(i), view);
			wrapper.addView(view);
		}
	}

	private String getDate(Long time, String format) {
		Date date = new Date(time);
		SimpleDateFormat SDF = new SimpleDateFormat(format);
		return SDF.format(date);
	}

	private void initView(JSONObject leg, LinearLayout view)
			throws JSONException {
		String mode = leg.getString("mode");
		String from = leg.getJSONObject("from").getString("name");
		Long departureTime = leg.getLong("startTime");
		Long arivalTime = leg.getLong("endTime");
		String to = leg.getJSONObject("to").getString("name");

		((TextView) view.findViewById(R.id.transport_details_title))
				.setText(mode);
		((TextView) view
				.findViewById(R.id.transport_details_text_departure_time))
				.setText("" + getDate(departureTime, "H:m"));
		((TextView) view
				.findViewById(R.id.transport_details_text_departure_location))
				.setText(from);
		((TextView) view.findViewById(R.id.transport_details_text_arival_time))
				.setText(("" + getDate(arivalTime, "H:m")));
		((TextView) view
				.findViewById(R.id.transport_details_text_arival_location))
				.setText(to);

	}

	private void setVisibilities() {
		Button prefButton = (Button) findViewById(R.id.transport_details_pref);
		Button nextButton = (Button) findViewById(R.id.transport_details_next);
		if ((page * pageSize + pageSize) >= legs.length()) {

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

	private void removeTableRows() {
		// for (int i = 1; 1 < table.getChildCount(); i++) {
		// table.removeViewAt(i);
		// }
		wrapper.removeAllViews();
	}

	public void nextPage(View v) {
		page++;
		this.removeTableRows();
		try {
			this.initViews();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong =(", Toast.LENGTH_LONG)
					.show();
			return;
		}
		this.setVisibilities();

	}

	public void prefPage(View v) {
		page--;
		this.removeTableRows();
		try {
			this.initViews();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong =(", Toast.LENGTH_LONG)
					.show();
			return;
		}
		this.setVisibilities();

	}

}
