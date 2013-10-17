package com.example.getconnected.activities;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.getconnected.R;
import com.example.getconnected.network.GeoLocation;
import com.example.getconnected.network.PlacesAutoCompleteAdapter;
import com.example.getconnected.rest.RESTRequest;
import com.example.getconnected.rest.RESTRequestEvent;
import com.example.getconnected.rest.RESTRequestListener;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

public class TransportActivity extends BaseActivity implements OnItemClickListener, RESTRequestListener {

	private final Calendar calendarTime = Calendar.getInstance();
	private final Calendar calendarDate = Calendar.getInstance();
	
	private Date time;
	private Date date;
	
	private EditText inputTime;
	private EditText inputDate;
	
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
	private AutoCompleteTextView autoCompViewFrom;
	private AutoCompleteTextView autoCompViewTo;
	
	private RadioGroup radioGroup;
	private RadioButton radioArrival;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport);
	    initLayout(R.string.title_activity_transport, true, true, true, true);
	    
	    autoCompViewFrom = (AutoCompleteTextView) findViewById(R.id.transport_input_from);
	    autoCompViewTo = (AutoCompleteTextView) findViewById(R.id.transport_input_to);
	    
	    autoCompViewFrom.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.places_list_item));
	    autoCompViewFrom.setOnItemClickListener(this);
	    
	    autoCompViewTo.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.places_list_item));
	    autoCompViewTo.setOnItemClickListener(this);
	    
	    inputTime = (EditText) findViewById(R.id.transport_input_time);
	    inputTime.setText(timeFormat.format(new Date()));
	    inputDate = (EditText) findViewById(R.id.transport_input_date);
	    inputDate.setText(dateFormat.format(new Date()));
	    
	    radioGroup = (RadioGroup) findViewById(R.id.transport_radio_departure_arrival);
	    radioArrival = (RadioButton) findViewById(R.id.transport_radio_arrival);
	    
		buttonOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				plan();
			}
		});
		
		setTimePicker();
		setDatePicker();
		
	}
	
	protected void plan() {
		
		//String geocodingURL = "https://maps.googleapis.com/maps/api/geocode/json?address=Southeast+Water+Avenue,+Portland,+OR,+United+States&sensor=true";
		//System.out.println(geocodingURL);
		//RESTRequest request = new RESTRequest("http://maps5.trimet.org/opentripplanner-api-webapp/ws/plan?_dc=1382005620562&arriveBy=false&time=" + time + "&ui_date=" + date + "&mode=TRANSIT%2CWALK&optimize=QUICK&maxWalkDistance=840&walkSpeed=1.341&date=2013-10-17&toPlace=" + fromLatitude + "," + fromLongitude + "&fromPlace=" + toLatitude + "," + toLongitude);
		
		GeoLocation fromLocation = new GeoLocation("Southeast+Water+Avenue,+Portland,+OR,+United+States");
		double fromLatitude = fromLocation.getLatitude();
		double fromLongitude = fromLocation.getLongitude();
		
		GeoLocation toLocation = new GeoLocation("Southeast+Water+Avenue,+Portland,+OR,+United+States");
		double toLatitude = toLocation.getLatitude();
		double toLongitude = toLocation.getLongitude();		
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String time = timeFormat.format(calendarTime.getTime());
		String date = dateFormat.format(calendarDate.getTime());
		
		boolean arriveBy = radioGroup.getCheckedRadioButtonId() == radioArrival.getId() ? true : false;
		
		//RESTRequest request = new RESTRequest("http://maps5.trimet.org/opentripplanner-api-webapp/ws/plan?_dc=1382005620562&arriveBy=false&time=" + time + "&ui_date=" + date + "&mode=TRANSIT%2CWALK&optimize=QUICK&maxWalkDistance=840&walkSpeed=1.341&date=2013-10-17&toPlace=" + fromLatitude + "," + fromLongitude + "&fromPlace=" + toLatitude + "," + toLongitude);
		System.out.println("http://maps5.trimet.org/opentripplanner-api-webapp/ws/plan?_dc=1382005620562&arriveBy=" + arriveBy + "&time=" + time + "&ui_date=" + date + "&mode=TRANSIT%2CWALK&optimize=QUICK&maxWalkDistance=840&walkSpeed=1.341&date=2013-10-17&toPlace=" + fromLatitude + "," + fromLongitude + "&fromPlace=" + toLatitude + "," + toLongitude);
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
	            new TimePickerDialog(TransportActivity.this, timePicker, calendarTime
	                    .get(Calendar.HOUR_OF_DAY), calendarTime.get(Calendar.MINUTE), true).show();
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
	            new DatePickerDialog(TransportActivity.this, datePicker, calendarDate
	                    .get(Calendar.YEAR), calendarDate.get(Calendar.MONTH),
	                    calendarDate.get(Calendar.DAY_OF_MONTH)).show();
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
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

	@Override
	public void RESTRequestOnPreExecute(RESTRequestEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RESTRequestOnProgressUpdate(RESTRequestEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RESTRequestOnPostExecute(RESTRequestEvent event) {
		// TODO Auto-generated method stub
		
	}
}
