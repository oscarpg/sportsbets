package com.rurallabs.sportsbets.business.services;

import java.util.List;

import com.rurallabs.sportsbets.business.entities.League;

public interface LeagueService {
	
	public List<League> findByName(final String name);
	
	public List<League> findPublicLeagues();

}
