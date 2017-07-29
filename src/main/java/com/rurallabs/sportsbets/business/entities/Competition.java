package com.rurallabs.sportsbets.business.entities;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.rurallabs.sportsbets.business.util.I18nUtils;

@Entity
@Table(name = "COMPETITION")
public class Competition implements I18nNamedEntity {

	private static final long serialVersionUID = 3636625753997889778L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 200)
	private String name;

	@ElementCollection(fetch = FetchType.LAZY, targetClass = java.lang.String.class)
	@CollectionTable(name = "COMPETITION_NAME_I18N", joinColumns = @JoinColumn(name = "COMPETITION_ID") )
	@MapKeyColumn(name = "LANG", nullable = false, length = 20)
	@Column(name = "NAME", nullable = false, length = 200)
	private final Map<String, String> namesByLang = new LinkedHashMap<>();

	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;

	@ManyToMany(mappedBy = "competitions")
	private Set<Team> teams;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public String getName(final Locale locale) {
		return I18nUtils.getTextForLocale(locale, this.namesByLang, this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public Map<String, String> getNamesByLang() {
		return this.namesByLang;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(final Set<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "Competition [id=" + this.id + ", name=" + this.name + ", active=" + this.active + "]";
	}

}
