package com.rurallabs.sportsbets.business.entities.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.Competition;

@Repository
public interface CompetitionRepository extends PagingAndSortingRepository<Competition, Long> {

	List<Competition> findByActiveTrue();

}
