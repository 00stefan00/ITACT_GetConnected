package com.util.getconnected;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class ActivityHelper {

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		// Use the current date as the default date in the picker
		final static Calendar c = Calendar.getInstance();
		
		static int year = c.get(Calendar.YEAR);
		static int month = c.get(Calendar.MONTH);
		static int day = c.get(Calendar.DAY_OF_MONTH);


		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			DatePickerFragment.year = year;
			DatePickerFragment.month = month;
			DatePickerFragment.day = day;
		}
	}
}