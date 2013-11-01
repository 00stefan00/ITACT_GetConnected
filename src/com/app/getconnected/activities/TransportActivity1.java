package com.app.getconnected.activities;
                        
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.app.getconnected.R;

public class TransportActivity1 extends BaseActivity {

	private CheckBox checkBoxBus;
	private CheckBox checkBoxTrain;
	private CheckBox checkBoxTaxiOther;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport1);
		initLayout(R.string.title_activity_transport, true, true, true, true);
		
		checkBoxBus = (CheckBox) findViewById(R.id.transport_checkbox_bus);
		checkBoxTrain = (CheckBox) findViewById(R.id.transport_checkbox_train);
		checkBoxTaxiOther = (CheckBox) findViewById(R.id.transport_checkbox_taxi_other);

		buttonOk.setText(getResources().getString(R.string.transportation_result_next));
		buttonOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextPage();
			}
		});

	}

	protected void nextPage() {

		String mode = getTransportMode(checkBoxBus.isChecked(),
				checkBoxTrain.isChecked(), checkBoxTaxiOther.isChecked());
		
		Intent intent = new Intent(TransportActivity1.this, TransportActivity2.class);
		intent.putExtra("mode", mode);
		startActivityForResult(intent, 1);

	}

	private String getTransportMode(boolean bus, boolean train,
			boolean taxiOther) {
		String mode;

		if (!bus && !train && taxiOther) {
			mode = "WALK";
		} else {
			mode = "TRANSIT,WALK";
		}

		return mode;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport, menu);
		return true;
	}

}
