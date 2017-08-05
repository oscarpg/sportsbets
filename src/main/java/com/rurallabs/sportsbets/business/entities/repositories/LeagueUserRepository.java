package com.rurallabs.sportsbets.business.entities.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.LeagueUser;

@Repository
public interface LeagueUserRepository extends PagingAndSortingRepository<LeagueUser, Long> {
	
	// No methods to add
	
}
