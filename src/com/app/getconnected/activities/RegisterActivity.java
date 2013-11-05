package com.app.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.getconnected.R;
import com.app.getconnected.config.Config;
import com.app.getconnected.rest.RESTRequest.Method;
import com.app.getconnected.security.Register;
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
		fieldEmail=(EditText) findViewById(R.id.emailText);
		fieldGender=(RadioGroup) findViewById(R.id.gender);
		fieldGender.check(R.id.gender_male);
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
			if(attemptApiRegister())
			{
				Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
				startActivityForResult(intent, 1);
			}
			else
			{
				fieldUsername.setError(getString(R.string.registration_failed));
			}
		}
	}
	private boolean attemptApiRegister()
	{
		String username=fieldUsername.getText().toString();
		String password=fieldPassword.getText().toString();
		String firstName=fieldFirstName.getText().toString();
		String lastName=fieldLastName.getText().toString();
		String telephoneNumber=fieldTelephoneNumber.getText().toString();
		String email=fieldEmail.getText().toString();
		String gender=((RadioButton)findViewById(fieldGender.getCheckedRadioButtonId())).getTag().toString();

		Register register = new Register(username, password, firstName, lastName, telephoneNumber, email, gender);
		Boolean success = false;
		try {
			String body = register.attemptApiRequest(Config.OPEN_RIDE_API + "register", Method.POST, "RegisterRequest");
			if(body.equals("body")) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
}
