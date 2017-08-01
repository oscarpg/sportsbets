package com.rurallabs.sportsbets.business.services.impl;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rurallabs.sportsbets.business.entities.BetConfig;
import com.rurallabs.sportsbets.business.entities.League;
import com.rurallabs.sportsbets.business.entities.LeagueUser;
import com.rurallabs.sportsbets.business.entities.User;
import com.rurallabs.sportsbets.business.entities.repositories.BetConfigRepository;
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
	private BetConfigRepository betConfigRepository;
	@Autowired
	private LeagueUserRepository leagueUserRepository;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<League> findByName(final String name) {
		return this.leagueRepository.findByNameLikeIgnoreCaseAndActiveTrue(name);
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
			//TODO
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
		
		final BetConfig newBetConfig = new BetConfig();
		if (leagueBean.getMatch() != null) {
			newBetConfig.setMatch(leagueBean.getMatch());
		} else {
			newBetConfig.setCompetitions(leagueBean.getCompetitions());
			newBetConfig.setTeams(leagueBean.getTeams());
		}
		newBetConfig.setPublicBets(leagueBean.getPublicBets());
		if (leagueBean.getPublicBets().booleanValue()) {
			// Bet are visible. Set the max number of duplicated bets
			newBetConfig.setDuplicatedResults(leagueBean.getDuplicatedResults());
		} else {
			// Bets are hidden until matches start, do not limit duplicated bets
			newBetConfig.setDuplicatedResults(Integer.valueOf(Integer.MAX_VALUE));
		}
		
		// Create the new bet config
		final BetConfig betConfig = this.betConfigRepository.save(newBetConfig);
		newLeague.setBetConfig(betConfig);
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

}
