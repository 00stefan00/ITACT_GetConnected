package com.example.getconnected;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TransportActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport);
	    initLayout(R.string.title_activity_transport, true, true, true, true);
	    
//		buttonOk = (Button) findViewById(R.id.footer_button_ok);
//		buttonOk.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(TransportActivity.this, TransportResultActivity.class);
//				startActivityForResult(intent, 1);
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transport, menu);
		return true;
	}

}
