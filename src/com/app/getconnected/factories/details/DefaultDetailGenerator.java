package com.app.getconnected.factories.details;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;

/**
 * @author getConnected 1
 */
public class DefaultDetailGenerator extends BaseDetailGenerator{

	/**
	 * Constructor
	 * @param view
	 * @param context
	 * @param data
	 * @throws JSONException
	 */
	public DefaultDetailGenerator(View view, Context context, JSONObject data)
			throws JSONException {
		super(view, context, data);
	}

	@Override
	public View getView() {
		return this.view;
	}
	
}
