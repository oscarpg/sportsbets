package com.rurallabs.sportsbets.web.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rurallabs.sportsbets.business.entities.League;
import com.rurallabs.sportsbets.business.entities.LeagueUser;
import com.rurallabs.sportsbets.business.entities.User;
import com.rurallabs.sportsbets.business.services.UserService;

@Controller
public class OverviewController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public String overview(final HttpServletRequest request, final ModelMap model) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String login = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();

		final User user = this.userService.find(login);
		
		final Set<League> leagues = new HashSet<>();
		if (CollectionUtils.isNotEmpty(user.getUserLeagues())) {
			for (final LeagueUser league: user.getUserLeagues()) {
				leagues.add(league.getLeague());
			}
		}

		model.addAttribute("leagues", leagues);

		return "overview";
	}

}
