package com.app.getconnected.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.app.getconnected.R;
import com.app.getconnected.config.Config;
import com.app.getconnected.network.GeoLocation;
import com.app.getconnected.rest.RESTRequest;
import com.app.getconnected.rest.RESTRequestEvent;
import com.app.getconnected.rest.RESTRequestListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class TransportActivity extends BaseActivity implements
		OnFocusChangeListener, OnClickListener, RESTRequestListener {

	private final Calendar calendarTime = Calendar.getInstance();
	private final Calendar calendarDate = Calendar.getInstance();

	private Date time;
	private Date date;

	private EditText inputTime;
	private EditText inputDate;

	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm",
			Locale.getDefault());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",
			Locale.getDefault());
	private AutoCompleteTextView autoCompViewFrom;
	private AutoCompleteTextView autoCompViewTo;

	private EditText inputFrom;
	private EditText inputTo;

	private RadioGroup radioGroup;
	private RadioButton radioArrival;

	private CheckBox checkBoxBus;
	private CheckBox checkBoxTrain;
	private CheckBox checkBoxTaxiOther;

	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport);
		initLayout(R.string.title_activity_transport, true, true, true, true);

		inputFrom = (EditText) findViewById(R.id.transport_input_from);
		inputFrom.setOnFocusChangeListener(this);
		inputFrom.setOnClickListener(this);
		inputTo = (EditText) findViewById(R.id.transport_input_to);
		inputTo.setOnFocusChangeListener(this);
		inputTo.setOnClickListener(this);

		inputTime = (EditText) findViewById(R.id.transport_input_time);
		inputTime.setText(timeFormat.format(new Date()));
		inputDate = (EditText) findViewById(R.id.transport_input_date);
		inputDate.setText(dateFormat.format(new Date()));

		radioGroup = (RadioGroup) findViewById(R.id.transport_radio_departure_arrival);
		radioArrival = (RadioButton) findViewById(R.id.transport_radio_arrival);

		checkBoxBus = (CheckBox) findViewById(R.id.transport_checkbox_bus);
		checkBoxTrain = (CheckBox) findViewById(R.id.transport_checkbox_train);
		checkBoxTaxiOther = (CheckBox) findViewById(R.id.transport_checkbox_taxi_other);

		buttonOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				planTrip();
			}
		});

		setTimePicker();
		setDatePicker();

	}

	@SuppressLint("SimpleDateFormat")
	public void planTrip() {

		GeoLocation fromLocation = new GeoLocation(autoCompViewFrom.getText()
				.toString());
		GeoLocation toLocation = new GeoLocation(autoCompViewTo.getText()
				.toString());

		if (!validateLocation(autoCompViewFrom.getText().toString(),
				fromLocation)) {
			return;
		}

		if (!validateLocation(autoCompViewTo.getText().toString(), toLocation)) {
			return;
		}

		double fromLatitude = fromLocation.getLatitude();
		double fromLongitude = fromLocation.getLongitude();

		double toLatitude = toLocation.getLatitude();
		double toLongitude = toLocation.getLongitude();

		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String time = timeFormat.format(calendarTime.getTime());
		String date = dateFormat.format(calendarDate.getTime());

		boolean arriveBy = radioGroup.getCheckedRadioButtonId() == radioArrival
				.getId() ? true : false;

		String mode = getTransportMode(checkBoxBus.isChecked(),
				checkBoxTrain.isChecked(), checkBoxTaxiOther.isChecked());

		RESTRequest request = new RESTRequest(Config.tripPlannerAddress);
		request.addEventListener(this);
		request.putString("_dc", "1382083769026");
		request.putString("arriveBy", "" + arriveBy);
		request.putString("time", time);
		request.putString("ui_date", date);
		request.putString("date", date);
		request.putString("mode", mode);
		request.putString("optimize", "QUICK");
		request.putString("maxWalkDistance", "1609");
		request.putString("walkSpeed", "1.341");
		request.putString("toPlace", toLatitude + "," + toLongitude);
		request.putString("fromPlace", fromLatitude + "," + fromLongitude);
		request.execute();

	}

	private String getTransportMode(boolean bus, boolean train,
			boolean taxiOther) {
		String mode;

		if (!bus && !train && taxiOther) {
			mode = "WALK";
		} else {
			mode = "TRANSIT,WALK";
		}

		return mode;
	}

	public boolean validateLocation(String address, GeoLocation location) {
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
				new TimePickerDialog(TransportActivity.this, timePicker,
						calendarTime.get(Calendar.HOUR_OF_DAY), calendarTime
								.get(Calendar.MINUTE), true).show();
			}
		});
		inputTime.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					new TimePickerDialog(TransportActivity.this, timePicker,
							calendarTime.get(Calendar.HOUR_OF_DAY), calendarTime
									.get(Calendar.MINUTE), true).show();
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
				new DatePickerDialog(TransportActivity.this, datePicker,
						calendarDate.get(Calendar.YEAR), calendarDate
								.get(Calendar.MONTH), calendarDate
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		inputDate.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					new DatePickerDialog(TransportActivity.this, datePicker,
							calendarDate.get(Calendar.YEAR), calendarDate
									.get(Calendar.MONTH), calendarDate
									.get(Calendar.DAY_OF_MONTH)).show();
				}
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport, menu);
		return true;
	}

	@Override
	public void RESTRequestOnPreExecute(RESTRequestEvent event) {
		dialog = new ProgressDialog(this);
		dialog.setTitle(getResources().getString(R.string.loading));
		dialog.show();
	}

	@Override
	public void RESTRequestOnProgressUpdate(RESTRequestEvent event) {

	}

	@Override
	public void RESTRequestOnPostExecute(RESTRequestEvent event) {
		dialog.dismiss();

		Intent intent = new Intent(this, TransportResultActivity.class);
		intent.putExtra("json", event.getResult());
		startActivity(intent);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (2): {
			System.out.println("onActivityResult");
			if (resultCode == Activity.RESULT_OK) {
				String location = data.getStringExtra("location");
				String type = data.getStringExtra("type");

				if (type.equals(getResources().getString(
						R.string.transport_text_from))) {
					inputFrom.setText(location);
				} else {
					inputTo.setText(location);
				}
			}
			break;
		}
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			openLocationSelector(v);
		}
	}

	@Override
	public void onClick(View v) {
		openLocationSelector(v);
	}

	private void openLocationSelector(View v) {
		String type;

		if (v == (View) inputFrom) {
			type = getResources().getString(R.string.transport_text_from);
		} else {
			type = getResources().getString(R.string.transport_text_to);
		}
		System.out.println("openLocationSelector");
		Intent intent = new Intent(TransportActivity.this,
				LocationSelectorActivity.class);
		intent.putExtra("type", type);
		startActivityForResult(intent, 2);
	}

}
