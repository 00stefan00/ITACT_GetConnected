package com.app.getconnected.rides;

/**
 * Created with IntelliJ IDEA.
 * User: johan
 * Date: 10/30/13
 * Time: 12:26 PM
 */
public interface Argumentable {
	/**
	 * Sets the key which is needed for request
	 * @param key
	 */
	public void setKey(String key);

	/**
	 * Sets the argument which is needed for request
	 * @param argument
	 */
	public void setArgument(String argument);

	/**
	 * Checks whether essential data is not empty
	 * @return
	 */
	public boolean isEmpty();
}
