package com.app.getconnected.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.app.getconnected.R;
import com.app.getconnected.config.Config;
import com.app.getconnected.rest.RESTRequest.Method;
import com.app.getconnected.security.Register;
import com.exception.getconnected.FieldValidationException;
import com.util.getconnected.FieldValidator;

public class RegisterAdditionalActivity extends BaseActivity {

	EditText fieldFirstName;
	EditText fieldLastName;
	RadioGroup fieldGender;
	String username;
	String password;
	String email;
	String telephoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_additional);
		initLayout(R.string.title_activity_register_additional, true, true, true, true);

		fieldFirstName=(EditText) findViewById(R.id.first_name);
		fieldLastName=(EditText) findViewById(R.id.last_name);
		fieldGender=(RadioGroup) findViewById(R.id.gender);
		fieldGender.check(R.id.gender_male);

		username = getIntent().getStringExtra("username");
		password = getIntent().getStringExtra("password");
		email = getIntent().getStringExtra("email");
		telephoneNumber = getIntent().getStringExtra("telephoneNumber");

		buttonOk.setText(getString(R.string.register));
		buttonOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				register();
			}
		});
	}

	public void register()
	{
		Boolean validInput=true;
		EditText[] fieldsToValidate={
				fieldFirstName,
				fieldLastName
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
				Intent intent = new Intent(RegisterAdditionalActivity.this, LoginActivity.class);
				startActivityForResult(intent, 1);
			}
			else
			{
				Toast.makeText(this, getString(R.string.registration_failed), Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}
	private boolean attemptApiRegister()
	{
		String firstName=fieldFirstName.getText().toString();
		String lastName=fieldLastName.getText().toString();
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
