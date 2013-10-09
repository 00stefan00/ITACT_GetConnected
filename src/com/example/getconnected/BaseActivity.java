package com.example.getconnected;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

abstract class BaseActivity extends Activity {

	protected TextView txtHeading;
	protected Button buttonBack;
	protected Button buttonMenu;
	protected Button buttonOk;
	protected Button buttonHome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void initLayout(int resId, boolean homeButton, boolean backButton, boolean menuButton, boolean okButton) {
	     if(txtHeading == null)
	     txtHeading = (TextView) findViewById(R.id.header_text);
	     if(txtHeading != null)
	       txtHeading.setText(resId);
	     
	     buttonHome = (Button) findViewById(R.id.header_button_home);
		 buttonBack = (Button) findViewById(R.id.footer_button_back);
		 buttonMenu = (Button) findViewById(R.id.footer_button_menu);
		 buttonOk = (Button) findViewById(R.id.footer_button_ok);
		
		 buttonBack.setOnClickListener(new OnClickListener() {
			
			 @Override
			 public void onClick(View v) {
				 System.out.println("ok");
			 }
	   	 });
	     
	     this.buttonHome.setVisibility(homeButton ? View.VISIBLE : View.INVISIBLE); 
	     this.buttonBack.setVisibility(backButton ? View.VISIBLE : View.INVISIBLE); 
	     this.buttonMenu.setVisibility(menuButton ? View.VISIBLE : View.INVISIBLE); 
	     this.buttonOk.setVisibility(okButton ? View.VISIBLE : View.INVISIBLE); 
	}
	
	protected void disableBackButton() {
		
	}

}
