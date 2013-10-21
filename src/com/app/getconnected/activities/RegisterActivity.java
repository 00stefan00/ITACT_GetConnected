package com.app.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.app.getconnected.R;
import com.exception.getconnected.FieldValidationException;
import com.util.getconnected.FieldValidator;

public class RegisterActivity extends BaseActivity {
	
	EditText fieldUsername;
	EditText fieldPassword;
	EditText fieldFirstName;
	EditText fieldLastName;
	EditText fieldTelephoneNumber;
	EditText fieldEmail;
	RadioGroup fieldGender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initLayout(R.string.title_activity_registeractivity, true, true, true, false);
		
		fieldUsername=(EditText) findViewById(R.id.username);
		fieldPassword=(EditText) findViewById(R.id.password);
		fieldFirstName=(EditText) findViewById(R.id.first_name);
		fieldLastName=(EditText) findViewById(R.id.last_name);
		fieldTelephoneNumber=(EditText) findViewById(R.id.telephone_number);
		fieldEmail=(EditText) findViewById(R.id.email);
		fieldGender=(RadioGroup) findViewById(R.id.gender);
	}
	
	public void register(View view)
	{
		Boolean validInput=true;
		EditText[] fieldsToValidate={
			fieldUsername, 
			fieldPassword, 
			fieldFirstName, 
			fieldLastName, 
			fieldTelephoneNumber, 
			fieldEmail
		};
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
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivityForResult(intent, 1);
		}
	}
}
