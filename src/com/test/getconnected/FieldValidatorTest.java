package com.test.getconnected;

import android.test.AndroidTestCase;
import com.exception.getconnected.FieldValidationException;
import com.util.getconnected.FieldValidator;

/**
 * Created with IntelliJ IDEA.
 * User: johan_000
 * Date: 10/17/13
 * Time: 11:21 PM
 */
public class FieldValidatorTest extends AndroidTestCase {

	private static final String INPUT = "password";

	public void testValidateTextField() throws Exception {
		boolean error = false;
		try {
			FieldValidator.validateTextField(INPUT, false);
		} catch (FieldValidationException e) {
			error = true;
		} finally {
			assertFalse(error);
		}
	}

	public void testValidateTextFieldHardCheck() throws Exception {
		boolean error = false;
		try {
			FieldValidator.validateTextField(INPUT, true);
		} catch (FieldValidationException e) {
			error = true;
		} finally {
			assertFalse(error);
		}
	}

	public void testValidateNumberField() throws Exception {
		boolean error = false;
		try {
			FieldValidator.validateNumberField(INPUT);
		} catch (FieldValidationException e) {
			error = true;
		} finally {
			assertTrue(error);
		}
	}

	public void testValidateStringField() throws Exception {
		boolean error = false;
		try {
			FieldValidator.validateStringField(INPUT);
		} catch (FieldValidationException e) {
			error = true;
		} finally {
			assertFalse(error);
		}
	}

	public void testIsEmpty() throws Exception {
		assertFalse(FieldValidator.isEmpty(INPUT));
	}
}
