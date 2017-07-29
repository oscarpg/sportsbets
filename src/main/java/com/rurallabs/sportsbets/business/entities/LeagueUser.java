package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LEAGUE_USERS")
public class LeagueUser implements Serializable {

	private static final long serialVersionUID = -3238478280036248437L;

	@Id
	@ManyToOne
	@JoinColumn(name = "LEAGUE_ID")
	private League league;

	@Id
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "IS_ADMIN", nullable = false)
	private Boolean isAdmin;

	public LeagueUser() {
		super();
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(final League league) {
		this.league = league;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(final Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
