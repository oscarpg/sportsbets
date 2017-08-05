package com.rurallabs.sportsbets.business.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rurallabs.sportsbets.business.entities.Match;
import com.rurallabs.sportsbets.business.entities.Team;
import com.rurallabs.sportsbets.business.entities.repositories.MatchRepository;
import com.rurallabs.sportsbets.business.services.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchRepository matchRepository;
	
	@Override
	public List<Match> findByTeams(final Set<Team> teams) {
		return this.matchRepository.findByTeams(teams);
	}

	@Override
	public List<Match> findNextMatches() {
		return this.matchRepository.findFirst10ByOrderByDateDesc();
	}

}
