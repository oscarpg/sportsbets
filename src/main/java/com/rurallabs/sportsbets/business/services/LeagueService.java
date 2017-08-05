package com.rurallabs.sportsbets.business.services;

import java.util.List;

import javax.transaction.Transactional;

import com.rurallabs.sportsbets.business.entities.League;
import com.rurallabs.sportsbets.business.entities.LeagueUser;
import com.rurallabs.sportsbets.web.beans.LeagueBean;

public interface LeagueService {
	
	public League findById(final Long leagueId);
	
	public List<League> findByName(final String name);
	
	public League findByCode(final String code);
	
	public List<League> findPublicLeagues();
	
	@Transactional
	public League save(final String login, LeagueBean leagueBean);
	
	@Transactional
	public League join(Long leagueId, String login, String leaguePassword);
	
	public LeagueUser findLeagueUser(League league, String login);

}
