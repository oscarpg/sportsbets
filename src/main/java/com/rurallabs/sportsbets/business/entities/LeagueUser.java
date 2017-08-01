package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LEAGUE_USERS", uniqueConstraints = @UniqueConstraint(columnNames = { "LEAGUE_ID", "USER_ID"}))
public class LeagueUser implements Serializable {

	private static final long serialVersionUID = -3238478280036248437L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "LEAGUE_ID")
	private League league;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "IS_ADMIN", nullable = false)
	private Boolean isAdmin;

	public LeagueUser() {
		super();
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
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
