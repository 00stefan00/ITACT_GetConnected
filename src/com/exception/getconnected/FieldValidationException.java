package com.exception.getconnected;

/**
 * Created with IntelliJ IDEA.
 * User: EnquiringStone
 * Date: 17-10-13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class FieldValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	private int index;

	/**
	 * Constructor
	 * @param i
	 */
	public FieldValidationException(int i) {
		this.index = i;
	}

	@Override
	public String getMessage() {
		return "invalid input";
	}

	/**
	 * Gets the index
	 * @return
	 */
	public int getIndex() {
		return index;
	}
}
