package com.app.getconnected.factories.details;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.getconnected.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BusDetailGenerator extends BaseDetailGenerator {

	protected TextView busLine;
	protected TextView headSign;
	
	public BusDetailGenerator(View view, Context context, JSONObject data)
			throws JSONException {
		super(view,context,data);
		initViews();

	}

	@Override
	public View getView() {
		return this.view;
	}

	
	private void initViews() throws JSONException{
		setBusLine();
		setHeadSign();
	}
	
	private void setBusLine() throws JSONException{
		busLine = (TextView) view.findViewById(R.id.transport_details_bus_line);
		busLine.setText(data.getString("route"));
	}
	
	private void setHeadSign() throws JSONException{
		headSign = (TextView) view.findViewById(R.id.transport_details_head_sign);
		headSign.setText(data.getString("headsign"));
	}
}
