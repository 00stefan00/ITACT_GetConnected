package com.app.getconnected.activities;

import com.app.getconnected.R;
import com.app.getconnected.R.layout;
import com.app.getconnected.R.menu;
import com.app.getconnected.network.PlacesAutoCompleteAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LocationSelectorActivity extends BaseActivity implements OnItemClickListener {

	private AutoCompleteTextView autoCompView;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_selector);
		initLayout(R.string.title_activity_location_selector, true, true, true, false);
		
		type = getIntent().getExtras().getString("type");
		
		autoCompView = (AutoCompleteTextView) findViewById(R.id.location_selector_input);
		autoCompView.setHint(type);
	    
		autoCompView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.places_list_item));
		autoCompView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location_selector, menu);
		return true;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("location", str);
        resultIntent.putExtra("type", type);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }	

}
