package com.app.getconnected.security;

import android.content.Context;
import android.util.Log;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: johan
 * Date: 11/1/13
 * Time: 11:47 AM
 */
public class Login extends Api {

	private Context context;

	private String username;

	private String password;

	public static final String fileName = "credentials.csf";

	public static final String LOGIN_SEED = "loginCredentials";

	public Login(Context context, String username, String password) {
		this.context = context;
		this.username = username;
		this.password = password;
		fillMap();
	}

	public void saveCredentials() throws Exception {
		File file = new File(context.getFilesDir(), fileName);
		new FileOutputStream(file, false).close();
		String write = Cryptor.encrypt(username + ";" + password);
		Log.d("write", write);
		FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
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
