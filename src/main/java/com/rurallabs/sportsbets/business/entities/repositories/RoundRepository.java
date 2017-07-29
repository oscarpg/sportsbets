package com.rurallabs.sportsbets.business.entities.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.Round;

@Repository
public interface RoundRepository extends PagingAndSortingRepository<Round, Long> {

	public List<Round> findByCompetitionId(final Integer competitionId);

}
