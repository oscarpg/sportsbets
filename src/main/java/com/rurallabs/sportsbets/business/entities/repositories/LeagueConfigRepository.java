package com.rurallabs.sportsbets.business.entities.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.LeagueConfig;

@Repository
public interface LeagueConfigRepository extends PagingAndSortingRepository<LeagueConfig, Long> {

	public LeagueConfig findByLeagueId(final Long leagueId);

}
