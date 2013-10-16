package com.example.getconnected.activities;

import com.example.getconnected.R;
import com.example.getconnected.sqllite.DatabaseHandler;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	private Button buttonTransport;
	private Button buttonMap;
	private Button buttonMarket;
	private View buttonService;
	private Button buttonHelp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initLayout(R.string.title_activity_main, false, false, false, false);
		
		buttonTransport = (Button) findViewById(R.id.home_button_transport);
		buttonTransport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, TransportActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		
		buttonMap = (Button) findViewById(R.id.home_button_map);
		buttonMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MapActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		
		buttonMarket = (Button) findViewById(R.id.home_button_marketplace);
		buttonMarket.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MarketplaceActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		
		buttonService = (Button) findViewById(R.id.home_button_service);
		buttonService.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this, TransportActivity.class);
//				startActivityForResult(intent, 1);
			}
		});		
		
		buttonHelp = (Button) findViewById(R.id.home_button_help);
		buttonHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this, TransportActivity.class);
//				startActivityForResult(intent, 1);
			}
		});		
		
		DatabaseHandler databaseHandler = new DatabaseHandler(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
