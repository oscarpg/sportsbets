package com.rurallabs.sportsbets.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.rurallabs.sportsbets.business.entities.Competition;
import com.rurallabs.sportsbets.business.entities.Match;
import com.rurallabs.sportsbets.business.entities.Team;

public class LeagueBean implements Serializable {

	private static final long serialVersionUID = -5996705884298880120L;

	@NotBlank
	private String name;

	private String password;

	@NotNull
	private Boolean publicBets;

	private Integer duplicatedResults;

	private List<Competition> competitions;

	private List<Team> teams;

	private Match match;

	public LeagueBean() {
		super();
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

	public Boolean getPublicBets() {
		return this.publicBets;
	}

	public void setPublicBets(final Boolean publicBets) {
		this.publicBets = publicBets;
	}

	public Integer getDuplicatedResults() {
		return this.duplicatedResults;
	}

	public void setDuplicatedResults(final Integer duplicatedResults) {
		this.duplicatedResults = duplicatedResults;
	}

	public List<Competition> getCompetitions() {
		return this.competitions;
	}

	public void setCompetitions(final List<Competition> competitions) {
		this.competitions = competitions;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(final List<Team> teams) {
		this.teams = teams;
	}

	public Match getMatch() {
		return this.match;
	}

	public void setMatch(final Match match) {
		this.match = match;
	}

}
