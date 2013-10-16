package com.example.getconnected.activities;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.getconnected.R;
import com.example.getconnected.R.id;
import com.example.getconnected.R.layout;
import com.example.getconnected.R.menu;
import com.example.getconnected.R.string;
import com.example.getconnected.network.PlacesAutoCompleteAdapter;

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
import android.widget.TimePicker;
import android.widget.Toast;

public class TransportActivity extends BaseActivity implements OnItemClickListener {

	private final Calendar calendarDeparture = Calendar.getInstance();
	private final Calendar calendarArrival = Calendar.getInstance();
	private final Calendar calendarDate = Calendar.getInstance();
	
	private Date timeDeparture;
	private Date timeArrival;
	private Date date;
	
	private EditText inputDeparture;
	private EditText inputArrival;
	private EditText inputDate;
	
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport);
	    initLayout(R.string.title_activity_transport, true, true, true, true);
	    
	    AutoCompleteTextView autoCompViewFrom = (AutoCompleteTextView) findViewById(R.id.transport_input_from);
	    AutoCompleteTextView autoCompViewTo = (AutoCompleteTextView) findViewById(R.id.transport_input_to);
	    
	    autoCompViewFrom.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.places_list_item));
	    autoCompViewFrom.setOnItemClickListener(this);
	    
	    autoCompViewTo.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.places_list_item));
	    autoCompViewTo.setOnItemClickListener(this);
	    
	    inputDeparture = (EditText) findViewById(R.id.home_button_transport);
	    inputDeparture.setText(timeFormat.format(new Date()));
	    inputArrival = (EditText) findViewById(R.id.home_button_map);
	    inputArrival.setText(timeFormat.format(new Date()));
	    inputDate = (EditText) findViewById(R.id.transport_button_date);
	    inputDate.setText(dateFormat.format(new Date()));
	    
		buttonOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TransportActivity.this, TransportResultActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		
		setTimePickers();
		setDatePicker();
		
	}
	
	private void setTimePickers() {
		final TimePickerDialog.OnTimeSetListener timeDeparturePicker = new TimePickerDialog.OnTimeSetListener() {

		    @Override
		    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

		        calendarDeparture.set(Calendar.HOUR_OF_DAY, hourOfDay);
		        calendarDeparture.set(Calendar.MINUTE, minute);
		        timeDeparture = calendarDeparture.getTime();
		        
		        inputDeparture.setText(timeFormat.format(timeDeparture));
		    }
		    
		};
	    inputDeparture.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            new TimePickerDialog(TransportActivity.this, timeDeparturePicker, calendarDeparture
	                    .get(Calendar.HOUR_OF_DAY), calendarDeparture.get(Calendar.MINUTE), true).show();
	        }

	    });	
	    
	    final TimePickerDialog.OnTimeSetListener timeArrivalPicker = new TimePickerDialog.OnTimeSetListener() {

		    @Override
		    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

		        calendarArrival.set(Calendar.HOUR_OF_DAY, hourOfDay);
		        calendarArrival.set(Calendar.MINUTE, minute);
		        timeArrival = calendarArrival.getTime();
		        
		        inputArrival.setText(timeFormat.format(timeArrival));
		    }
		    
		};
		inputArrival.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            new TimePickerDialog(TransportActivity.this, timeArrivalPicker, calendarArrival
	                    .get(Calendar.HOUR_OF_DAY), calendarArrival.get(Calendar.MINUTE), true).show();
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
}
