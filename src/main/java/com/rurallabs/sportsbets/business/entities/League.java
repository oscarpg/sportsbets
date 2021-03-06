package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LEAGUE")
public class League implements Serializable {

	private static final long serialVersionUID = -6864675171936001856L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "PASSWORD", length = 50)
	private String password;

	@Column(name = "CODE", length = 10, nullable = false)
	private String code;

	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;

	@OneToMany(mappedBy = "league")
	private Set<LeagueUser> participants = new LinkedHashSet<>();

	@OneToOne(optional = false)
	@JoinColumn(name = "LEAGUE_CONFIG_ID", unique = true, nullable = false, updatable = false)
	private LeagueConfig leagueConfig;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public Set<LeagueUser> getParticipants() {
		return this.participants;
	}

	public void setParticipants(final Set<LeagueUser> participants) {
		this.participants = participants;
	}

	public LeagueConfig getLeagueConfig() {
		return this.leagueConfig;
	}

	public void setLeagueConfig(final LeagueConfig leagueConfig) {
		this.leagueConfig = leagueConfig;
	}

	@Override
	public String toString() {
		return "League [id=" + this.id + ", name=" + this.name + ", active=" + this.active + "]";
	}

}
