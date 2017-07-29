package com.rurallabs.sportsbets.exceptions;

public class DuplicatedLoginException extends Exception {

	private static final long serialVersionUID = 2246629172125297625L;

	private final String login;

	public DuplicatedLoginException(final String login) {
		super();
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	@Override
	public String toString() {
		return "DuplicatedLoginException [login=" + this.login + "]";
	}

}
