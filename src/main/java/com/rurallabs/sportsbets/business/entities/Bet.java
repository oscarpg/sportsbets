package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BET")
public class Bet implements Serializable {

	private static final long serialVersionUID = -4415222630116003583L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEAGUE_ID", nullable = false)
	private League league;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATCH_ID", nullable = false)
	private Match match;

	@Column(name = "SCORE_A", nullable = true)
	private Integer scoreA;

	@Column(name = "SCORE_B", nullable = true)
	private Integer scoreB;

	@Column(name = "POINTS_COMPUTED", nullable = false)
	private final Boolean pointsComputed = Boolean.FALSE;

	@Column(name = "POINTS", nullable = true)
	private Integer points;

	public Bet() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(final League league) {
		this.league = league;
	}

	public Integer getScoreA() {
		return this.scoreA;
	}

	public void setScoreA(final Integer scoreA) {
		this.scoreA = scoreA;
	}

	public Integer getScoreB() {
		return this.scoreB;
	}

	public void setScoreB(final Integer scoreB) {
		this.scoreB = scoreB;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(final Integer points) {
		this.points = points;
	}

	public Boolean getPointsComputed() {
		return this.pointsComputed;
	}

	public Match getMatch() {
		return this.match;
	}

	public void setMatch(final Match match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "Bet [id=" + this.id + ", user=" + this.user + ", scoreA=" + this.scoreA + ", scoreB=" + this.scoreB
				+ ", pointsComputed=" + this.pointsComputed + ", points=" + this.points + "]";
	}

}
