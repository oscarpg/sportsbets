package com.rurallabs.sportsbets.exceptions;

public class DuplicatedEmailException extends Exception {

	private static final long serialVersionUID = 2246629172125297625L;

	private final String email;

	public DuplicatedEmailException(final String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	@Override
	public String toString() {
		return "DuplicatedEmailException [email=" + this.email + "]";
	}

}
