package com.exception.getconnected;

import android.app.Activity;
import android.widget.Toast;

import com.app.getconnected.config.Config;

public class UncaughtExceptionHandler
{
	protected UncaughtExceptionHandler(){ }
	
	/**
	 * Catches all exception. Ensuring the application doesn't crash
	 * @return uncaughtExceptionHandler
	 */
	public static java.lang.Thread.UncaughtExceptionHandler getUncaughtExceptionHandler(final Activity activity, final String message)
	{
		return new Thread.UncaughtExceptionHandler()
		{
			public void uncaughtException(Thread t, Throwable e)
			{
				if (Config.DEBUG)
				{
					System.err.println("---------------------------------------------");
					System.err.println("");
					
					System.err.println("Exception in " + t + ":");
					e.printStackTrace();
					
					System.err.println("");
					System.err.println("---------------------------------------------");
				}
				
				Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			}
		};
	}
}
