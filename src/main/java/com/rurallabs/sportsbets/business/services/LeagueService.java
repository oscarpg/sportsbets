package com.rurallabs.sportsbets.business.services;

import java.util.List;

import com.rurallabs.sportsbets.business.entities.League;
import com.rurallabs.sportsbets.web.beans.LeagueBean;

public interface LeagueService {
	
	public List<League> findByName(final String name);
	
	public List<League> findPublicLeagues();
	
	public League save(final String login, LeagueBean leagueBean);

}
