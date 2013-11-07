package com.app.getconnected.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.app.getconnected.R;
import com.app.getconnected.factories.details.Detailfactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TransportDetailsActivity extends BaseActivity {

	private int page = 0;
	private int pageSize = 3;
	/**
	 * The data we are using
	 */
	private JSONArray legs;
	/**
	 * The layout we are using
	 */
	LinearLayout wrapper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_details);
		initLayout(R.string.title_activity_transport_result, true, true, true,
				false);
		String json = getIntent().getExtras().getString("json");
		wrapper = (LinearLayout) this
				.findViewById(R.id.transport_details_wrapper);
		try {
			JSONObject jObject;
			jObject = new JSONObject(json);
			legs = jObject.getJSONArray("legs");			
			
			initViews();
		} catch (Exception e) {
			
			e.printStackTrace();
			Toast.makeText(this, R.string.wrongMessage, Toast.LENGTH_LONG)
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

	/**
	 * @throws JSONException
	 * Calls the factory and sets the views
	 * Initializes the views
	 */
	private void initViews() throws JSONException {
		for (int i = (page * pageSize); i < legs.length()
				&& i < (page * pageSize + pageSize); i++) {
				wrapper.addView(Detailfactory.getView(legs.getJSONObject(i), TransportDetailsActivity.this));
		}
	}

	/**
	 * Sets the visibilities for the pager buttons
	 */
	private void setVisibilities() {
		Button prefButton = (Button) findViewById(R.id.transport_details_pref);
		Button nextButton = (Button) findViewById(R.id.transport_details_next);
		if (legs != null && (page * pageSize + pageSize) >= legs.length()) {
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
	 * removes all views form our wrapper
	 */
	private void removeWrapperViews() {
		wrapper.removeAllViews();
	}

	/**
	 * Changes the page to the next one
	 * @param v
	 */
	public void nextPage(View v) {
		page++;
		this.removeWrapperViews();
		try {
			this.initViews();
		} catch (Exception e) {
			Toast.makeText(this, R.string.wrongMessage, Toast.LENGTH_LONG)
					.show();
			return;
		}
		this.setVisibilities();

	}

	/**
	 * Changes the page to the previous one
	 * @param v
	 */
	public void prefPage(View v) {
		page--;
		this.removeWrapperViews();
		try {
			this.initViews();
		} catch (Exception e) {
			Toast.makeText(this, R.string.wrongMessage, Toast.LENGTH_LONG)
					.show();
			return;
		}
		this.setVisibilities();

	}

}
