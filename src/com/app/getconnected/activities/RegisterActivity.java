package com.app.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.getconnected.R;

public class RegisterActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}
	
	public void register(View view)
	{
		Log.d("DEBUG", "register button has been pressed");
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivityForResult(intent, 1);
	}
}
