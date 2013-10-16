package com.example.getconnected.rest;

import com.example.getconnected.rest.RESTRequestEvent;

public interface RESTRequestListener
{
	void RESTRequestOnPreExecute(RESTRequestEvent event);
	
	void RESTRequestOnProgressUpdate(RESTRequestEvent event);
	
	void RESTRequestOnPostExecute(RESTRequestEvent event);
}
