package com.app.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.app.getconnected.R;
import com.exception.getconnected.FieldValidationException;
import com.util.getconnected.FieldValidator;

/**
 * @author getConnected 2
 */

public class RegisterActivity extends BaseActivity {
	
	EditText fieldUsername;
	EditText fieldPassword;
	EditText fieldTelephoneNumber;
	EditText fieldEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initLayout(R.string.title_activity_registeractivity, true, true, true, true);
		
		fieldUsername=(EditText) findViewById(R.id.username);
		fieldPassword=(EditText) findViewById(R.id.password);
		fieldTelephoneNumber=(EditText) findViewById(R.id.telephone_number);
		fieldEmail=(EditText) findViewById(R.id.emailText);

		buttonOk.setText(getString(R.string.transportation_result_next));
		buttonOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(validateFields())
				{
					Intent intent = new Intent(RegisterActivity.this, RegisterAdditionalActivity.class);
					intent.putExtra("username", fieldUsername.getText().toString());
					intent.putExtra("password", fieldPassword.getText().toString());
					intent.putExtra("email", fieldEmail.getText().toString());
					intent.putExtra("telephoneNumber", fieldTelephoneNumber.getText().toString());
					startActivity(intent);
				}
			}
		});
	}
	
	public boolean validateFields()
	{
		Boolean validInput=true;
		EditText[] fieldsToValidate={
			fieldUsername, 
			fieldPassword,
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
		return validInput;
	}
}
