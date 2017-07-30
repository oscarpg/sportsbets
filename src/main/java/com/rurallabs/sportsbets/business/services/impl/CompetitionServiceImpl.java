package com.rurallabs.sportsbets.business.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rurallabs.sportsbets.business.entities.Competition;
import com.rurallabs.sportsbets.business.entities.repositories.CompetitionRepository;
import com.rurallabs.sportsbets.business.services.CompetitionService;

@Service
public class CompetitionServiceImpl implements CompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;
	
	@Override
	public List<Competition> findAll() {
		return this.competitionRepository.findByActiveTrue();
	}

	@Override
	public Competition find(final Long id) {
		return this.competitionRepository.findOne(id);
	}

	@Override
	public Competition save(final Long id, final String name, final Map<String, String> namesByLang, final boolean active) {
		final Competition competition =
                (id == null? new Competition() : this.competitionRepository.findOne(id));

        competition.setName(name);
        competition.getNamesByLang().clear();
        competition.getNamesByLang().putAll(namesByLang);
        competition.setActive(active);
        
        if (id == null) {
            return this.competitionRepository.save(competition);
        }
        return competition;
	}

	@Override
	public void delete(final Long competitionId) {
		this.competitionRepository.delete(competitionId);
	}

}
