package com.example.getconnected.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.getconnected.R;

public class RegisterActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}
	
	public void register(View view)
	{
		Log.d("DEBUG", "loginButton has been pressed");
	}
}