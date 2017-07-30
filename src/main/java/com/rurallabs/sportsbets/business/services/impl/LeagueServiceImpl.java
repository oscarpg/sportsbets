package com.rurallabs.sportsbets.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rurallabs.sportsbets.business.entities.League;
import com.rurallabs.sportsbets.business.entities.repositories.LeagueRepository;
import com.rurallabs.sportsbets.business.services.LeagueService;

@Service
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private LeagueRepository leagueRepository;
	
	@Override
	public List<League> findByName(final String name) {
		return this.leagueRepository.findByNameLikeIgnoreCaseAndActiveTrue(name);
	}

	@Override
	public List<League> findPublicLeagues() {
		return this.leagueRepository.findByActiveTrueAndPasswordIsNull();
	}

}
