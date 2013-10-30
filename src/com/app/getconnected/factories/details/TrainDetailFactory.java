package com.app.getconnected.factories.details;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;

public class TrainDetailFactory extends BaseDetailFactory implements IDetailFactory{

	public TrainDetailFactory(View view, Context context, JSONObject data)
			throws JSONException {
		super(view, context, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView() {
		return this.view;
	}

}
