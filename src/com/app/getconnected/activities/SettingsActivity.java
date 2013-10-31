package com.app.getconnected.activities;

import java.util.ArrayList;
import java.util.Locale;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.getconnected.R;

public class SettingsActivity extends BaseActivity {
	
	ArrayList<String> spinnerArray = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		initLayout(R.string.title_activity_settings, true, true, true, false);
	}
	
	

	public void changeLanguage(String language) {
		String languageToLoad  = language; 
	    Locale locale = new Locale(languageToLoad); 
	    Locale.setDefault(locale);
	    Configuration config = new Configuration();
	    config.locale = locale;
	    getBaseContext().getResources().updateConfiguration(config, 
	      getBaseContext().getResources().getDisplayMetrics());
	    this.setContentView(R.layout.activity_settings);
	}
	
	
}
