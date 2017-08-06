package com.rurallabs.sportsbets.business.services.impl;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rurallabs.sportsbets.business.entities.League;
import com.rurallabs.sportsbets.business.entities.LeagueConfig;
import com.rurallabs.sportsbets.business.entities.LeagueUser;
import com.rurallabs.sportsbets.business.entities.User;
import com.rurallabs.sportsbets.business.entities.repositories.LeagueConfigRepository;
import com.rurallabs.sportsbets.business.entities.repositories.LeagueRepository;
import com.rurallabs.sportsbets.business.entities.repositories.LeagueUserRepository;
import com.rurallabs.sportsbets.business.entities.repositories.UserRepository;
import com.rurallabs.sportsbets.business.services.LeagueService;
import com.rurallabs.sportsbets.business.services.UserService;
import com.rurallabs.sportsbets.web.beans.LeagueBean;

@Service
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private PasswordEncryptor passwordEncryptor;
	
	@Autowired
	private LeagueRepository leagueRepository;
	@Autowired
	private LeagueConfigRepository betConfigRepository;
	@Autowired
	private LeagueUserRepository leagueUserRepository;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public League findById(final Long leagueId) {
		return this.leagueRepository.findOne(leagueId);
	}
	
	@Override
	public List<League> findByName(final String name) {
		return this.leagueRepository.findByNameLikeIgnoreCaseAndActiveTrue('%' + name + '%');
	}
	
	@Override
	public League findByCode(final String code) {
		return this.leagueRepository.findByCodeIgnoreCaseAndActiveTrue(code);
	}

	@Override
	public List<League> findPublicLeagues() {
		return this.leagueRepository.findByActiveTrueAndPasswordIsNull();
	}

	@Override
	public League save(final String login, final LeagueBean leagueBean) {
		final User user = this.userService.find(login);
		
		final League newLeague = new League();
		newLeague.setName(leagueBean.getName());
		newLeague.setActive(true);
		
		
		if (leagueBean.getMatch() == null && CollectionUtils.isEmpty(leagueBean.getCompetitions()) 
				&& CollectionUtils.isEmpty(leagueBean.getTeams())) {
			// Empty league.
			return null;
		}
		
		if (StringUtils.isNotBlank(leagueBean.getPassword())) {
			newLeague.setPassword(this.passwordEncryptor.encryptPassword(leagueBean.getPassword()));
		}
		
		// Generate a new league code
		final RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();
		int i = 0;
		do {
			final String code = generator.generate(10);
			final boolean duplicatedCode = this.leagueRepository.existsByCode(code);
			if (!duplicatedCode) {
				newLeague.setCode(code);
				break;
			}
			i++;
		} while (i < 5);
		
		if (newLeague.getCode() == null) {
			// TODO Error, code couldn't be generated
			return null;
		}
		
		final LeagueConfig newLeagueConfig = new LeagueConfig();
		if (leagueBean.getMatch() != null) {
			newLeagueConfig.setMatch(leagueBean.getMatch());
		} else {
			newLeagueConfig.setCompetitions(leagueBean.getCompetitions());
			newLeagueConfig.setTeams(leagueBean.getTeams());
		}
		newLeagueConfig.setPublicBets(leagueBean.getPublicBets());
		if (leagueBean.getPublicBets().booleanValue()) {
			// Bet are visible. Set the max number of duplicated bets
			newLeagueConfig.setDuplicatedResults(leagueBean.getDuplicatedResults());
		} else {
			// Bets are hidden until matches start, do not limit duplicated bets
			newLeagueConfig.setDuplicatedResults(Integer.valueOf(Integer.MAX_VALUE));
		}
		
		// Create the new bet config
		final LeagueConfig leagueConfig = this.betConfigRepository.save(newLeagueConfig);
		newLeague.setLeagueConfig(leagueConfig);
		newLeague.setParticipants(new HashSet<>());
		
		final League league = this.leagueRepository.save(newLeague);
		
		final LeagueUser newLeagueUser = new LeagueUser();
		newLeagueUser.setLeague(league);
		newLeagueUser.setUser(user);
		newLeagueUser.setIsAdmin(Boolean.TRUE);
		final LeagueUser participant = this.leagueUserRepository.save(newLeagueUser);
		
		// Add the league to the user
		user.getUserLeagues().add(participant);
		this.userRepository.save(user);
		
		// Add the user to the league
		newLeague.getParticipants().add(participant);
		return this.leagueRepository.save(newLeague);
	}

	@Override
	public League join(final Long leagueId, final String login, final String leaguePassword) {
		final User user = this.userService.find(login);
		
		final League league = this.leagueRepository.findOne(leagueId);
		if (league == null) {
			//TODO throw new LeagueNotFoundException(); 
		}
		
		if (!league.isActive()) {
			//TODO throw new InactiveLeagueException(); 
		}
		
		if (StringUtils.isNotBlank(league.getPassword())) {
			if (!this.passwordEncryptor.checkPassword(leaguePassword, league.getPassword())) {
				//TODO throw new IncorrectPasswordException();
			}
		}
		
		if (CollectionUtils.isNotEmpty(user.getUserLeagues())) {
			for (final LeagueUser leagueForUser: user.getUserLeagues()) {
				if (leagueForUser.getLeague().equals(league)) {
					// The user already belongs to this league
					return league;
				}
			}
		}
		
		LeagueUser leagueUser = new LeagueUser();
		leagueUser.setLeague(league);
		leagueUser.setUser(user);
		leagueUser.setIsAdmin(Boolean.FALSE);
		
		leagueUser = this.leagueUserRepository.save(leagueUser);
		
		user.getUserLeagues().add(leagueUser);
		this.userRepository.save(user);
		
		// Add the user to the league
		league.getParticipants().add(leagueUser);
		return this.leagueRepository.save(league);
		
	}

	@Override
	public LeagueUser findLeagueUser(final League league, final String login) {
		if (league == null || !league.isActive()) {
			return null;
		}
		
		final User user = this.userService.find(login);
		
		for (final LeagueUser leagueUser: league.getParticipants()) {
			if (leagueUser.getUser().equals(user)) {
				return leagueUser;
			}
		}
		
		return null;
	}

}
