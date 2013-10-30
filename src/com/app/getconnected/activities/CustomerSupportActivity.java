package com.app.getconnected.activities;

import java.sql.Timestamp;
import java.util.HashMap;

import com.app.getconnected.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CustomerSupportActivity extends BaseActivity {
	
	EditText fieldFullname;
	EditText emailAdres;
	EditText complaint;
	RadioGroup group;
	RadioButton choiceButton;
	View companyChoice;
	String choiceName;
	HashMap<String, String> map = new HashMap<String, String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_support);
		initLayout(R.string.title_activity_customer_support, true, true, true, false);
		
		//Email address dummies
		map.put("Arriva", "complaints@arriva.nl");
		map.put("Connexion", "complaints@connexion.nl");
		map.put("Qbuzz", "complaints@qbuzz.nl");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_support, menu);
		return true;
	}
	
	public void getFields(){
		fieldFullname = (EditText) findViewById(R.id.fullnameText);
		emailAdres = (EditText) findViewById(R.id.emailText);
		complaint = (EditText) findViewById(R.id.complaintText);
		
		group = (RadioGroup) findViewById(R.id.company_choice);
		
		int id= group.getCheckedRadioButtonId();
		View radioButton = group.findViewById(id);
		int radioId = group.indexOfChild(radioButton);
		choiceButton = (RadioButton) group.getChildAt(radioId);
	}
	
	public boolean dataIsValid(){
		if(fieldFullname.getText().toString().equals("")){
			Toast.makeText(CustomerSupportActivity.this, "Please enter your name.", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(emailAdres.getText().toString().equals("")){
			Toast.makeText(CustomerSupportActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(complaint.getText().toString().equals("")){
			Toast.makeText(CustomerSupportActivity.this, "Please enter your complaint or question.", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(group.getCheckedRadioButtonId() == -1){
			Toast.makeText(CustomerSupportActivity.this, "Please select a transport company.", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	public boolean sendMailToTransportCompany(View view){
		getFields();
		
		if(!dataIsValid()){
			return false;
		}
		
		String fullname =fieldFullname.getText().toString();
		String email = emailAdres.getText().toString();
		String complaintValue = complaint.getText().toString();
		String ticket = ("Ticket: " + fullname + ", " + new Timestamp(System.currentTimeMillis()));
		choiceName = (String) choiceButton.getText();
		
		//Create the e-mail intent
		Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + map.get(choiceName)));
		i.putExtra(Intent.EXTRA_CC, new String[] {email});
		i.putExtra(Intent.EXTRA_SUBJECT, ticket);
		i.putExtra(Intent.EXTRA_TEXT   , ("Name: " + fullname + "\nEmail adres: " + email + "\n\n Complaint or question:\n" + complaintValue));
		try {
			startActivity(i);
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(CustomerSupportActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		    return false;
		}
		return true;
	}
}
