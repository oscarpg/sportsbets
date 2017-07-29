package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LEAGUE_COMMENT")
public class LeagueComment implements Serializable {

	private static final long serialVersionUID = -2897466333568384144L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID", nullable = false)
	private User author;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@Column(name = "MESSAGE", nullable = false, length = 600)
	private String message;

	@ManyToOne
	@JoinColumn(name = "LEAGUE_ID", nullable = false)
	private League league;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return this.author;
	}

	public void setAuthor(final User author) {
		this.author = author;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(final League league) {
		this.league = league;
	}

}
