package com.rurallabs.sportsbets.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rurallabs.sportsbets.business.entities.Competition;
import com.rurallabs.sportsbets.business.entities.League;
import com.rurallabs.sportsbets.business.entities.Team;
import com.rurallabs.sportsbets.business.services.CompetitionService;
import com.rurallabs.sportsbets.business.services.LeagueService;
import com.rurallabs.sportsbets.business.services.TeamService;
import com.rurallabs.sportsbets.web.beans.LeagueBean;

@Controller
@RequestMapping("/league")
public class LeagueController {
	
	private static final String VIEW_BASE = "league/";
	
	@Autowired
	private CompetitionService competitionService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private LeagueService leagueService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateLeagueForm(final HttpServletRequest request, final ModelMap model) {
		final List<Team> teams = this.teamService.findAll();
		
		final List<Competition> competitions = this.competitionService.findAll();
		
		model.addAttribute("teams", teams);
		model.addAttribute("competitions", competitions);
		
		final LeagueBean leagueBean = new LeagueBean();
		model.addAttribute("leagueBean", leagueBean);

		return VIEW_BASE + "create";
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String signup(final HttpServletRequest request, final ModelMap model, 
			@Valid @ModelAttribute(value = "leagueBean") final LeagueBean leagueBean, final BindingResult result,
			final RedirectAttributes redirectAttributes) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String login = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		
		final League league = this.leagueService.save(login, leagueBean);
		if (league == null) {
			//Show an error
			return VIEW_BASE + "create";
		}
		
		return "redirect:/overview";
	}
	
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String joinLeague(final HttpServletRequest request, final ModelMap model) {
		//TODO
		return VIEW_BASE + "join";
		
	}
}
