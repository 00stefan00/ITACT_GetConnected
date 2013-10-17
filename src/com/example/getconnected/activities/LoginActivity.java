package com.example.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.getconnected.R;

public class LoginActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	public void register(View view)
	{
		Log.d("DEBUG", "registerButton has been pressed");
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivityForResult(intent, 1);
	}
	
	public void login(View view)
	{
		Log.d("DEBUG", "loginButton has been pressed");
	}
}