package com.rurallabs.sportsbets.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rurallabs.sportsbets.business.entities.Competition;
import com.rurallabs.sportsbets.business.entities.Team;
import com.rurallabs.sportsbets.business.services.CompetitionService;
import com.rurallabs.sportsbets.business.services.TeamService;

@Controller
@RequestMapping("/league")
public class LeagueController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private CompetitionService competitionService;
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String showCreateLeagueForm(final HttpServletRequest request, final ModelMap model) {
		final List<Team> teams = this.teamService.findAll();
		
		final List<Competition> competitions = this.competitionService.findAll();
		
		model.addAttribute("teams", teams);
		model.addAttribute("competitions", competitions);
		//TODO
		return "create";
		
	}
	
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String joinLeague(final HttpServletRequest request, final ModelMap model) {
		//TODO
		return "join";
		
	}
}
