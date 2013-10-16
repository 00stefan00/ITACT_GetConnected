package com.example.getconnected.activities;

import com.example.getconnected.R;
import com.example.getconnected.R.layout;
import com.example.getconnected.R.menu;
import com.example.getconnected.R.string;

import android.os.Bundle;
import android.view.Menu;

public class TransportResultActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_result);
		initLayout(R.string.title_activity_transport_result, true, true, true, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport_result, menu);
		return true;
	}

}
