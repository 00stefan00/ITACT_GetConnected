package com.example.getconnected.activities;

import com.example.getconnected.R;
import com.example.getconnected.R.layout;
import com.example.getconnected.R.menu;
import com.example.getconnected.R.string;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TransportDetailsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_details);
		initLayout(R.string.title_activity_transport_result, true, true, true, false); //todo doe eens string maken
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport_details, menu);
		return true;
	}

}
