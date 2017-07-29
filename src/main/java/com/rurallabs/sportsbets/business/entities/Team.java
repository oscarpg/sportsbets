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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.rurallabs.sportsbets.business.util.I18nUtils;

@Entity
@Table(name = "TEAM")
public class Team implements I18nNamedEntity {

	private static final long serialVersionUID = 6483080992140147998L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@ElementCollection(fetch = FetchType.LAZY, targetClass = java.lang.String.class)
	@CollectionTable(name = "TEAM_NAME_I18N", joinColumns = @JoinColumn(name = "TEAM_ID") )
	@MapKeyColumn(name = "LANG", nullable = false, length = 20)
	@Column(name = "NAME", nullable = false, length = 200)
	private final Map<String, String> namesByLang = new LinkedHashMap<>();

	@Column(name = "CODE", nullable = false, length = 5)
	private String code;
	
	@Column(name = "COUNTRY", nullable = false, length=10)
	private String country;
	
	@Column(name = "REGION", nullable = true, length=10)
	private String region;

	@ManyToMany
	@JoinTable(name = "COMPETITION_TEAMS", 
		joinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID") , 
		inverseJoinColumns = @JoinColumn(name = "COMPETITION_ID", referencedColumnName = "ID") )
	private Set<Competition> competitions;

	public Team() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}
	
	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(final String region) {
		this.region = region;
	}

	@Override
	public Map<String, String> getNamesByLang() {
		return this.namesByLang;
	}

	@Override
	public String getName(final Locale locale) {
		return I18nUtils.getTextForLocale(locale, this.namesByLang, this.name);
	}

	public Set<Competition> getCompetitions() {
		return this.competitions;
	}

	public void setCompetitions(final Set<Competition> competitions) {
		this.competitions = competitions;
	}

	@Override
	public String toString() {
		return "Team [id=" + this.id + ", name=" + this.name + ", code=" + this.code + "]";
	}

}
