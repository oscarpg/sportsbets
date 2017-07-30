package com.rurallabs.sportsbets.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OverviewController {
	
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public String overview(final HttpServletRequest request, final ModelMap model) {
		
		return "overview";
	}

}
