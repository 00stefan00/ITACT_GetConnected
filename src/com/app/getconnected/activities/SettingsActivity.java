package com.app.getconnected.activities;

import java.util.Locale;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.app.getconnected.R;

public class SettingsActivity extends BaseActivity {

	RadioGroup languageOptions;
	int currentScaleChoice = 0;
	int currentLanguageChoice = 0;
	final static int ENGLISH = 0;
	final static int DUTCH = 1;
	String[] languages = {"en", "nl"};
	int[] fontScaleOptions = {R.string.small, R.string.medium, R.string.big};
	String[] fontSpinnerArray = new String[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		initLayout(R.string.title_activity_settings, true, true, true, true);
		buttonOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				applySettings(v);
			}
		});
		
		updateSpinner();
		languageOptions = (RadioGroup) findViewById(R.id.languagePicker);
		initLanguagePicker();
	}

	private void initLanguagePicker() {
		languageOptions = (RadioGroup) findViewById(R.id.languagePicker);
		for(int i = 0; i < languages.length; i++) {
			RadioButton button;
		    button = new RadioButton(this);
		    button.setText(languages[i]);
		    button.setId(i);
		    languageOptions.addView(button);		    
		}
		
	}

	public void applySettings(View view){
		Configuration config = new Configuration();
		Spinner s = (Spinner) findViewById(R.id.font_scale);
		String scale_choice = s.getSelectedItem().toString();
		int choice = s.getSelectedItemPosition();
                
		if (scale_choice.equals(getResources().getString(R.string.small))){
			changeScale(config, choice, 0.7);
		}
		else if(scale_choice.equals(getResources().getString(R.string.medium))){
			changeScale(config, choice, 1);
		}
		else if(scale_choice.equals(getResources().getString(R.string.big))){
			changeScale(config, choice, 1.3);
		}
		else{
			Log.e("This should not happen error", "Not a valid choice!");
		}
		
		RadioGroup group = (RadioGroup) findViewById(R.id.languagePicker);
		currentLanguageChoice = group.getCheckedRadioButtonId();

		if (currentLanguageChoice != -1){
			changeLanguage(config, languages[currentLanguageChoice]);
		}
		updateContent(config);
	}

	public void changeLanguage(Configuration config, String language) {
	    String languageToLoad = language;
	    Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
	    config.locale = locale;
	}
	
	public void changeScale(Configuration config, int choice, double scale) {
	    Log.d("Default font scale", ""+config.fontScale);
	    config.fontScale = (float) scale;
	    currentScaleChoice = choice;
	}
	
	public void updateContent(Configuration config){
	    getBaseContext().getResources().updateConfiguration(config, 
	    getBaseContext().getResources().getDisplayMetrics());
	    this.setContentView(R.layout.activity_settings);
	    initLayout(R.string.title_activity_settings, true, true, true, true);
	    buttonOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				applySettings(v);
			}
		});
	    updateSpinner();
	    initLanguagePicker();
	}

	public void updateSpinner(){
	    for (int i=0; i<fontScaleOptions.length; i++){
	    	fontSpinnerArray[i] = getResources().getString(fontScaleOptions[i]);
	    }
		
        Spinner s = (Spinner) findViewById(R.id.font_scale);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
        android.R.layout.simple_spinner_item, fontSpinnerArray);
        s.setAdapter(adapter);
        s.setSelection(currentScaleChoice);
	}
		
}
