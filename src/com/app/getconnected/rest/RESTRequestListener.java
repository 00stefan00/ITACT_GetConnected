package com.app.getconnected.rest;

import com.app.getconnected.rest.RESTRequestEvent;

public interface RESTRequestListener
{
	void RESTRequestOnPreExecute(RESTRequestEvent event);
	
	void RESTRequestOnProgressUpdate(RESTRequestEvent event);
	
	void RESTRequestOnPostExecute(RESTRequestEvent event);
}
