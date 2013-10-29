package com.test.getconnected.rest;

import android.test.AndroidTestCase;

import com.app.getconnected.rest.RESTRequestEvent;
import com.app.getconnected.rest.RESTRequestListener;
import com.app.getconnected.rest.RESTRequest.HeaderAcceptedData;
import com.app.getconnected.rest.RESTRequest.Method;
import com.util.getconnected.JSONParser;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */
public class RESTRequestTest extends AndroidTestCase
{	
	/**
	 * @return address
	 */
	public void testGetAddress()
	{
		//return address;
	}

	/**
	 * @param address
	 */
	public void testSetAddress(String address)
	{
		//this.address = address;
	}
	
	/**
	 * @return method
	 */
	public void testGetMethod()
	{
		//return method;
	}
	
	/**
	 * @param method
	 */
	public void testSetMethod(Method method)
	{
		//this.method = method;
	}
	
	/**
	 * @return headerAcceptedData
	 */
	public void testGetHeaderAcceptedData()
	{
		//return headerAcceptedData;
	}
	
	/**
	 * @param headerAcceptedData
	 */
	public void testSetHeaderAcceptedData(String headerAcceptedData)
	{
		//this.headerAcceptedData = headerAcceptedData;
	}
	
	/**
	 * @return ID
	 */
	public void testGetID()
	{
		//return ID;
	}

	/**
	 * @param address
	 */
	public void testSetID(String ID)
	{
		//this.ID = ID;
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void testPutString(String key, String value)
	{
		//parameters.add(new BasicNameValuePair(key, value));
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void testPutInt(String key, int value)
	{
		//parameters.add(new BasicNameValuePair(key, Integer.toString(value)));
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void testPutDouble(String key, double value)
	{
		//parameters.add(new BasicNameValuePair(key, Double.toString(value)));
	}

	/**
	 * @param key
	 * @param value
	 */
	public void testPutFloat(String key, float value)
	{
		//parameters.add(new BasicNameValuePair(key, Float.toString(value)));
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void testPutBoolean(String key, boolean value)
	{
		//parameters.add(new BasicNameValuePair(key, Boolean.toString(value)));
	}
	
	/**
	 * @param eventListener
	 */
	public void testAddEventListener(RESTRequestListener eventListener)
	{
		//this.eventListeners.add(eventListener);
	}
	
	protected void testDoInBackground(Void... voids)
	{
//		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
//
//		HttpUriRequest httpRequest = null; 
//		
//		// Get the correct request method
//		try
//		{
//			switch (method)
//			{
//				case GET:
//					// Set URL and encode parameters
//					httpRequest = new HttpGet(address + "?" + URLEncodedUtils.format(parameters, "utf-8"));
//					break;
//	
//				case POST:
//					httpRequest = new HttpPost(address);
//					
//					// Encode parameters as entity
//					((HttpPost) httpRequest).setEntity(new UrlEncodedFormEntity(parameters));
//					break;
//					
//				case PUT:
//					httpRequest = new HttpPut(address);
//					
//					// Encode parameters as entity
//					((HttpPut) httpRequest).setEntity(new UrlEncodedFormEntity(parameters));
//					break;
//					
//				default:
//					return "-1";
//			}
//		}
//		catch (IllegalArgumentException e)
//		{
//			e.printStackTrace();
//			return "-2";
//		}
//		catch (UnsupportedEncodingException e)
//		{
//			return "-3";
//		}
//		
//		// Indicate what data needs to be received
//		httpRequest.setHeader("Accept", headerAcceptedData);
//
//		InputStream inputStream = null;
//		
//		Scanner scanner = null;
//		
//		try
//		{
//			// Run request
//			HttpResponse httpResponse = defaultHttpClient.execute(httpRequest);
//			
//			// Get content of response
//			inputStream = httpResponse.getEntity().getContent();
//			
//			// Trick to read all data from a stream in one line: https://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
//			scanner = new Scanner(inputStream).useDelimiter("\\A");
//
//			String result = "";
//			
//			// Read lines into result
//			while (scanner.hasNext())
//			{
//				result += scanner.next();
//			}
//			
//			if (result.length() > 0)
//			{
//				return result;
//			}
//		}
//		catch (IOException e)
//		{
//			return "-4";
//		}
//		finally // Close opened utilities
//		{
//			if (scanner != null)
//			{
//				scanner.close();
//			}
//			
//			if (inputStream != null)
//			{
//				try
//				{
//					inputStream.close();
//				}
//				catch (IOException e) { }
//			}
//		}
//		
//		return "-5";
	}
	
	protected void testOnPreExecute()
	{
//		super.onPreExecute();
//		
//		for (RESTRequestListener eventListener : eventListeners)
//		{
//			// Create new RESTRequestEvent to be handled by the event listener
//			eventListener.RESTRequestOnPreExecute(new RESTRequestEvent(this, ID));
//		}
	}
	
	protected void testOnProgressUpdate(Void... voids)
	{
//		super.onProgressUpdate(voids);
//		
//		for (RESTRequestListener eventListener : eventListeners)
//		{
//			// Create new RESTRequestEvent to be handled by the event listener
//			eventListener.RESTRequestOnProgressUpdate(new RESTRequestEvent(this, ID));
//		}
	}
	
	protected void testOnPostExecute(String result)
	{
//		for (RESTRequestListener eventListener : eventListeners)
//		{
//			// Create new RESTRequestEvent to be handled by the event listener
//			eventListener.RESTRequestOnPostExecute(new RESTRequestEvent(this, result, ID));
//		}
	}
	
	public void testToString()
	{
		//return address + "?" + URLEncodedUtils.format(parameters, "utf-8");
	}
}
