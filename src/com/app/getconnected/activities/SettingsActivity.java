package com.app.getconnected.activities;

import java.util.Locale;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.getconnected.R;

public class SettingsActivity extends BaseActivity {

	RadioGroup languageOptions;
	final static int ENGLISH = 0;
	final static int DUTCH = 1;
	String[] languages = {"en", "nl"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		initLayout(R.string.title_activity_settings, true, true, true, false);
		languageOptions = (RadioGroup) findViewById(R.id.languagePicker);
		
		initLanguagePicker();
	}

	private void initLanguagePicker() {
		for(int i = 0; i < languages.length; i++) {
			RadioButton button;
		    button = new RadioButton(this);
		    button.setText(languages[i]);
		    button.setId(i);
		    button.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		            onButtonClicked(v);
		        }
		    });
		    languageOptions.addView(button);		    
		}
	}

	protected void onButtonClicked(View v) {
		changeLanguage(languages[v.getId()]);		
	}

	public void changeLanguage(String language) {
		String languageToLoad = language;
		Locale locale = new Locale(languageToLoad);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());
		this.setContentView(R.layout.activity_settings);
		initLanguagePicker();
	}

}
