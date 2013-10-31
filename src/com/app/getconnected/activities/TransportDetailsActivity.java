package com.app.getconnected.activities;

import java.lang.reflect.InvocationTargetException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.getconnected.R;
import com.app.getconnected.factories.details.Detailfactory;

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
			
			e.printStackTrace();
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
			try {
				wrapper.addView(Detailfactory.getView(legs.getJSONObject(i), TransportDetailsActivity.this));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

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

	private void removeTableRows() {
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
