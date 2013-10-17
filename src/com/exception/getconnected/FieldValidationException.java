package com.exception.getconnected;

import android.content.res.Resources;
import com.example.getconnected.R;

/**
 * Created with IntelliJ IDEA.
 * User: EnquiringStone
 * Date: 17-10-13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class FieldValidationException extends Exception {

	private int index;

	public FieldValidationException(int i) {
		this.index = i;
	}

	public String getMessage() {
		return Resources.getSystem().getString(this.index);
	}
}
