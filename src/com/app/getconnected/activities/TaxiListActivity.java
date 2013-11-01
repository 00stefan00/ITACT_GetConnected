package com.app.getconnected.activities;

import com.app.getconnected.R;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class TaxiListActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taxi_list);
		initLayout(R.string.title_activity_transport, true, true, true, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.taxi_list, menu);
		return true;
	}
	
	public void call(View view){
		 Intent intent = new Intent(Intent.ACTION_DIAL);
		 intent.setData(Uri.parse("tel:"+(String)view.getTag()));
		 startActivity(intent);
	}

}
