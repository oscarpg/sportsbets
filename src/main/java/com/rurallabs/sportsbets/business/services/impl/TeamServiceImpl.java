package com.rurallabs.sportsbets.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rurallabs.sportsbets.business.entities.Team;
import com.rurallabs.sportsbets.business.entities.repositories.TeamRepository;
import com.rurallabs.sportsbets.business.services.TeamService;
import com.rurallabs.sportsbets.business.util.IterableUtils;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Override
	public List<Team> findAll() {
		return IterableUtils.toList(this.teamRepository.findAll());
	}

}
