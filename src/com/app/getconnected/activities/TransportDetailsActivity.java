package com.app.getconnected.activities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.getconnected.R;

import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TransportDetailsActivity extends BaseActivity {

	private int page = 0;
	private int pageSize = 3;
	private JSONArray legs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_details);
		initLayout(R.string.title_activity_transport_result, true, true, true, false); //todo doe eens string maken
		String json = getIntent().getExtras().getString("json");
		try {
			JSONObject jObject;
			jObject = new JSONObject(json);
			legs = jObject.getJSONArray("legs");
			initViews();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong =(", Toast.LENGTH_LONG)
					.show();
			return;
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport_details, menu);
		return true;
	}
	
	private void initViews() throws JSONException{
		RelativeLayout wrapper = (RelativeLayout)findViewById(R.id.transport_details_content_wrapper);
		for(int i = 0; i < legs.length() && i < 4; i++){
			RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.transport_details_view, wrapper, false);
			initView(legs.getJSONObject(i),view);
			System.out.println(((TextView)view.findViewById(R.id.transport_detail_title)).getText());
			wrapper.addView(view);
		}
	}
	
	private void initView(JSONObject leg,RelativeLayout view)throws JSONException{
		String mode = leg.getString("mode");
		String from = leg.getJSONObject("from").getString("name");
		String departureTime = leg.getString("startTime");
		String arivalTime = leg.getString("endTime");
		String to = leg.getJSONObject("to").getString("name");
		
		TextView tv = (TextView)findViewById(R.id.dingvoordemo);
		tv.append(mode +  "\n");
		tv.append(departureTime + " -> " + from + "\n");
		tv.append(arivalTime + " -> " + to + "\n\n");

		
		
		//((TextView) view.findViewById(R.id.transport_detail_title)).setText(mode);
		//((TextView) view.findViewById(R.id.transport_details_text_departure_time)).setText(departureTime);
		//((TextView) view.findViewById(R.id.transport_details_text_departure_location)).setText(from);
		//((TextView) view.findViewById(R.id.transport_details_text_arival_time)).setText(arivalTime);
		//((TextView) view.findViewById(R.id.transport_details_text_arival_location)).setText(to);
		
	}

}
