package com.app.getconnected.activities.help;

import android.os.Bundle;
import android.view.Menu;

import com.app.getconnected.R;
import com.app.getconnected.activities.BaseActivity;

/**
 * @author getConnected 1
 */
public class HelpPlanActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help_plan);
		initLayout(R.string.title_activity_help_plan, true, true, true, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help_plan, menu);
		return true;
	}

}
