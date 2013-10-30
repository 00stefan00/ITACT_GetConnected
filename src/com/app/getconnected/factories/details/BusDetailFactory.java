package com.app.getconnected.factories.details;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.getconnected.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BusDetailFactory extends BaseDetailFactory implements
		IDetailFactory {

	public BusDetailFactory(View view, Context context, JSONObject data)
			throws JSONException {
		super(view,context,data);
	}

	@Override
	public View getView() {
		return this.view;
	}

}
