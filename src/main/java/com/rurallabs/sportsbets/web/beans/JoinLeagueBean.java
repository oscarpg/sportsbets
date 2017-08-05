package com.rurallabs.sportsbets.web.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class JoinLeagueBean implements Serializable {

	private static final long serialVersionUID = 9221183980566816398L;

	@NotNull
	private Long id;
	private String password;

	public JoinLeagueBean() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
