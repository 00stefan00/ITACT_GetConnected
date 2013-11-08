package com.app.getconnected.factories.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.app.getconnected.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseDetailGenerator {

	protected View view;
	protected JSONObject data;
	private Context context;

	// The standard TextViews
	protected TextView title;
	protected TextView departureLocation;
	protected TextView departureTime;
	protected TextView arivalLocation;
	protected TextView arivalTime;

	// The tags for the JSON objects
	private final String TITLE_TAG = "mode";
	private final String DEPARTURE_TAG = "from";
	private final String DEPARTURE_LOCATION_TAG = "name";
	private final String DEPARTURE_TIME_TAG = "startTime";
	private final String ARIVAL_TAG = "to";
	private final String ARIVAL_LOCATION_TAG = "name";
	private final String ARIVAL_TIME_TAG = "endTime";

	/**
	 * @param view
	 * @param context
	 * @param data
	 * @throws JSONException
	 */
	public BaseDetailGenerator(View view, Context context, JSONObject data)
			throws JSONException {
		this.view = view;
		this.context = context;
		this.data = data;
		title = (TextView) view.findViewById(R.id.transport_details_title);
		departureLocation = (TextView) view
				.findViewById(R.id.transport_details_text_departure_location);
		departureTime = (TextView) view
				.findViewById(R.id.transport_details_text_departure_time);
		arivalLocation = (TextView) view
				.findViewById(R.id.transport_details_text_arival_location);
		arivalTime = (TextView) view
				.findViewById(R.id.transport_details_text_arival_time);
		this.setTextViews();
	}

	/**
	 * Gets the view
	 * 
	 * @return
	 */
	abstract public View getView();

	/**
	 * @throws JSONException
	 */
	private void setTitle() throws JSONException {
		String titleStr = data.getString(TITLE_TAG);
		title.setText(this.translateTitle(titleStr));
	}

	/**
	 * @param title
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	private String translateTitle(String title) {
		title = "transport_details_" + title.toLowerCase() + "_title";
		String packageName = context.getPackageName();
		int resId = context.getResources().getIdentifier(title, "string",
				packageName);
		return context.getString(resId);
	}

	/**
	 * @throws JSONException
	 */
	private void setDepartureLocation() throws JSONException {
		String departureLocationStr = data.getJSONObject(DEPARTURE_TAG)
				.getString(DEPARTURE_LOCATION_TAG);
		this.departureLocation.setText(departureLocationStr);
	}

	/**
	 * @throws JSONException
	 */
	private void setDepartureTime() throws JSONException {
		long departureTimeStr = data.getLong(DEPARTURE_TIME_TAG);
		this.departureTime.setText(this.getDate(departureTimeStr, "HH:mm"));
	}

	/**
	 * @throws JSONException
	 */
	private void setArivalLocation() throws JSONException {
		String arivalLocationStr = data.getJSONObject(ARIVAL_TAG).getString(
				ARIVAL_LOCATION_TAG);
		this.arivalLocation.setText(arivalLocationStr);
	}

	/**
	 * @throws JSONException
	 */
	private void setArivalTime() throws JSONException {
		long arivalTimeStr = data.getLong(ARIVAL_TIME_TAG);
		this.arivalTime.setText(this.getDate(arivalTimeStr, "HH:mm"));
	}

	/**
	 * @throws JSONException
	 */
	private void setTextViews() throws JSONException {
		this.setTitle();
		this.setDepartureLocation();
		this.setDepartureTime();
		this.setArivalLocation();
		this.setArivalTime();
	}

	/**
	 * @param time
	 * @param format
	 * @return
	 */
	protected String getDate(Long time, String format) { // todo to the current
															// // device format.
		Date date = new Date(time);
		SimpleDateFormat SDF = new SimpleDateFormat(format);
		return SDF.format(date);
	}
}