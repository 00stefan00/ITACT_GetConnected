package com.app.getconnected.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.app.getconnected.R;
import com.app.getconnected.gps.GPSLocator;
import com.app.getconnected.gps.Location;
import com.app.getconnected.network.GeoLocation;
import com.app.getconnected.rides.SaveRides;
import com.app.getconnected.sqllite.MarketplaceDatabaseHandler;
import com.exception.getconnected.FieldValidationException;
import com.util.getconnected.FieldValidator;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//import android.util.Log;

public class CreateRideActivity extends BaseActivity implements
		View.OnFocusChangeListener, OnClickListener {

	private final Calendar calendarTime = Calendar.getInstance();
	private final Calendar calendarDate = Calendar.getInstance();

	JSONObject output = null;
	private EditText inputFrom;
	private EditText inputTo;

	private EditText inputTime;
	private EditText inputDate;

	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm",
			Locale.getDefault());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",
			Locale.getDefault());

	private Date time;
	private Date date;

  	MarketplaceDatabaseHandler helper = new MarketplaceDatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createride);
		initLayout(R.string.title_activity_createride, true, true, true, true);

		inputFrom = (EditText) findViewById(R.id.editText1);
		inputFrom.setOnFocusChangeListener(this);
		inputFrom.setOnClickListener(this);
		inputTo = (EditText) findViewById(R.id.editText2);
		inputTo.setOnFocusChangeListener(this);
		inputTo.setOnClickListener(this);

		inputTime = (EditText) findViewById(R.id.editText3);
		inputTime.setText(timeFormat.format(new Date()));

		inputDate = (EditText) findViewById(R.id.editText4);
		inputDate.setText(dateFormat.format(new Date()));


		buttonOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(validateFields())
				{
					output = createOutput();
					finish();
				}
			}
		});

		setTimePicker();
		setDatePicker();
	}

	public void doTheValidation(EditText[] fieldsToValidate) {
		Boolean validInput = true;
		for (EditText textField : fieldsToValidate) {
			textField.setError(null);
			try {
				FieldValidator.validateTextField(textField);
			} catch (FieldValidationException e) {
				textField.setError(getString(e.getIndex()));
				validInput = false;
			}
		}
		if (validInput) {
			if (attemptToCreateRide(output.toString())) {
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
		  	//Create timestamp and insert into sqlite
	      	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	      	helper.executeQuery("INSERT INTO rides VALUES ('" + timestamp + "','" + input + "')");
	      
	      	saveRides.createRequest();
	      
	      	new AlertDialog.Builder(this)
	      	.setMessage(getString(R.string.create_ride_succes))
	      	.show();
    	} catch (Exception e) {
     		e.printStackTrace();
    	}
	  	return false;
	}

	public JSONObject createOutput() {

		JSONObject JSONoutput = new JSONObject();

		String departureAdress = ((EditText) findViewById(R.id.editText1)).getText().toString();
		String arrivalAdress = ((EditText) findViewById(R.id.editText2)).getText().toString();

		String departureDateString = ((EditText) findViewById(R.id.editText3)).getText().toString();
		String departureTimeString = ((EditText) findViewById(R.id.editText4)).getText().toString();


		GeoLocation departureLocation = new GeoLocation(departureAdress);
		GeoLocation arrivalLocation = new GeoLocation(arrivalAdress);

		boolean error = false;

		try {
			JSONoutput.put("rideId", -1);
			JSONoutput.put("ridestartPtLat", (float) departureLocation.getLatitude());
			JSONoutput.put("ridestartPtLon", (float) departureLocation.getLongitude());
			JSONoutput.put("rideendPtLat", (float) arrivalLocation.getLatitude());
			JSONoutput.put("rideendPtLon", (float) arrivalLocation.getLongitude());
			JSONoutput.put("ridestartTime", convertDateTimeToSeconds(departureDateString, departureTimeString));
			JSONoutput.put("rideprice", 0.1);
			JSONoutput.put("rideComment", "Ride comment!");
			JSONoutput.put("acceptableDetourInMin", 5);
			JSONoutput.put("acceptableDetourInKm", 5);
			JSONoutput.put("acceptableDetourInPercent", 5);
			JSONoutput.put("offeredSeatsNo", 10);
			JSONoutput.put("startptAddress", departureAdress);
			JSONoutput.put("endptAddress", arrivalAdress);
		} catch (JSONException e) {
			error = true;
			e.printStackTrace();
		} finally {
			String whatToSay = (error) ? 	this.getResources().getString(R.string.create_ride_fail) :
											this.getResources().getString(R.string.create_ride_succes);
			Toast.makeText(
					this,
					whatToSay,
					Toast.LENGTH_SHORT).show();
		}


		return JSONoutput;
	}

	@SuppressLint("SimpleDateFormat")
	public int convertDateTimeToSeconds(String date, String time) {
		int output = 0;
		try {
			output = (int) new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm").parse(date + " " + time).getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}

	@Override
	public void onFocusChange(View view, boolean hasFocus) {
		if (hasFocus) {
			openLocationSelector(view);
		}
	}

	private void openLocationSelector(View v) {
		String type;

		if (v == (View) inputFrom) {
			type = getResources().getString(R.string.transport_text_from);
		} else {
			type = getResources().getString(R.string.transport_text_to);
		}

		Intent intent = new Intent(CreateRideActivity.this,
				LocationSelectorActivity.class);
		intent.putExtra("type", type);
		startActivityForResult(intent, 2);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case (2): {
				if (resultCode == Activity.RESULT_OK) {
					String location = data.getStringExtra("location");
					String type = data.getStringExtra("type");

					if (type.equals(getResources().getString(
							R.string.location_from))) {
						inputFrom.setText(location);
					} else {
						inputTo.setText(location);
					}
				}
				break;
			}
		}
	}

	public boolean validateLocation(String address, Location location) {
		if (address.equals("")) {
			Toast.makeText(
					this,
					this.getResources().getString(
							R.string.field_validation_no_input),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!location.isValidLocation()) {
			Toast.makeText(
					this,
					this.getResources().getString(
							R.string.field_validation_unknown_location),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View view) {
		openLocationSelector(view);
	}

	private void setTimePicker() {
		final TimePickerDialog.OnTimeSetListener timePicker = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

				calendarTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendarTime.set(Calendar.MINUTE, minute);
				time = calendarTime.getTime();

				inputTime.setText(timeFormat.format(time));
			}
		};
		inputTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(CreateRideActivity.this, timePicker,
						calendarTime.get(Calendar.HOUR_OF_DAY), calendarTime
						.get(Calendar.MINUTE), true).show();
			}
		});
		inputTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					new TimePickerDialog(CreateRideActivity.this, timePicker,
							calendarTime.get(Calendar.HOUR_OF_DAY),
							calendarTime.get(Calendar.MINUTE), true).show();
				}
			}
		});

	}

	private void setDatePicker() {
		final DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
								  int dayOfMonth) {
				calendarDate.set(Calendar.YEAR, year);
				calendarDate.set(Calendar.MONTH, monthOfYear);
				calendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				date = calendarDate.getTime();
				inputDate.setText(dateFormat.format(date));
			}
		};
		inputDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new DatePickerDialog(CreateRideActivity.this, datePicker,
						calendarDate.get(Calendar.YEAR), calendarDate
						.get(Calendar.MONTH), calendarDate
						.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		inputDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					new DatePickerDialog(CreateRideActivity.this, datePicker,
							calendarDate.get(Calendar.YEAR), calendarDate
							.get(Calendar.MONTH), calendarDate
							.get(Calendar.DAY_OF_MONTH)).show();
				}
			}
		});
	}
	public Boolean validateFields()
	{
		Location fromLocation;
		Location toLocation;

		if (inputFrom.getText().toString().equals(inputTo.getText().toString())) {
			Toast.makeText(
					this,
					this.getResources().getString(
							R.string.field_validation_same_input),
					Toast.LENGTH_SHORT).show();

			return false;
		}

		if (inputFrom.getText().toString()
				.equals(getResources().getString(R.string.current_location))) {
			fromLocation = new GPSLocator(this);

			if (!fromLocation.isValidLocation()) {
				Toast.makeText(
						this,
						this.getResources().getString(
								R.string.gps_disabled),
						Toast.LENGTH_SHORT).show();

				return false;
			}
		} else {
			fromLocation = new GeoLocation(inputFrom.getText().toString());

			if (!validateLocation(inputFrom.getText().toString(), fromLocation)) {
				return false;
			}
		}

		if (inputTo.getText().toString()
				.equals(getResources().getString(R.string.current_location))) {
			toLocation = new GPSLocator(this);
			if (!toLocation.isValidLocation()) {
				Toast.makeText(
						this,
						this.getResources().getString(
								R.string.gps_disabled),
						Toast.LENGTH_SHORT).show();

				return false;
			}
		} else {
			toLocation = new GeoLocation(inputTo.getText().toString());

			if (!validateLocation(inputTo.getText().toString(), toLocation)) {
				return false;
			}
		}
		return true;
	}
}

