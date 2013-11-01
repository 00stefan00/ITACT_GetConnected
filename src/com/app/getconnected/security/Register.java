package com.app.getconnected.security;

/**
 * Created with IntelliJ IDEA.
 * User: johan
 * Date: 11/1/13
 * Time: 11:48 AM
 */
public class Register  extends Api {

	private String username, password, firstName, lastName, telephoneNumber, email, gender;

	public Register(String username, String password, String firstName, String lastName, String telephoneNumber, String email, String gender) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephoneNumber = telephoneNumber;
		this.email = email;
		this.gender = gender;
		fillMap();
	}

	@Override
	public void fillMap() {
		emptyMap();
		addToMap("username", username);
		addToMap("randomPass", password);
		addToMap("firstname", firstName);
		addToMap("lastname", lastName);
		addToMap("telephonenumber", telephoneNumber);
		addToMap("email", email);
		addToMap("gender", gender);
	}
}
