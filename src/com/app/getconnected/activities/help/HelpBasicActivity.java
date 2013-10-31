package com.app.getconnected.activities.help;

import com.app.getconnected.R;
import com.app.getconnected.R.layout;
import com.app.getconnected.R.menu;
import com.app.getconnected.activities.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HelpBasicActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help_basic);
		initLayout(R.string.title_activity_help_basic, true, true, true, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help_basic, menu);
		return true;
	}

}
