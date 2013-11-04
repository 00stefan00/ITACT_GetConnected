package com.app.getconnected.activities;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.getconnected.R;
import com.app.getconnected.network.GeoLocation;
import com.app.getconnected.rides.SaveRides;
import com.exception.getconnected.FieldValidationException;
import com.util.getconnected.FieldValidator;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateRideActivity extends BaseActivity{
	
  JSONObject output = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createride);
		initLayout(R.string.title_activity_createride, true, true, true, true);
		
		Button createRideButton = (Button) findViewById(R.id.footer_button_ok);
		createRideButton.setOnClickListener(new OnClickListener(){
      @Override
      public void onClick(View view) {
        //Log.d("test","createoutput:"+createOutput().toString());
        output = createOutput();
        EditText[] fieldsToValidate = {
            (EditText) findViewById(R.id.editText1), 
            (EditText) findViewById(R.id.editText2), 
            (EditText) findViewById(R.id.editText3), 
            (EditText) findViewById(R.id.editText4), 
        };
        doTheValidation(fieldsToValidate);
      }
		});
	}
	
	public void doTheValidation(EditText[] fieldsToValidate){
	    Boolean validInput=true;
	    for(EditText textField : fieldsToValidate) {
	      textField.setError(null);
	      try {
	        FieldValidator.validateTextField(textField);
	      } catch (FieldValidationException e) {
	        textField.setError(getString(e.getIndex()));
	        validInput=false;
	      }
	    }
	    if(validInput) {
	      if(attemptToCreateRide(output.toString())) {
	        Intent intent = new Intent(CreateRideActivity.this, MarketplaceActivity.class);
	        startActivityForResult(intent, 1);
	      }
      } else {
        new AlertDialog.Builder(this)
        .setMessage(getString(R.string.field_validation_input_required))
        .show();
        //fieldUsername.setError(getString(R.string.registration_failed));
      }
	}
	
	public boolean attemptToCreateRide(String input){
	  
	  SaveRides saveRides = new SaveRides();
	  saveRides.setArgument(input);
	  saveRides.setKey("JSON");
	  saveRides.setJsonKey("{\"PostOfferResponse\":{\"rideId\":14039}}");
	  saveRides.setURL("/users/"+saveRides.getUsername()+"/rides/offers");
	  
	  try {
      saveRides.createRequest();
      new AlertDialog.Builder(this)
      .setMessage(getString(R.string.create_ride_succes))
      .show();
    } catch (Exception e) {
      
      e.printStackTrace();
    }
	  
	  return false;
	}
	
	public JSONObject createOutput(){
	  
    JSONObject JSONoutput = new JSONObject();
    
    String departureAdress = ((EditText) findViewById(R.id.editText1)).getText().toString();
    String arrivalAdress = ((EditText) findViewById(R.id.editText2)).getText().toString();
    
    String departureDateString = ((EditText) findViewById(R.id.editText3)).getText().toString();
    String departureTimeString = ((EditText) findViewById(R.id.editText4)).getText().toString();
    
    
    
    GeoLocation departureLocation = new GeoLocation(departureAdress);
    GeoLocation arrivalLocation = new GeoLocation(arrivalAdress);
    
    try {
      JSONoutput.put("rideId", -1);
      JSONoutput.put("ridestartPtLat", (float) departureLocation.getLatitude());
      JSONoutput.put("ridestartPtLon", (float) departureLocation.getLongitude());
      JSONoutput.put("rideendPtLat", (float) arrivalLocation.getLatitude());
      JSONoutput.put("rideendPtLon", (float) arrivalLocation.getLongitude());
      JSONoutput.put("ridestartTime", convertDateTimeToSeconds(departureDateString,departureTimeString));
      JSONoutput.put("rideprice", 0.1);
      JSONoutput.put("rideComment", "Ride comment!");
      JSONoutput.put("acceptableDetourInMin", 5);
      JSONoutput.put("acceptableDetourInKm", 5);
      JSONoutput.put("acceptableDetourInPercent", 5);
      JSONoutput.put("offeredSeatsNo", 10);
      JSONoutput.put("startptAddress", departureAdress);
      JSONoutput.put("endptAddress", arrivalAdress);       
    } catch (JSONException e){
      e.printStackTrace();
    }
    
    
    return JSONoutput;
  }
	 
  @SuppressLint("SimpleDateFormat")
  public int convertDateTimeToSeconds(String date, String time){
    int output=0;
    try {
      output = (int) new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm").parse(date+" "+time).getTime() / 1000;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    //Log.d("test","output date (timezone problem?):"+output);
    return output;
  }
}
