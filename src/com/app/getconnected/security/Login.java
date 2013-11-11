package com.app.getconnected.security;

import android.content.Context;
import android.util.Log;
import com.app.getconnected.config.Config;

import java.io.*;

/**
 * @author getConnected 2
 */

public class Login extends Api {

	private Context context;

	private String username;

	private String password;

	public static final String fileName = Config.LOGIN_INTERNAL_FILE_NAME;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param username
	 * @param password
	 */
	public Login(Context context, String username, String password) {
		this.context = context;
		this.username = username;
		this.password = password;
		fillMap();
	}

	/**
	 * Saves the credentials to a file within the mobile environment
	 * 
	 * @throws Exception
	 */
	public void saveCredentials() throws Exception {
		File file = new File(context.getFilesDir(), fileName);
		new FileOutputStream(file, false).close();
		String write = Cryptor.encrypt(username + ";" + password);
		Log.d("write", write);
		FileOutputStream outputStream = context.openFileOutput(fileName,
				Context.MODE_PRIVATE);
		outputStream.write(write.getBytes());
		outputStream.close();
	}

	@Override
	public void fillMap() {
		emptyMap();
		addToMap("username", username);
		addToMap("password", password);
	}
}
