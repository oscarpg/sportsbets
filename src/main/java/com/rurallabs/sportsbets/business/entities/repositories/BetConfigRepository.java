package com.rurallabs.sportsbets.business.entities.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.BetConfig;

@Repository
public interface BetConfigRepository extends PagingAndSortingRepository<BetConfig, Long> {

	public BetConfig findByLeagueId(final Long leagueId);

}
