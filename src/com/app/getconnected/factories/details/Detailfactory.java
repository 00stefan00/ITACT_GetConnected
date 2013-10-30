package com.app.getconnected.factories.details;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
	private static final int MODE_TRAIN = "TRAIN".hashCode();
	
	private static final String MODE_TAG = "mode";

	public static View getView(JSONObject leg, Context context) throws JSONException, ClassNotFoundException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		// Get the mode string in lower case, i.e. BUS -> bus
		String mode = leg.getString(MODE_TAG).toLowerCase();
		
		System.out.println(mode);
		
		// Get the view resource name, i.e. transport_detail_view_bus
		String viewName = "transport_detail_view_" + mode;
		// Get the current activity package name
	    String packageName = context.getPackageName();
	    // Get the resource id according to the resource name
	    int resId = context.getResources().getIdentifier(viewName, "layout", packageName);
	    // Get the mode with the first char to upper, i.e. Bus
		mode = Character.toUpperCase(mode.charAt(0)) + mode.substring(1);
		// Get the according generator class name
	    String className = "com.app.getconnected.factories.details." + mode + "DetailGenerator"; // get the current package name?
	    // Use reflection to get the corresponding class
		Class<?> detailGenerator = Class.forName(className);
		// get the constructor of the earlier created class
		Constructor<?> constructor = detailGenerator.getConstructor(View.class, Context.class,JSONObject.class);
		// inflate the view for our generator
		View view = inflateView(resId,context);
		// Instantiate the generator 
		Object detailGeneratorInstance = constructor.newInstance(view, context,leg);
		// return the view created by the generator
		return ((BaseDetailGenerator)detailGeneratorInstance).getView();
	    
		/*
		if (getMode(leg) == MODE_WALK)
			return (new WalkDetailGenerator(inflateView(R.layout.transport_detail_view_walk,context), context, leg)).getView();
		if (getMode(leg) == MODE_BUS)
			return (new BusDetailFactory(inflateView(R.layout.transport_detail_view_bus,context), context, leg)).getView();
		if (getMode(leg) == MODE_TRAIN)
			return (new TrainDetailGenerator(inflateView(R.layout.transport_detail_view_train,context), context, leg)).getView();
		return (new WalkDetailGenerator(inflateView(R.layout.transport_detail_view_walk,context), context, leg)).getView();// The default
		*/
	}

	private static int getMode(JSONObject leg) throws JSONException {
		String mode = leg.getString("mode");
		return mode.hashCode();
	}

	private static View inflateView(int resource, Context context) {
		ViewGroup wrapper = (ViewGroup) ((Activity) context)
				.findViewById(R.id.transport_details_wrapper);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(resource, (ViewGroup) wrapper, false);
		return view;
	}

}
