package com.example.getconnected.activities;

import java.util.ArrayList;

import com.example.getconnected.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class TransportResultActivity extends BaseActivity {

	private int page = 0;
	private ArrayList<String> list;
	
	
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
	
	private void nextPage(){
		//set next page
	}
	
	private void prefPage(){
		if(page == 0){
			Button prefButton = (Button) findViewById(R.id.transport_results_pref);
			prefButton.setVisibility(View.INVISIBLE);
			return;
		}
		
		//set previous page
	}

}
