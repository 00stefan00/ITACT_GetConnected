package com.app.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.app.getconnected.R;

public class LoginActivity extends BaseActivity {
	
	EditText fieldUsername;
	EditText fieldPassword;
	EditText focusView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		fieldUsername = (EditText) findViewById(R.id.username);
		fieldPassword = (EditText) findViewById(R.id.password);
	}
	
	public void register(View view)
	{
		Log.d("DEBUG", "registerButton has been pressed");
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivityForResult(intent, 1);
	}
	
	public void attemptLogin(View view)
	{
		Boolean cancel=false;
		fieldUsername.setError(null);
		fieldPassword.setError(null);
		String user = fieldUsername.getText().toString();
		String password = fieldPassword.getText().toString();
		Log.d("DEBUG", "loginButton has been pressed");
		if (TextUtils.isEmpty(password)) {
			fieldPassword.setError(getString(R.string.error_field_required));
			focusView = fieldPassword;
			cancel = true;
		} else if (password.length() < 4) {
			fieldPassword.setError(getString(R.string.error_invalid_password));
			focusView = fieldPassword;
			cancel = true;
		}
		
		if (TextUtils.isEmpty(user)) {
			fieldUsername.setError(getString(R.string.error_field_required));
			focusView = fieldUsername;
			cancel = true;
		}
		
		if(!cancel)
		{
			loggedIn=true;
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivityForResult(intent, 1);
		}
	}
}
