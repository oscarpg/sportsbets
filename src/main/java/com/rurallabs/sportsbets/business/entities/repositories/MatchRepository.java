package com.rurallabs.sportsbets.business.entities.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.Match;

@Repository
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {

	// No methods to add
}
