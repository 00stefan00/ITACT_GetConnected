package com.app.getconnected.factories.details;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.app.getconnected.R;

/**
 * @author getConnected 1
 */
public class BusDetailGenerator extends BaseDetailGenerator {

	protected TextView busLine;
	protected TextView headSign;

	/**
	 * Constructor
	 * @param view
	 * @param context
	 * @param data
	 * @throws JSONException
	 */
	public BusDetailGenerator(View view, Context context, JSONObject data)
			throws JSONException {
		super(view,context,data);
		initViews();

	}

	@Override
	public View getView() {
		return this.view;
	}

	/**
	 * Initializes the views
	 * @throws JSONException
	 */
	private void initViews() throws JSONException{
		setBusLine();
		setHeadSign();
	}

	/**
	 * Sets the bus lines
	 * @throws JSONException
	 */
	private void setBusLine() throws JSONException{
		busLine = (TextView) view.findViewById(R.id.transport_details_bus_line);
		busLine.setText(data.getString("route"));
	}

	/**
	 * Set the head signs
	 * @throws JSONException
	 */
	private void setHeadSign() throws JSONException{
		headSign = (TextView) view.findViewById(R.id.transport_details_head_sign);
		headSign.setText(data.getString("headsign"));
	}
}
