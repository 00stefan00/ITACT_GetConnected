package com.app.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.app.getconnected.R;
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
			loggedIn=true;
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivityForResult(intent, 1);
		}
	}
}
