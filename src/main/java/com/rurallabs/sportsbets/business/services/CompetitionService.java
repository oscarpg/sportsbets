package com.rurallabs.sportsbets.business.services;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.rurallabs.sportsbets.business.entities.Competition;

public interface CompetitionService {

	List<Competition> findAll();

	Competition find(Long id);

	@Transactional
	Competition save(final Long id, final String name, final Map<String, String> namesByLang, final boolean active);

	@Transactional
	void delete(final Long competitionId);
}
