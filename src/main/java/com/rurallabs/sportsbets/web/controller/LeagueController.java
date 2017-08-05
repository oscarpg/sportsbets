package com.rurallabs.sportsbets.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rurallabs.sportsbets.business.entities.Competition;
import com.rurallabs.sportsbets.business.entities.League;
import com.rurallabs.sportsbets.business.entities.LeagueUser;
import com.rurallabs.sportsbets.business.entities.Team;
import com.rurallabs.sportsbets.business.services.CompetitionService;
import com.rurallabs.sportsbets.business.services.LeagueService;
import com.rurallabs.sportsbets.business.services.TeamService;
import com.rurallabs.sportsbets.web.beans.JoinLeagueBean;
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
			@Valid @ModelAttribute(value = "leagueBean") final LeagueBean leagueBean, 
			final BindingResult result,
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
	
	@RequestMapping(value = "searchLeagues", method = RequestMethod.GET)
	public String joinLeague(final HttpServletRequest request, final ModelMap model) {
		// Just show the template
		return VIEW_BASE + "join";
		
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String searchLeague(final HttpServletRequest request, final ModelMap model,
			@RequestParam(value = "name", required = false) final String name,
			@RequestParam(value = "code", required = false) final String code,
			final RedirectAttributes redirectAttributes) {
		
		if (StringUtils.isBlank(name) && StringUtils.isBlank(code)) {
			redirectAttributes.addFlashAttribute("error", "search.league.any.mandatory");
			return VIEW_BASE + "join";
		}
		
		List<League> leagues;
		if (StringUtils.isNotBlank(name)) {
			leagues = this.leagueService.findByName(name);
		} else {
			final League league = this.leagueService.findByCode(code);
			leagues = new ArrayList<>(); 
			if (league != null) {
				leagues.add(league);
			} 
		}
		model.addAttribute("leagues", leagues);
		
		final JoinLeagueBean joinLeagueBean = new JoinLeagueBean();
		model.addAttribute("joinLeagueBean", joinLeagueBean);
		return VIEW_BASE + "join";
		
	}
	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String joinLeague(final HttpServletRequest request, final ModelMap model,
			@Valid @ModelAttribute(value = "joinLeagueBean") final JoinLeagueBean joinLeagueBean, 
			final BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return VIEW_BASE + "join";
		}
		
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String login = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		
		final League league = this.leagueService.join(joinLeagueBean.getId(), login, joinLeagueBean.getPassword());
		if (league == null) {
			redirectAttributes.addFlashAttribute("error", "join.league.unable.to.join");
			return VIEW_BASE + "join";
		}
		
		// Go to the league details
		return "redirect:/" + VIEW_BASE + league.getId().toString();
	}
	
	@RequestMapping("{leagueId}")
	public String leagueDetails(@PathVariable("leagueId") final Long leagueId, final HttpServletRequest request,
			final ModelMap model) {
		
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String login = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		
		final League league = this.leagueService.findById(leagueId);
		if (league == null) {
			return "404";
		}
		
		final LeagueUser leagueUser = this.leagueService.findLeagueUser(league, login);
		if (leagueUser == null) {
			return "403";
		}
		
		model.addAttribute("league", league);
		model.addAttribute("leagueUser", leagueUser);
		
		return VIEW_BASE + "scoreboard";

	}
	
}
