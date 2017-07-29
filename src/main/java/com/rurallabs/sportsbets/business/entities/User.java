package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USER_DATA", uniqueConstraints=
		@UniqueConstraint(columnNames={"EMAIL"}))
public class User implements Serializable {

	private static final long serialVersionUID = 6044745065577451347L;

	@Id
	@Column(name = "LOGIN", length = 100)
	private String login;

	@Column(name = "NAME", nullable = false, length = 600)
	private String name;

	@Column(name = "EMAIL", nullable = false, length = 200)
	private String email;

	@Column(name = "PASSWORD", nullable = false, length = 500)
	private String password;

	@Column(name = "GLOBAL_ADMIN", nullable = false)
	private boolean globalAdmin = false;

	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LeagueUser> leagues;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Notification> notification;

	public User() {
		super();
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public boolean isGlobalAdmin() {
		return this.globalAdmin;
	}

	public void setGlobalAdmin(final boolean globalAdmin) {
		this.globalAdmin = globalAdmin;
	}

	public Set<LeagueUser> getUserLeagues() {
		return this.leagues;
	}

	public void setUserLeagues(final Set<LeagueUser> leagues) {
		this.leagues = leagues;
	}

	public Set<Notification> getNotification() {
		return this.notification;
	}

	public void setNotification(final Set<Notification> notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		return "User [login=" + this.login + ", email=" + this.email + "]";
	}

}
