package com.rurallabs.sportsbets.business.entities.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.Team;

@Repository
public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {

	List<Team> findByCountry(String country);
	
	List<Team> findByRegion(String region);
	
}
