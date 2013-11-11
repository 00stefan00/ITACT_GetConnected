package com.app.getconnected.security;

import android.util.Base64;

/**
 * @author getConnected 2
 */

public class Cryptor {

	/**
	 * Encodes the cleartext
	 * 
	 * @param cleartext
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String cleartext) throws Exception {
		byte[] data = null;
		try {
			data = cleartext.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Base64.encodeToString(data, Base64.DEFAULT);
	}

	/**
	 * Decodes the encrypted text
	 * 
	 * @param encrypted
	 * @return
	 * @throws Exception
	 */
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
