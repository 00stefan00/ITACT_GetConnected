package com.app.getconnected.security;

import android.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: 	johan
 * Date: 	11/1/13
 * Time: 	1:37 PM
 */
public class Cryptor {
	public static String encrypt(String cleartext) throws Exception {
		byte[] data = null;
		try {
			data = cleartext.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Base64.encodeToString(data, Base64.DEFAULT);
	}

	public static String decrypt(String encrypted) throws Exception {
		byte[] data = Base64.decode(encrypted, Base64.DEFAULT);
		String text = null;
		try {
			text = new String(data, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

}
