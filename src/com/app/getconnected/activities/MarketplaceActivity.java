package com.app.getconnected.activities;

import com.app.getconnected.R;
import com.app.getconnected.notifications.CheckNotifications;

import android.os.Bundle;

public class MarketplaceActivity extends BaseActivity{
	CheckNotifications checkNoti;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marketplace);
		initLayout(R.string.title_activity_marketplace, true, true, true, false);
		checkNoti = new CheckNotifications(this);	
	}
}
