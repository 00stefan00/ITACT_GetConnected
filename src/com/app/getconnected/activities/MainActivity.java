package com.app.getconnected.activities;

import com.app.getconnected.R;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initLayout(R.string.title_activity_main, false, false, false, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void onResume(){
	//	Need to write a fancy check here (if language changed)
		super.onResume();
		this.setContentView(R.layout.activity_main);
		Log.i("DEBUG", "resume");
	}
}
