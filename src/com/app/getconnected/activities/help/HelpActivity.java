package com.app.getconnected.activities.help;

import com.app.getconnected.activities.BaseActivity;
import com.app.getconnected.R;
import android.os.Bundle;
import android.view.Menu;

public class HelpActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		initLayout(R.string.title_activity_help, true, true, true, false);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

}
