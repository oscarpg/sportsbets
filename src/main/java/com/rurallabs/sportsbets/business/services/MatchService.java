package com.rurallabs.sportsbets.business.services;

import java.util.List;
import java.util.Set;

import com.rurallabs.sportsbets.business.entities.Match;
import com.rurallabs.sportsbets.business.entities.Team;

public interface MatchService {
	
	List<Match> findByTeams(Set<Team> teams);
	
	List<Match> findNextMatches();

}
