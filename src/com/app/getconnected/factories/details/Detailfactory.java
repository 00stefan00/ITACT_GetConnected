package com.app.getconnected.factories.details;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.getconnected.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Detailfactory {

	private static final int MODE_WALK = "WALK".hashCode();
	private static final int MODE_BUS = "BUS".hashCode();
	private static final int MODE_TRAIN = "TRIAN".hashCode();

	public static View getView(JSONObject leg, Context context) throws JSONException {
		if (getMode(leg) == MODE_WALK)
			return (new WalkDetailFactory(inflateView(R.layout.transport_detail_view,context), context, leg)).getView();
		if (getMode(leg) == MODE_BUS)
			return (new BusDetailFactory(inflateView(R.layout.transport_detail_view,context), context, leg)).getView();
		if (getMode(leg) == MODE_TRAIN)
			return (new TrainDetailFactory(inflateView(R.layout.transport_detail_view,context), context, leg)).getView();
		return null;

	}

	private static int getMode(JSONObject leg) throws JSONException {
		String mode = leg.getString("mode");
		return mode.hashCode();
	}
	
	private static View inflateView(int resource,Context context){
		ViewGroup wrapper = (ViewGroup)((Activity)context).findViewById(R.id.transport_details_wrapper);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(resource, (ViewGroup) wrapper,false);
		return view;
	}

}
