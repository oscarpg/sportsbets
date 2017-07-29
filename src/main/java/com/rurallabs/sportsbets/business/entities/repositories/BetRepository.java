package com.rurallabs.sportsbets.business.entities.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.Bet;

@Repository
public interface BetRepository extends PagingAndSortingRepository<Bet, Long> {

	// No methods to add
}
