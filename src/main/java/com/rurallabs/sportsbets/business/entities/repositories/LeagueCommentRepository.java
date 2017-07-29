package com.rurallabs.sportsbets.business.entities.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.LeagueComment;

@Repository
public interface LeagueCommentRepository extends PagingAndSortingRepository<LeagueComment, Long> {

	public List<LeagueComment> findByLeagueIdOrderByDate(Long leagueId);
}
