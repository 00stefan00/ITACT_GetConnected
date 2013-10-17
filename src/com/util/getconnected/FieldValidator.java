package com.util.getconnected;

import com.app.getconnected.R;
import com.exception.getconnected.FieldValidationException;

/**
 * Created with IntelliJ IDEA.
 * User: EnquiringStone
 * Date: 17-10-13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class FieldValidator {

	private static final int MIN_CHARACTER_COUNT = 3;
	private static final int MAX_CHARACTER_COUNT = 12;


	/**
	 * @param text
	 * @param hardCheck
	 * @throws FieldValidationException
	 */
	public static void validateTextField(String text, Boolean hardCheck) throws FieldValidationException {
		if(isEmpty(text)) throw new FieldValidationException(R.string.validation_no_input);
		if(hardCheck) {
			if(text.length() < MIN_CHARACTER_COUNT) throw new FieldValidationException(R.string.field_validation_too_short);
			if(text.length() > MAX_CHARACTER_COUNT) throw new FieldValidationException(R.string.field_validation_too_long);
		}
	}

	/**
	 * @param text
	 * @throws FieldValidationException
	 */
	public static void validateNumberField(String text) throws FieldValidationException{
		for (int i=0; i< text.length(); i++) {
			if (!Character.isDigit(text.charAt(i))) throw new FieldValidationException(R.string.field_validation_number);
		}
	}

	/**
	 * @param text
	 * @throws FieldValidationException
	 */
	public static void validateStringField(String text) throws FieldValidationException {
		for (int i=0; i< text.length(); i++) {
			if (Character.isDigit(text.charAt(i))) throw new FieldValidationException(R.string.field_validation_string);
		}
	}

	/**
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text) {
		return text == null || text.equals("");
	}

}
