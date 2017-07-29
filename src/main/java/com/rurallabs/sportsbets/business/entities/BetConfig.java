package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BET_CONFIG")
public class BetConfig implements Serializable {

	private static final long serialVersionUID = 6879513900888641245L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false, mappedBy = "betConfig")
	private League league;
	
	@Column(name = "PUBLIC_BETS", nullable = false)
	private Boolean publicBets;

	@Column(name = "DUPLICATED_RESULTS", nullable = true)
	private Integer duplicatedResults;

	@ManyToMany
	@JoinTable(name = "BET_CONFIG_COMPETITION", 
		joinColumns = @JoinColumn(name = "BET_CONFIG_ID", referencedColumnName = "ID") , 
		inverseJoinColumns = @JoinColumn(name = "COMPETITION_ID", referencedColumnName = "ID") )
	private Set<Competition> competitions;
	
	@ManyToMany
	@JoinTable(name = "BET_CONFIG_TEAM", 
		joinColumns = @JoinColumn(name = "BET_CONFIG_ID", referencedColumnName = "ID") , 
		inverseJoinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID") )
	private Set<Team> teams;
	
	@ManyToOne
    @JoinColumn(name="MATCH_ID", nullable=true)
	private Match match;

	public BetConfig() {
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

	public Set<Competition> getCompetitions() {
		return this.competitions;
	}

	public void setCompetitions(final Set<Competition> competitions) {
		this.competitions = competitions;
	}

	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(final Set<Team> teams) {
		this.teams = teams;
	}

	public Match getMatch() {
		return this.match;
	}

	public void setMatch(final Match match) {
		this.match = match;
	}

}
