package com.app.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.app.getconnected.R;
import com.app.getconnected.rest.RESTRequest;
import com.app.getconnected.rest.RESTRequest.Method;
import com.app.getconnected.security.Login;
import com.exception.getconnected.FieldValidationException;
import com.util.getconnected.FieldValidator;

public class LoginActivity extends BaseActivity {
	
	EditText fieldUsername;
	EditText fieldPassword;
	EditText focusView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initLayout(R.string.title_activity_loginactivity, true, true, true, false);
		fieldUsername = (EditText) findViewById(R.id.username);
		fieldPassword = (EditText) findViewById(R.id.password);
		if(loggedIn)
		{
			Intent intent = new Intent(getApplicationContext(), MarketplaceActivity.class);
			startActivityForResult(intent, 1);
		}
	}
	public void onPause()
	{
		super.onPause();
		this.finish();
	}
	
	public void register(View view)
	{
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivityForResult(intent, 1);
	}
	
	public void attemptLogin(View view)
	{
		Boolean validInput=true;
		EditText[] fieldsToValidate={fieldUsername, fieldPassword};
		for(EditText textField : fieldsToValidate)
		{
			textField.setError(null);
			try {
				FieldValidator.validateTextField(textField);
			} catch (FieldValidationException e) {
				textField.setError(getString(e.getIndex()));
				validInput=false;
			}
		}
		if(validInput)
		{
			if(getFileStreamPath(Login.fileName).isFile()) {
				loggedIn = true;
				startIntent();
			}
			else if(attemptApiLogin())
			{
				loggedIn=true;
				startIntent();
			}
			else
			{
				fieldUsername.setError(getString(R.string.login_incorrect_credentials));
			}
		}
	}

	private void startIntent() {
		Intent intent = new Intent(LoginActivity.this, MarketplaceActivity.class);
		startActivityForResult(intent, 1);
	}

	private boolean attemptApiLogin()
	{
		String username=fieldUsername.getText().toString();
		String password=fieldPassword.getText().toString();
		Login login = new Login(this, username, password);
		String response = "";
		boolean success = false;
		try {
			response = login.attemptApiRequest(RESTRequest.API_URL + "/login", Method.GET, "loginRequest");
			if(response.equals("body")) {
				login.saveCredentials();
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
}
