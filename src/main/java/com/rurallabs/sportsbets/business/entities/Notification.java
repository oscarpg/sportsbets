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

import org.hibernate.annotations.Type;

@Entity
@Table(name = "NOTIFICATION")
public class Notification implements Serializable {

	private static final long serialVersionUID = 124517458813142393L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@Column(name = "DATE", nullable = false)
	@Type(type = "timestamp")
	private Date date;

	@Column(name = "UNREAD", nullable = false)
	private Boolean unread;

	@Column(name = "MESSAGE", nullable = false, length = 500)
	private String message;

	public Notification() {
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Boolean getUnread() {
		return this.unread;
	}

	public void setUnread(final Boolean unread) {
		this.unread = unread;
	}

}
