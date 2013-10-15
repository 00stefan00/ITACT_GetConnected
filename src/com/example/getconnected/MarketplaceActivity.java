package com.example.getconnected;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MarketplaceActivity extends BaseActivity{

	private Button request_ride_btn;
	private Button display_requests_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_marketplace);
	
	request_ride_btn = (Button) findViewById(R.id.request_ride_btn);
	request_ride_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MarketplaceActivity.this, OfferRideActivity.class);
			startActivityForResult(intent, 1);
		}
	});
	
	
	display_requests_btn = (Button) findViewById(R.id.display_requests_btn);
	display_requests_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MarketplaceActivity.this, RequestedRidesActivity.class);
			startActivityForResult(intent, 1);
		}
	});
	
	}
}
