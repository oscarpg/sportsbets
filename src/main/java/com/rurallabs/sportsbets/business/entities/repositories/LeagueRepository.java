package com.rurallabs.sportsbets.business.entities.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.League;

@Repository
public interface LeagueRepository extends PagingAndSortingRepository<League, Long> {

	public List<League> findByActiveTrueAndPasswordIsNull();

	public List<League> findByNameIgnoreCaseAndActiveTrue(final String name);

}
