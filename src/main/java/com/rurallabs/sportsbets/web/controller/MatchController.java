package com.rurallabs.sportsbets.web.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rurallabs.sportsbets.business.entities.Match;
import com.rurallabs.sportsbets.business.services.MatchService;
import com.rurallabs.sportsbets.web.beans.LeagueBean;

@Controller
public class MatchController {
	
	@Autowired
	private MatchService matchService;
	
	@RequestMapping(value = "/ajax/createLeague/matchesByTeams", method = RequestMethod.GET)
	public String listMatchesByTeams(@ModelAttribute("leagueBean") final LeagueBean leagueBean, final Model model) {
		final List<Match> matches;
		if (CollectionUtils.isEmpty(leagueBean.getTeams())) {
			matches = this.matchService.findNextMatches();
		} else {
			matches = this.matchService.findByTeams(leagueBean.getTeams());
		}
		matches.add(0, new Match());
	    model.addAttribute("matches", matches);
		return "league/create :: matches";
	}

}
