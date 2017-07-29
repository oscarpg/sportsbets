package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "MATCH")
public class Match implements Serializable, Comparable<Match> {

	private static final long serialVersionUID = 2453543096073719510L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "COMPETITION_ID", nullable = false)
	private Competition competition;

	@ManyToOne
	@JoinColumn(name = "ROUND_ID", nullable = true)
	private Round round;

	@Column(name = "DATE", nullable = true)
	@Type(type = "timestamp")
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HOME_TEAM_ID", nullable = true)
	private Team homeTeam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AWAY_TEAM_ID", nullable = true)
	private Team awayTeam;

	@Column(name = "SCORE_A", nullable = true)
	private Integer scoreA;

	@Column(name = "SCORE_B", nullable = true)
	private Integer scoreB;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Competition getCompetition() {
		return this.competition;
	}

	public void setCompetition(final Competition competition) {
		this.competition = competition;
	}

	public Round getRound() {
		return this.round;
	}

	public void setRound(final Round round) {
		this.round = round;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Team getHomeTeam() {
		return this.homeTeam;
	}

	public void setHomeTeam(final Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return this.awayTeam;
	}

	public void setAwayTeam(final Team awayTeam) {
		this.awayTeam = awayTeam;
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

	@Override
	public String toString() {
		return "Match [id=" + this.id + ", date=" + this.date + ", homeTeam=" + this.homeTeam + ", awayTeam="
				+ this.awayTeam + ", scoreA=" + this.scoreA + ", scoreB=" + this.scoreB + "]";
	}

	@Override
	public int compareTo(final Match o) {
		if (this.round != null && o.round != null) {
			final int roundComparisson = this.round.compareTo(o.round);
			if (roundComparisson != 0) {
				return roundComparisson;
			}
		}

		final int dateComparisson = this.date.compareTo(o.date);
		if (dateComparisson != 0) {
			return dateComparisson;
		}

		return this.homeTeam.getName().compareTo(o.getHomeTeam().getName());
	}

}
