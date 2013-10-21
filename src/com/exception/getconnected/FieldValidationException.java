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

	public FieldValidationException(int i) {
		this.index = i;
	}

	public String getMessage() {
		return "invalid input";
	}
	
	public int getIndex() {
		return index;
	}
}
