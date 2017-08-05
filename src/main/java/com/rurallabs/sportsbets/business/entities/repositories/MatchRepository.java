package com.rurallabs.sportsbets.business.entities.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.Match;
import com.rurallabs.sportsbets.business.entities.Team;

@Repository
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {

	@Query("select m from Match m where m.homeTeam in (?1) or m.awayTeam in (?1) order by m.date desc")
	List<Match> findByTeams(Collection<Team> teams);
	
	List<Match> findFirst10ByOrderByDateDesc();
}
